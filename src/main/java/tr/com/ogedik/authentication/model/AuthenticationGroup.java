/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tr.com.ogedik.authentication.constants.Permission;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationGroup extends AbstractAuthenticationModel {

  @NotNull
  private String name;

  private String description;

  private List<Permission> permissions;

}
