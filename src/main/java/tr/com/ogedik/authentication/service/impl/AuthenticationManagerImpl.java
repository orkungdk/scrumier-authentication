package tr.com.ogedik.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.service.ApplicationUserService;

/**
 * @author orkun.gedik
 */
@Service
@Qualifier(AuthenticationConstants.Qualifier.AUTH_MANAGER)
public class AuthenticationManagerImpl implements AuthenticationManager {

  @Autowired
  private ApplicationUserService applicationuserService;

  @Override
  public Authentication authenticate(Authentication auth) throws AuthenticationException {
    ApplicationUser applicationUser = applicationuserService.getUserByUsername(auth.getPrincipal().toString());
    if (applicationUser == null) {
      throw new UsernameNotFoundException(AuthenticationConstants.Exception.USER_NOT_FOUND);
    }
    if (!StringUtils.equalsIgnoreCase(applicationUser.getPassword(), auth.getCredentials())) {
      throw new BadCredentialsException(AuthenticationConstants.Exception.AUTH_FAIL);
    }

    return new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), applicationUser.getPassword(), null);
  }
}
