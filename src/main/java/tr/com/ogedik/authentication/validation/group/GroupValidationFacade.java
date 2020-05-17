/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.group;

import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.validation.ValidationFacade;

/**
 * @author orkun.gedik
 */
@Component
public class GroupValidationFacade extends ValidationFacade<AuthenticationGroup> {

  public void validate(AuthenticationGroup group) {
    // TODO
  }
}
