/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.commons.validator.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class UserPasswordValidator<T extends AuthenticationUser> implements Validator<T> {

  @Override
  public void validate(T validationRequest) {
    /*
     * ToDo: After the configuration service is done, this tr.com.ogedik.commons.validator will be implemented The aim will be to validate
     *  password policy
     */
  }
}
