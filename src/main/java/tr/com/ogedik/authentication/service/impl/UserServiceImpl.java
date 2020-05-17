/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.entity.UserEntity;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.mapper.UserMapper;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.persistance.manager.UserPersistenceManager;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.util.ResourceIdGenerator;
import tr.com.ogedik.authentication.validation.user.UserValidationFacade;

/**
 * @author orkun.gedik
 */
@Service
public class UserServiceImpl implements UserService {

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
    return ObjectUtils.isEmpty(entity) ? null : userMapper.convert(entity);
  }

  @Override
  public AuthenticationUser create(AuthenticationUser user) {
    user.setResourceId(ResourceIdGenerator.generate());
    validationFacade.validateCreate(user);
    user.setEnrolmentDate(LocalDateTime.now());

    UserEntity savedEntity = persistenceManager.save(userMapper.convert(user));

    return userMapper.convert(savedEntity);
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
      throw new AuthenticationException(AuthenticationConstants.Exception.USER_NOT_FOUND);
    }

    persistenceManager.deleteByUsername(username);
  }
}
