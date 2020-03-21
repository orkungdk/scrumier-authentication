/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author orkun.gedik
 */
public class AuthenticationException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(AuthenticationException.class);

    public AuthenticationException(String message) {
        super(message);
        logger.warn("An error has been thrown with the following meessage: {}", message);
    }
}
