/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationUser extends AbstractAuthenticationModel {

  @NotNull
  private String username;

  @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
  private String password;

  @NotNull
  private String team;

  private String email;

  private List<AuthenticationGroup> groups;

  private LocalDateTime enrolmentDate;

}
