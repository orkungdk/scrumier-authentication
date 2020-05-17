/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
public class AuthenticationResponse extends ResponseEntity {

  private AuthenticationResponse(HttpStatus httpStatus) {
    super(httpStatus);
  }

  private AuthenticationResponse(Object body, HttpStatus httpStatus) {
    super(body, httpStatus);
  }

  public static AuthenticationResponse build(Object body) {
    if (ObjectUtils.isEmpty(body)) {
      return new AuthenticationResponse(HttpStatus.BAD_REQUEST);
    } else {
      return new AuthenticationResponse(body, HttpStatus.OK);
    }
  }

  public static AuthenticationResponse build(boolean result) {
    if (result) {
      return new AuthenticationResponse(HttpStatus.OK);
    } else {
      return new AuthenticationResponse(HttpStatus.BAD_REQUEST);
    }
  }

  public static AuthenticationResponse OK() {
    return new AuthenticationResponse(HttpStatus.OK);
  }

  public static AuthenticationResponse KO() {
    return new AuthenticationResponse(HttpStatus.BAD_REQUEST);
  }
}
