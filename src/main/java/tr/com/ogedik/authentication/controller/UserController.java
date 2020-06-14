/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.authentication.util.AuthenticationUtil;

import javax.validation.Valid;

/**
 * @author orkun.gedik
 */
@Controller
@RequestMapping(AuthenticationConstants.Paths.USERS)
public class UserController {

  private static final Logger logger = LogManager.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @GetMapping
  public AuthenticationResponse getUsers() {
    logger.info("The request has been received to return all users.");

    return AuthenticationResponse.build(userService.getAllUsers());
  }

  @GetMapping(AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse getUser(@PathVariable String identifier) {
    logger.info("The request has been received to return an user with id {}.", identifier);

    return AuthenticationResponse.build(userService.getUserByUsername(identifier));
  }

  /*
   * ToDo: check config to validate users can be created without authentication
   */
  @PostMapping
  public AuthenticationResponse createUser(
      @Valid @RequestBody AuthenticationUser authenticationUser,
      @RequestHeader(value = AuthenticationConstants.Header.AUTH_USER,
              defaultValue = AuthenticationConstants.Header.ANONYMOUS) String authenticatedUsername) {
    logger.info("The request has been received to create an user.");
    AuthenticationUtil.fillMeta(authenticationUser, authenticatedUsername);

    return AuthenticationResponse.build(userService.create(authenticationUser));
  }

  @PutMapping
  public AuthenticationResponse updateUser(@Valid @RequestBody AuthenticationUser authenticationUser,
      @RequestHeader(AuthenticationConstants.Header.AUTH_USER) String authenticatedUsername) {
    logger.info("The request has been received to update an user.");
    AuthenticationUtil.fillMeta(authenticationUser, authenticatedUsername);

    return AuthenticationResponse.build(userService.update(authenticationUser));
  }

  @DeleteMapping(AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse deleteUser(@PathVariable String identifier) {
    logger.info("The request has been received to delete an user with id {}.", identifier);
    userService.delete(identifier);

    return AuthenticationResponse.OK();
  }

}
