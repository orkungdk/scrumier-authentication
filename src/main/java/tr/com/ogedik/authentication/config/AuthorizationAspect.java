/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import tr.com.ogedik.commons.annotations.Restricted;

/**
 * Authentication aspect configuration
 *
 * @author orkun.gedik
 */
@Aspect
@Configuration
public class AuthorizationAspect {

  @Around("@annotation(restricted)")
  public void authorize(ProceedingJoinPoint point, Restricted restricted) {
    tr.com.ogedik.commons.aspect.AuthorizationAspect.authorize(point, restricted);
  }

}
