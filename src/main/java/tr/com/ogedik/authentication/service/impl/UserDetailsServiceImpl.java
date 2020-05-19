/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.service.UserDetailsService;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.util.AuthenticationUtil;

/**
 * @author orkun.gedik
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthenticationUser user = userService.getUserByUsername(username);

    if (user == null) {
      logger.warn("ApplicationUser cannot be found in database. Username is {}", username);
      throw new AuthenticationException(AuthenticationConstants.Exception.USER_NOT_FOUND);
    }

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .authorities(AuthenticationUtil.getAuthorities(user.getGroups()))
        .build();
  }
}
