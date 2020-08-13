package tr.com.ogedik.authentication.validation.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.commons.validator.ValidationFacade;

/**
 * @author orkun.gedik
 */
@Component
public class GroupValidationFacade extends ValidationFacade<AuthenticationGroup> {

  @Autowired
  private GroupCreateValidator<AuthenticationGroup> groupCreateValidator;

  @Autowired
  private GroupUpdateValidator<AuthenticationGroup> groupUpdateValidator;

  public void validateCreate(AuthenticationGroup authenticationGroup) {
    super.validate(authenticationGroup, groupCreateValidator);
  }

  public void validateUpdate(AuthenticationGroup authenticationGroup) {
    super.validate(authenticationGroup, groupUpdateValidator);
  }
}
