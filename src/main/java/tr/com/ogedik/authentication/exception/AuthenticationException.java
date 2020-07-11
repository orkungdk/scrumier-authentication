/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tr.com.ogedik.commons.expection.ErrorException;

/**
 * @author orkun.gedik
 */
public class AuthenticationException extends ErrorException {

  public AuthenticationException(String message) {
    super(AuthenticationErrorType.AUTH_FAIL, message);
  }

  public AuthenticationException(AuthenticationErrorType errorType, String message) {
    super(errorType, message);
  }

}
