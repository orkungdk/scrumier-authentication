/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service;

import tr.com.ogedik.authentication.model.Authentication;
import tr.com.ogedik.authentication.request.AuthenticationRequest;

/**
 * @author orkun.gedik
 */
public interface AuthenticationService {

  /**
   * Authenticates the user
   *
   * @param authenticationRequest the object of {@link AuthenticationRequest}
   * @return token
   */
  Authentication authenticate(AuthenticationRequest authenticationRequest);

}
