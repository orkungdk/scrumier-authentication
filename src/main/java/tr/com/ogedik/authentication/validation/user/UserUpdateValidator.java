package tr.com.ogedik.authentication.validation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.commons.expection.ErrorException;
import tr.com.ogedik.commons.validator.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class UserUpdateValidator<T extends AuthenticationUser> implements Validator<T> {

    @Autowired
    private UserService userService;

    @Override
    public void validate(T validationRequest) {
        if (!userService.isExist(validationRequest.getUsername())) {
            throw new ErrorException(AuthenticationErrorType.USER_NOT_FOUND, validationRequest.getUsername());
        }
    }
}
