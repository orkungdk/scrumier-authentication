/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.validation.MandatoryFieldValidator;
import tr.com.ogedik.authentication.validation.ValidationFacade;

import java.util.Arrays;

/**
 * @author orkun.gedik
 */
@Component
public class ApplicationUserValidationFacade extends ValidationFacade {

  @Autowired
  private ApplicationUserCreationValidator applicationUserCreationValidator;
  @Autowired
  private ApplicationUserUpdateValidator applicationUserUpdateValidator;
  @Autowired
  private MandatoryFieldValidator mandatoryFieldValidator;

  /**
   * Validates {@link ApplicationUserCreationValidator} and {@link MandatoryFieldValidator}
   * 
   * @param applicationUser the object of {@link ApplicationUser}
   */
  public void validateCreate(ApplicationUser applicationUser) {
    super.validate(applicationUser, Arrays.asList(applicationUserCreationValidator, mandatoryFieldValidator));
  }

  /**
   * Validates {@link ApplicationUserUpdateValidator} and {@link MandatoryFieldValidator}
   *
   * @param applicationUser the object of {@link ApplicationUser}
   */
  public void validateUpdate(ApplicationUser applicationUser) {
    super.validate(applicationUser, Arrays.asList(applicationUserUpdateValidator, mandatoryFieldValidator));
  }

}
