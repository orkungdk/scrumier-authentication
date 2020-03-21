/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationRequest;

/**
 * @author orkun.gedik
 */
@Component
public class AuthenticationValidationFacade extends ValidationFacade {

  @Autowired
  private MandatoryFieldValidator mandatoryFieldValidator;

  /**
   * Validates {@link MandatoryFieldValidator}
   *
   * @param authenticationRequest the object of {@link AuthenticationRequest}
   */
  public void validate(AuthenticationRequest authenticationRequest) {
    super.validate(authenticationRequest, Arrays.asList(mandatoryFieldValidator));
  }
}
