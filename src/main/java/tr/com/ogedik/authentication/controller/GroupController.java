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
import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.GroupService;
import tr.com.ogedik.authentication.util.AuthenticationUtil;
import tr.com.ogedik.commons.util.MetaUtils;

import javax.validation.Valid;

/**
 * @author orkun.gedik
 */
@Controller
@RequestMapping(AuthenticationConstants.Paths.GROUPS)
public class GroupController {

  private static final Logger logger = LogManager.getLogger(GroupController.class);

  @Autowired
  GroupService groupService;

  @GetMapping
  public AuthenticationResponse getGroups() {
    logger.info("The request has been received to return all groups.");
    return AuthenticationResponse.build(groupService.getGroups());
  }

  @GetMapping(AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse getGroup(@PathVariable Long identifier) {
    logger.info("The request has been received to return group with id {}.", identifier);
    return AuthenticationResponse.build(groupService.getGroupById(identifier));
  }

  @PostMapping
  public AuthenticationResponse createGroup(@Valid @RequestBody AuthenticationGroup authenticationGroup,
      @RequestHeader(AuthenticationConstants.Header.AUTH_USER) String authenticatedUsername) {
    logger.info("The request has been received to create a authenticationGroup.");
    MetaUtils.fillMeta(authenticationGroup, authenticatedUsername);
    return AuthenticationResponse.build(groupService.create(authenticationGroup));
  }

  @PutMapping
  public AuthenticationResponse updateGroup(@Valid @RequestBody AuthenticationGroup authenticationGroup,
      @RequestHeader(AuthenticationConstants.Header.AUTH_USER) String authenticatedUsername) {
    logger.info("The request has been received to update {} group.", authenticationGroup.getName());
    MetaUtils.fillMeta(authenticationGroup, authenticatedUsername);
    return AuthenticationResponse.build(groupService.update(authenticationGroup));
  }

  @DeleteMapping(AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse deleteGroup(@PathVariable Long identifier) {
    logger.info("The request has been received to delete group with id {}.", identifier);
    groupService.delete(identifier);

    return AuthenticationResponse.OK();
  }

}
