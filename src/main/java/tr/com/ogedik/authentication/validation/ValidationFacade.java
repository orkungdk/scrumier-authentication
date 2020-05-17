/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

import java.util.List;

/**
 * @author orkun.gedik
 */
public abstract class ValidationFacade<T> {

  public void validate(T validationRequest, List<Validator<T>> validators) {
    validators.stream().forEach(validator -> validator.validate(validationRequest));
  }
}
