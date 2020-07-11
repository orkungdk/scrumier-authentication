/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AuthenticationDetails;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.commons.helper.TokenHelper;

/**
 * @author orkun.gedik
 */
@Component
public class AuthenticationRequestFilter extends OncePerRequestFilter {

  private static final Logger logger = LogManager.getLogger(AuthenticationRequestFilter.class);

  @Autowired
  private UserService userService;

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    logger.info("Request session ({}) is being filtering...", request.getRequestedSessionId());
    AuthenticationDetails authenticationDetails = createAuthenticatedUser(request);

    // Once we get the token validate it.
    if (authenticationDetails.getPrincipal() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userService.loadUserByUsername(authenticationDetails.getPrincipal());

      // if token is valid configure Spring Security to manually set authentication
      if (TokenHelper.validateToken(authenticationDetails.getToken(), userDetails.getUsername())) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        /*
         * After setting the Authentication in the context, we specify that the current user is authenticated. So it
         * passes the Spring Security Configurations successfully
         */
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }

    /**
     * Returns an user with parsing token from request header.
     *
     * @param request {@link HttpServletRequest}
     * @return {@link AuthenticationDetails}
     * @throws AuthenticationException if the token cannot be parsed or the token has expired
     */
  private AuthenticationDetails createAuthenticatedUser(HttpServletRequest request) {
    // Return an empty user if request header is empty or does not contain token.
    String requestTokenHeader = request.getHeader(AuthenticationConstants.Header.AUTH_TOKEN);
    if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
      logger.warn("Token is empty or does not begin with Bearer String");
      return AuthenticationDetails.builder().build();
    }

    try {
      // Token is in the form "Bearer token". Remove Bearer word and get only the Token
      String token = requestTokenHeader.substring(7);
      String username = request.getHeader(AuthenticationConstants.Header.AUTH_USER);

      return AuthenticationDetails.builder().principal(username).token(token).build();
    } catch (IllegalArgumentException e) {
      throw new AuthenticationException(AuthenticationErrorType.UNABLE_GET_TOKEN, "Token cannot be parsed!");
    } catch (ExpiredJwtException e) {
      throw new AuthenticationException(AuthenticationErrorType.TOKEN_EXPIRED, "Expired token!");
    }
  }
}
