package tr.com.ogedik.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.com.ogedik.authentication.entity.UserEntity;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.mapper.UserMapper;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.persistance.manager.UserPersistenceManager;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.util.AuthenticationUtil;
import tr.com.ogedik.authentication.validation.user.UserValidationFacade;
import tr.com.ogedik.commons.abstraction.AbstractService;
import tr.com.ogedik.commons.constants.Services;
import tr.com.ogedik.commons.model.JiraUser;
import tr.com.ogedik.commons.rest.response.RestResponse;
import tr.com.ogedik.commons.util.MapUtils;
import tr.com.ogedik.commons.rest.request.client.HttpRestClient;
import tr.com.ogedik.commons.rest.request.client.helper.RequestURLDetails;

/**
 * @author orkun.gedik
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService implements UserService {

  private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

  @Autowired
  private UserPersistenceManager persistenceManager;
  @Autowired
  private UserValidationFacade validationFacade;
  @Autowired
  private UserMapper userMapper;

  @Override
  public List<AuthenticationUser> getAllUsers() {
    return userMapper.convert(persistenceManager.findAll());
  }

  @Override
  public boolean isExist(String username) {
    return persistenceManager.existsByUsername(username);
  }

  @Override
  public AuthenticationUser getUserByUsername(String username) {
    UserEntity entity = persistenceManager.findByUsername(username);
    return userMapper.convert(entity);
  }

  @Override
  public AuthenticationUser create(AuthenticationUser user) {
    validationFacade.validateCreate(user);
    fulfillUser(user);

    UserEntity toBeCreatedEntity = userMapper.convertCreate(user);
    UserEntity createdEntity = persistenceManager.save(toBeCreatedEntity);

    return userMapper.convert(createdEntity);
  }

  private void fulfillUser(AuthenticationUser user) {
    user.setEnrolmentDate(LocalDateTime.now());
    user.setAvatarUrl(getAvatarUrl(user.getUsername()));
  }

  private String getAvatarUrl(String username) {
    RequestURLDetails requestURLDetails = new RequestURLDetails(getRequestUrl(Services.INTEGRATION), Services.Path.JIRA_USER, MapUtils.of("username", username));
    RestResponse<JiraUser> jiraUserResponse = HttpRestClient.doGet(requestURLDetails, JiraUser.class);
    Map<String, String> avatarUrls = jiraUserResponse.getBody().getAvatarUrls();

    return avatarUrls.get("48x48"); // the biggest logo size
  }

  @Override
  public AuthenticationUser update(AuthenticationUser user) {
    validationFacade.validateUpdate(user);

    UserEntity foundUser = persistenceManager.findByUsername(user.getUsername());
    user.setResourceId(foundUser.getResourceId());

    UserEntity userToBeUpdated = userMapper.convert(user);
    UserEntity updatedEntity = persistenceManager.update(userToBeUpdated);

    return userMapper.convert(updatedEntity);
  }

  @Override
  public void delete(String username) {
    if (BooleanUtils.isFalse(persistenceManager.existsByUsername(username))) {
      throw new AuthenticationException(AuthenticationErrorType.USER_NOT_FOUND, username + " username isn't exist." );
    }

    persistenceManager.deleteByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthenticationUser user = getUserByUsername(username);

    if (user == null) {
      logger.warn("ApplicationUser cannot be found in database. Username is {}", username);
      throw new AuthenticationException(AuthenticationErrorType.USER_NOT_FOUND, "User is not registered yet.");
    }

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .authorities(AuthenticationUtil.getAuthorities(user.getGroups()))
        .build();
  }
}
