/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AbstractUser;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.service.ApplicationUserService;
import tr.com.ogedik.authentication.validation.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class ApplicationUserCreationValidator<T extends AbstractUser> implements Validator<T> {

    @Autowired
    private ApplicationUserService applicationUserService;

  @Override
  public void validate(T validationRequest) {
      ApplicationUser user = applicationUserService.getUserByUsername(validationRequest.getUsername());
      if (user != null) {
          throw new AuthenticationException(AuthenticationConstants.Exception.USER_EXIST);
      }
  }
}
