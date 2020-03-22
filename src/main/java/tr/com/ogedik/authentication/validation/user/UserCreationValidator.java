/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.User;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.validation.Validator;
import tr.com.ogedik.commons.models.AbstractUser;
import tr.com.ogedik.commons.models.BusinessObject;

/**
 * @author orkun.gedik
 */
@Component
public class UserCreationValidator<T extends AbstractUser> implements Validator<T> {

    @Autowired
    private UserService userService;

  @Override
  public void validate(T validationRequest) {
      User user = userService.getUserByUsername(validationRequest.getUsername());
      if (user != null) {
          throw new AuthenticationException(AuthenticationConstants.Exception.USER_EXIST);
      }
  }
}
