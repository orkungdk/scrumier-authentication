/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.commons.models.Group;
import tr.com.ogedik.commons.models.User;
import tr.com.ogedik.commons.validation.MandatoryFieldValidator;
import tr.com.ogedik.commons.validation.ValidationFacade;
import tr.com.ogedik.authentication.validation.user.UserCreationValidator;

import java.util.Arrays;

/**
 * @author orkun.gedik
 */
@Component
public class GroupValidationFacade extends ValidationFacade {

  @Autowired
  private MandatoryFieldValidator mandatoryFieldValidator;

  /**
   * Validates {@link UserCreationValidator} and {@link MandatoryFieldValidator}
   *
   * @param group the object of {@link User}
   */
  public void validate(Group group) {
    super.validate(group, Arrays.asList(mandatoryFieldValidator));
  }
}
