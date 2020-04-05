/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.commons.models.BusinessObject;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class Authentication implements org.springframework.security.core.Authentication, BusinessObject {

  @NotNull
  private String principal;

  @NotNull
  private String token;

  private List<UserGrantedAuthority> authorities;

  private boolean isAuthenticated;
  
  private String credentials;

  /**
   * The following methods are created for inheritance purposes. These are not used any place in the application.
   */
  @Override
  public Object getDetails() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }
}
