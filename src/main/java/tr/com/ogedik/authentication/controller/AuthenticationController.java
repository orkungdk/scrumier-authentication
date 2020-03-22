/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.constants.Role;
import tr.com.ogedik.authentication.request.AuthenticationRequest;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.AuthenticationService;
import tr.com.ogedik.commons.annotations.Restricted;
import tr.com.ogedik.commons.constants.Permission;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
  public ResponseEntity createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) {
    logger.info("The request has been received to create authentication token." );
    return AuthenticationResponse.build(authenticationService.authenticate(authenticationRequest));
  }

  @GetMapping(AuthenticationConstants.Paths.ROLES)
  public ResponseEntity getRoles() {
    logger.info("The request has been received to return all roles." );
    List<String> roles = Arrays.asList(Role.values()).stream().map(Role::toString).collect(Collectors.toList());

    return AuthenticationResponse.build(roles);
  }
}
