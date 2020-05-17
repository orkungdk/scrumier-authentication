/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author orkun.gedik
 */
public class AuthenticationException extends RuntimeException {

  public AuthenticationException(String message, String detail) {
    super(getError(message, detail));
  }

  public AuthenticationException(String message) {
    super(message);
  }

  public AuthenticationException(Throwable e) {
    super(e);
  }

  public AuthenticationException(String message, Throwable e) {
    super(getError(message, e.getMessage()));
  }

  private static String getError(String message, String detail) {
    return String.format("Message: %s \n Details: %s", message, detail);
  }
}
