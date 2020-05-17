/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.GroupService;

/**
 * @author orkun.gedik
 */
@RestController
public class GroupController {

  private static final Logger logger = LogManager.getLogger(GroupController.class);

  @Autowired
  GroupService groupService;

  @GetMapping(AuthenticationConstants.Paths.GROUPS)
  public AuthenticationResponse getGroups() {
    logger.info("The request has been received to return all groups.");
    return AuthenticationResponse.build(groupService.getGroups());
  }

  @GetMapping(AuthenticationConstants.Paths.GROUPS + AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse getGroup(@PathVariable Long identifier) {
    logger.info("The request has been received to return group with id {}.", identifier);
    return AuthenticationResponse.build(groupService.getGroupById(identifier));
  }

  @PostMapping(AuthenticationConstants.Paths.GROUPS)
  public AuthenticationResponse createGroup(@RequestBody AuthenticationGroup group) {
    logger.info("The request has been received to create a group.");
    return AuthenticationResponse.build(groupService.create(group));
  }

  @PutMapping(AuthenticationConstants.Paths.GROUPS)
  public AuthenticationResponse updateGroup(@RequestBody AuthenticationGroup group) {
    logger.info("The request has been received to update {} group.", group.getName());
    return AuthenticationResponse.build(groupService.update(group));
  }

  @DeleteMapping(AuthenticationConstants.Paths.GROUPS + AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse deleteGroup(@PathVariable Long identifier) {
    logger.info("The request has been received to delete group with id {}.", identifier);
    groupService.delete(identifier);

    return AuthenticationResponse.OK();
  }

}
