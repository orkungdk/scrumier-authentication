/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service;

import tr.com.ogedik.authentication.model.AuthenticationRequest;

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
  String authenticate(AuthenticationRequest authenticationRequest);

}
