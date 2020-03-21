/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

import tr.com.ogedik.authentication.model.BusinessObject;

/**
 * @author orkun.gedik
 */
public interface Validator<T extends BusinessObject> {

    void validate(T validationRequest);
}
