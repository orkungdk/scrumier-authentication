package tr.com.ogedik.authentication.service;

import tr.com.ogedik.authentication.model.AuthenticationDetails;
import tr.com.ogedik.commons.rest.request.model.AuthenticationRequest;

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
  AuthenticationDetails authenticate(AuthenticationRequest authenticationRequest);

  String validateToken(String token);
}
