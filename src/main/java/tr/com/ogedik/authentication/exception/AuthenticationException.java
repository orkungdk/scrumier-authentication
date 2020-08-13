package tr.com.ogedik.authentication.exception;

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
