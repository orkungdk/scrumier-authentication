/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;

/**
 * This class will extend Spring's AuthenticationEntryPoint class and override its method to commence.
 * It rejects every unauthenticated request and sends error code 401.
 * 
 * @author orkun.gedik
 */
@Component
public class AuthenticationEntrance implements AuthenticationEntryPoint, Serializable {

  @Override
  public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
      AuthenticationException e) throws IOException {
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, AuthenticationConstants.Exception.UNAUTHORIZED);
  }
}
