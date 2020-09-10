package tr.com.ogedik.authentication.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.com.ogedik.authentication.controller.AuthenticationController;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.model.AuthenticationDetails;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.util.AuthenticationUtil;
import tr.com.ogedik.commons.expection.ErrorException;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author orkun.gedik
 */
@Service
@Primary
public class AuthenticationManagerImpl implements AuthenticationManager {

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationDetails authenticate(Authentication authentication) throws ErrorException {
        AuthenticationUser user = userService.getUserByUsername(authentication.getPrincipal().toString());

        if (Objects.isNull(user)) {
            throw new ErrorException(AuthenticationErrorType.USER_NOT_FOUND,
                    "User not found for : " + authentication.getPrincipal().toString());
        }
        if (!passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword())) {
            throw new ErrorException(AuthenticationErrorType.INVALID_CREDENTIALS);
        }

        logger.info("Username and password validations are OK. Authentication is being initialized.");

        // Update last login date on the moment that user has been authenticated
        user.setLastLoginDate(LocalDateTime.now());
        userService.update(user);


        return AuthenticationDetails.builder()
                .authorities(AuthenticationUtil.getAuthorities(user.getGroups()))
                .principal(user.getUsername())
                .credentials(user.getPassword())
                .details(user)
                .isAuthenticated(true)
                .build();
    }

}
