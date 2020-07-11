/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.commons.validator.MandatoryFieldValidator;
import tr.com.ogedik.commons.validator.ValidationFacade;

/**
 * @author orkun.gedik
 */
@Component
public class UserValidationFacade extends ValidationFacade<AuthenticationUser> {

  @Autowired
  private UserCreationValidator userCreationValidator;
  @Autowired
  private UserUpdateValidator userUpdateValidator;
  @Autowired
  private JiraApplicationUserValidator jiraApplicationUserValidator;
  @Autowired
  private UsernameValidator usernameValidator;
  @Autowired
  private UserPasswordValidator userPasswordValidator;

  /**
   * Validates {@link UserCreationValidator}
   * 
   * @param authenticationUser the object of {@link AuthenticationUser}
   */
  public void validateCreate(AuthenticationUser authenticationUser) {
    super.validate(authenticationUser, MandatoryFieldValidator.getInstance(), userCreationValidator,
        jiraApplicationUserValidator, usernameValidator, userPasswordValidator);
  }

  /**
   * Validates {@link UserUpdateValidator}
   *
   * @param authenticationUser the object of {@link AuthenticationUser}
   */
  public void validateUpdate(AuthenticationUser authenticationUser) {
    super.validate(authenticationUser, MandatoryFieldValidator.getInstance(), userUpdateValidator, usernameValidator);
  }
}
