/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.ApplicationUserService;

/**
 * @author orkun.gedik
 */
@RestController
public class ApplicationUserController {

  @Autowired
  private ApplicationUserService applicationUserService;

  @GetMapping(AuthenticationConstants.Paths.USERS)
  public AuthenticationResponse getUsers() {
    return AuthenticationResponse.build(applicationUserService.getAllUsers());
  }

  @GetMapping(AuthenticationConstants.Paths.USERS + AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse getUser(@PathVariable String identifier) {
    return AuthenticationResponse.build(applicationUserService.getUserByUsername(identifier));
  }

  @PostMapping(AuthenticationConstants.Paths.USERS)
  public AuthenticationResponse createUser(@RequestBody ApplicationUser requestUser) {
    return AuthenticationResponse.build(applicationUserService.create(requestUser));
  }

  @PutMapping(AuthenticationConstants.Paths.USERS)
  public AuthenticationResponse updateUser(@RequestBody ApplicationUser requestUser) {
    return AuthenticationResponse.build(applicationUserService.update(requestUser));
  }

  @DeleteMapping(AuthenticationConstants.Paths.USERS + AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse deleteUser(@PathVariable String identifier) {
    applicationUserService.delete(identifier);

    return AuthenticationResponse.OK();
  }

}
