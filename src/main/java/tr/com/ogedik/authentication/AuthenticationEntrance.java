package tr.com.ogedik.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

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
    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, AuthenticationErrorType.UNAUTHORIZED.getTitle());
  }
}
