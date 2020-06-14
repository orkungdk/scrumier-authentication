/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.group;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.service.GroupService;
import tr.com.ogedik.authentication.validation.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class GroupCreateValidator <T extends AuthenticationGroup> implements Validator<T> {

    @Autowired
    private GroupService groupService;

    @Override
    public void validate(T validationRequest) {
        if (groupService.isExist(validationRequest.getName())) {
            throw new AuthenticationException(AuthenticationConstants.Exception.GROUP_EXIST);
        }
    }
}
