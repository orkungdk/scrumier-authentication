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

import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AuthenticationDetails;
import tr.com.ogedik.commons.request.model.AuthenticationRequest;
import tr.com.ogedik.authentication.service.AuthenticationService;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.commons.helper.TokenHelper;

/**
 * @author orkun.gedik
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;

  /**
   * Authenticates user after validating the request
   *
   * @param authenticationRequest the object of {@link AuthenticationRequest}
   * @return generated token
   * @throws AuthenticationException if user is not exist / disabled or the credentials are incorrect
   */
  public AuthenticationDetails authenticate(AuthenticationRequest authenticationRequest) {

    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          authenticationRequest.getUsername(), authenticationRequest.getPassword());
      AuthenticationDetails authenticationDetails = (AuthenticationDetails) authenticationManager
          .authenticate(authenticationToken);
      authenticationDetails.setToken(generateToken(authenticationRequest, authenticationDetails));

      return authenticationDetails;
    } catch (DisabledException e) {
      throw new AuthenticationException(AuthenticationErrorType.USER_DISABLED, "User is not active!");
    } catch (BadCredentialsException e) {
      throw new AuthenticationException(AuthenticationErrorType.INVALID_CREDENTIALS, "Incorrect username or password!");
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
      final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

      return TokenHelper.generateToken(userDetails.getUsername());
    } else {
      throw new AuthenticationException(AuthenticationErrorType.AUTH_FAIL, "User cannot be authenticated.");
    }
  }
}
