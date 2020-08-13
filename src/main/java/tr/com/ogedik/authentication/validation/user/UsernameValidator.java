package tr.com.ogedik.authentication.validation.user;

import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.commons.validator.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class UsernameValidator <T extends AuthenticationUser> implements Validator<T> {
    @Override
    public void validate(T validationRequest) {

    }
}
