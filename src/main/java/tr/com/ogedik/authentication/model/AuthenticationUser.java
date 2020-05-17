/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AuthenticationUser extends AbstractAuthenticationPojo {

  @NotNull private String username;

  @NotNull private String password;

  @NotNull private String team;

  private List<AuthenticationGroup> authenticationGroups;

  private LocalDateTime enrolmentDate;

  private LocalDateTime lastLogonDate;

}
