/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.validation.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class JiraApplicationUserValidator<T extends AuthenticationUser> implements Validator<T> {
  @Override
  public void validate(T validationRequest) {
    /*
     * ToDo: After the integration with Jira is done, implement this validator.
     *  The aim will be to validate user is also exist with same username in Jira
     */
  }
}
