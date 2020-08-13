package tr.com.ogedik.authentication.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.Permission;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@AllArgsConstructor
public class UserGrantedAuthority implements GrantedAuthority {

  private Permission permission;

  @Override
  public String getAuthority() {
    return permission.name();
  }
}
