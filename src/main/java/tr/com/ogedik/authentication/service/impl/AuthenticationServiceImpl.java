package tr.com.ogedik.authentication.service.impl;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.model.AuthenticationDetails;
import tr.com.ogedik.authentication.service.AuthenticationService;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.commons.expection.ErrorException;
import tr.com.ogedik.commons.helper.TokenHelper;
import tr.com.ogedik.commons.rest.request.model.AuthenticationRequest;

/**
 * @author orkun.gedik
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    /**
     * Authenticates user after validating the request
     *
     * @param authenticationRequest the object of {@link AuthenticationRequest}
     * @return {@link AuthenticationDetails} with the generated JWT token
     * @throws ErrorException if user is not exist / disabled or the credentials are incorrect
     */
    public AuthenticationDetails authenticate(AuthenticationRequest authenticationRequest) {

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword());
            AuthenticationDetails authenticationDetails = (AuthenticationDetails) authenticationManager
                    .authenticate(authenticationToken);
            authenticationDetails.setToken(generateToken(authenticationRequest, authenticationDetails));

            return authenticationDetails;
        } catch (DisabledException e) {
            throw new ErrorException(AuthenticationErrorType.USER_DISABLED, "User is not active!");
        } catch (BadCredentialsException e) {
            throw new ErrorException(AuthenticationErrorType.INVALID_CREDENTIALS, "Incorrect username or password!");
        }
    }

    @Override
    public String validateToken(String token) {
        Boolean isTokenExpired = TokenHelper.isTokenExpired(token);

        if (BooleanUtils.isFalse(isTokenExpired)) {
            String username = TokenHelper.getUsernameFromToken(token);
            if (userService.isExist(username)) {
                return username;
            } else {
                throw new ErrorException(AuthenticationErrorType.USER_NOT_FOUND, "Please authenticate again.");
            }
        } else {
            throw new ErrorException(AuthenticationErrorType.TOKEN_EXPIRED, "Please authenticate again.");
        }
    }

    /**
     * Generates authentication token
     *
     * @param authenticationRequest the object of {@link AuthenticationRequest}
     * @param authenticationDetails the object of {@link AuthenticationDetails}
     * @return generated authentication token
     */
    private String generateToken(AuthenticationRequest authenticationRequest,
                                 AuthenticationDetails authenticationDetails) {
        if (authenticationDetails.isAuthenticated()) {
            final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

            return TokenHelper.generateToken(userDetails.getUsername());
        } else {
            throw new ErrorException(AuthenticationErrorType.AUTH_FAIL, "User cannot be authenticated.");
        }
    }
}
