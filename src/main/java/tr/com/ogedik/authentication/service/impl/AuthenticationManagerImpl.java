package tr.com.ogedik.authentication.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.controller.AuthenticationController;
import tr.com.ogedik.authentication.model.Authentication;
import tr.com.ogedik.authentication.model.GrantedAuthority;
import tr.com.ogedik.commons.models.Group;
import tr.com.ogedik.commons.models.User;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.commons.utils.ListUtils;

import java.util.List;

/**
 * @author orkun.gedik
 */
@Service
@Primary
public class AuthenticationManagerImpl implements AuthenticationManager {

  private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

  @Autowired
  private UserService userService;

  @Override
  public Authentication authenticate(org.springframework.security.core.Authentication authentication)
      throws AuthenticationException {

    // Retrieve user from database
    User user = userService.getUserByUsername(authentication.getPrincipal().toString());
    // ToDo: Retrieve user from cache

    if (user == null) {
      throw new UsernameNotFoundException(AuthenticationConstants.Exception.USER_NOT_FOUND);
    }
    if (!StringUtils.equalsIgnoreCase(user.getPassword(), authentication.getCredentials())) {
      throw new BadCredentialsException(AuthenticationConstants.Exception.AUTH_FAIL);
    }

    return Authentication.builder()
        .authorities(getAuthorities(user.getGroups()))
        .principal(user.getUsername())
        .credentials(user.getPassword())
        .isAuthenticated(true)
        .build();
  }

  private List<GrantedAuthority> getAuthorities(List<Group> groups) {
    try {
      return (List<GrantedAuthority>)ListUtils.mergeNested(groups, "authorities");
    } catch (IllegalAccessException e) {
      logger.warn("Cannot parse user permissions. Authentication will be provided without authorities");
      return null;
    }
  }
}
