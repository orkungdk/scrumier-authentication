/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

import java.util.Arrays;

/**
 * @author orkun.gedik
 */
public abstract class ValidationFacade<T> {

  public void validate(T validationRequest, Validator<T>... validators) {
    Arrays.asList(validators).stream().forEach(validator -> validator.validate(validationRequest));
  }
}
