/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class Authentication extends AbstractUser {

  @NotNull
  private String username;
  @NotNull
  private String token;
}
