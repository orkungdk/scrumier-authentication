/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

import java.util.List;

import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.model.BusinessObject;

/**
 * Abstract class for all validation facade
 *
 * @implNote each validation facade should extend from this class.
 * @author orkun.gedik
 */
@Component
public abstract class ValidationFacade<T extends BusinessObject> {

  public void validate(T validationRequest, List<Validator<T>> validators) {
    validators.stream().forEach(validator -> validator.validate(validationRequest));
  }

}
