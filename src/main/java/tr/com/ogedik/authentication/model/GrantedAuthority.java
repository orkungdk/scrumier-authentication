/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.commons.constants.Permission;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@AllArgsConstructor
public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {

  private Permission permission;

  @Override
  public String getAuthority() {
    return permission.name();
  }
}
