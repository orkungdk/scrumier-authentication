/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.request;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class AuthenticationRequest {
  @NotNull
  private String username;
  @NotNull
  private String password;

}