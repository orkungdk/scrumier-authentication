/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.service.ApplicationUserDetailsService;
import tr.com.ogedik.authentication.service.ApplicationUserService;

import java.util.Arrays;

/**
 * @author orkun.gedik
 */
@Service
public class ApplicationUserDetailsServiceImpl implements ApplicationUserDetailsService {
    private static final Logger logger = LogManager.getLogger(ApplicationUserDetailsServiceImpl.class);

    @Autowired
    private ApplicationUserService applicationUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserService.getUserByUsername(username);
        
        if (applicationUser == null) {
            logger.warn("ApplicationUser cannot be found in database. Username is {}", username);
            throw new AuthenticationException(AuthenticationConstants.Exception.USER_NOT_FOUND);
        }
        
    return User.builder()
        .username(applicationUser.getUsername())
        .password(applicationUser.getPassword())
        .authorities(Arrays.asList(new SimpleGrantedAuthority(applicationUser.getRole())))
        .build();
    }
}
