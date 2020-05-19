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
public class UsernameValidator <T extends AuthenticationUser> implements Validator<T> {
    @Override
    public void validate(T validationRequest) {
        /*
         * ToDo: After the configuration service is done, this validator will be implemented
         *  The aim will be to validate username length, character, etc....
         */
    }
}
