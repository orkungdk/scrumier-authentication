/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.model.ApplicationGroup;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.validation.MandatoryFieldValidator;
import tr.com.ogedik.authentication.validation.ValidationFacade;
import tr.com.ogedik.authentication.validation.user.ApplicationUserCreationValidator;

import java.util.Arrays;

/**
 * @author orkun.gedik
 */
@Component
public class ApplicationGroupValidationFacade extends ValidationFacade {

  @Autowired
  private MandatoryFieldValidator mandatoryFieldValidator;

  /**
   * Validates {@link ApplicationUserCreationValidator} and {@link MandatoryFieldValidator}
   *
   * @param applicationGroup the object of {@link ApplicationUser}
   */
  public void validate(ApplicationGroup applicationGroup) {
    super.validate(applicationGroup, Arrays.asList(mandatoryFieldValidator));
  }
}
