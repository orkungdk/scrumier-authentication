/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.request;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.commons.annotations.Required;
import tr.com.ogedik.commons.models.BusinessObject;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class AuthenticationRequest implements BusinessObject {
  @Required
  private String username;
  @Required
  private String password;

}