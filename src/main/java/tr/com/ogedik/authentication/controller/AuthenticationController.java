/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.constants.Permission;
import tr.com.ogedik.authentication.model.AuthenticationRequest;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.AuthenticationService;

/**
 * @author orkun.gedik
 */
@RestController
@CrossOrigin
public class AuthenticationController {

  private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping(AuthenticationConstants.Paths.AUTHENTICATE)
  public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
    logger.info("The request has been received to create authentication token." );
    return AuthenticationResponse.build(authenticationService.authenticate(authenticationRequest));
  }

  @GetMapping(AuthenticationConstants.Paths.PERMISSIONS)
  public AuthenticationResponse getPermissions() {
    logger.info("The request has been received to retrieve list of available permissions.");
    return AuthenticationResponse.build(Permission.class.getEnumConstants());
  }
}
