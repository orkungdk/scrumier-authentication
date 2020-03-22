/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.commons.models.User;
import tr.com.ogedik.commons.validation.MandatoryFieldValidator;
import tr.com.ogedik.commons.validation.ValidationFacade;

import java.util.Arrays;

/**
 * @author orkun.gedik
 */
@Component
public class UserValidationFacade extends ValidationFacade {

  @Autowired
  private UserCreationValidator userCreationValidator;
  @Autowired
  private UserUpdateValidator userUpdateValidator;
  @Autowired
  private MandatoryFieldValidator mandatoryFieldValidator;

  /**
   * Validates {@link UserCreationValidator} and {@link MandatoryFieldValidator}
   * 
   * @param user the object of {@link User}
   */
  public void validateCreate(User user) {
    super.validate(user, Arrays.asList(userCreationValidator, mandatoryFieldValidator));
  }

  /**
   * Validates {@link UserUpdateValidator} and {@link MandatoryFieldValidator}
   *
   * @param user the object of {@link User}
   */
  public void validateUpdate(User user) {
    super.validate(user, Arrays.asList(userUpdateValidator, mandatoryFieldValidator));
  }

}
