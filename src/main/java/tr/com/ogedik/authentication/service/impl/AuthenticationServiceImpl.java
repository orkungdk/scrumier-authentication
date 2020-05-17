/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AuthenticationDetails;
import tr.com.ogedik.authentication.model.AuthenticationRequest;
import tr.com.ogedik.authentication.service.AuthenticationService;
import tr.com.ogedik.authentication.service.UserDetailsService;
import tr.com.ogedik.authentication.util.AuthenticationTokenHelper;

/**
 * @author orkun.gedik
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private AuthenticationTokenHelper authenticationTokenHelper;
  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * Authenticates user after validating the request
   *
   * @param authenticationRequest the object of {@link AuthenticationRequest}
   * @return generated token
   * @throws AuthenticationException if user is not exist / disabled or the credentials are incorrect
   */
  public AuthenticationDetails authenticate(AuthenticationRequest authenticationRequest) {

    try {
      AuthenticationDetails authenticationDetails = (AuthenticationDetails)authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
              authenticationRequest.getPassword()));
      authenticationDetails.setToken(generateToken(authenticationRequest, authenticationDetails));

      return authenticationDetails;
    } catch (DisabledException e) {
      throw new AuthenticationException(AuthenticationConstants.Exception.USER_DISABLED);
    } catch (BadCredentialsException e) {
      throw new AuthenticationException(AuthenticationConstants.Exception.INVALID_CREDENTIALS);
    }
  }

  /**
   * Generates authentication token
   *
   * @param authenticationRequest the object of {@link AuthenticationRequest}
   * @param authenticationDetails the object of {@link AuthenticationDetails}
   * @return generated authentication token
   */
  private String generateToken(AuthenticationRequest authenticationRequest,
      AuthenticationDetails authenticationDetails) {
    if (authenticationDetails.isAuthenticated()) {
      final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

      return authenticationTokenHelper.generateToken(userDetails);
    } else {
      throw new AuthenticationException(AuthenticationConstants.Exception.AUTH_FAIL);
    }
  }
}
