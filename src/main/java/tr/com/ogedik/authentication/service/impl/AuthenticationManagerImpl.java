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
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.util.AuthenticationUtil;
import tr.com.ogedik.commons.models.User;

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
    logger.info("Username and password validations are OK. Authentication is being initialized.");

    return Authentication.builder()
        .authorities(AuthenticationUtil.getAuthorities(user.getGroups()))
        .principal(user.getUsername())
        .credentials(user.getPassword())
        .isAuthenticated(true)
        .build();
  }

}
