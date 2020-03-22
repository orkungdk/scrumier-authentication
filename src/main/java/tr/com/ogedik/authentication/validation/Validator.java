/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

/**
 * @author orkun.gedik
 */
public interface Validator<T> {

    void validate(T validationRequest);
}
