/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.service.GroupService;
import tr.com.ogedik.commons.annotations.Authorities;
import tr.com.ogedik.commons.annotations.Restricted;
import tr.com.ogedik.commons.constants.Permission;
import tr.com.ogedik.commons.models.Group;
import tr.com.ogedik.commons.response.AbstractResponse;

/**
 * @author orkun.gedik
 */
@RestController
public class GroupController {

  private static final Logger logger = LogManager.getLogger(GroupController.class);

  @Autowired
  GroupService groupService;

  @GetMapping(AuthenticationConstants.Paths.GROUPS)
  public AbstractResponse getGroups() {
    logger.info("The request has been received to return all groups.");
    return AbstractResponse.build(groupService.getGroups());
  }

  @GetMapping(AuthenticationConstants.Paths.GROUPS + AuthenticationConstants.Paths.IDENTIFIER)
  public AbstractResponse getGroup(@PathVariable Long identifier) {
    logger.info("The request has been received to return group with id {}.", identifier);
    return AbstractResponse.build(groupService.getGroupById(identifier));
  }

  @PostMapping(AuthenticationConstants.Paths.GROUPS)
  public AbstractResponse createGroup(@RequestBody Group group) {
    logger.info("The request has been received to create a group.");
    return AbstractResponse.build(groupService.create(group));
  }

  @PutMapping(AuthenticationConstants.Paths.GROUPS)
  @Restricted(permission =  Permission.ADMIN )
  public AbstractResponse updateGroup(@RequestBody Group group, @RequestHeader(
      name = AuthenticationConstants.Header.AUTHORITIES) @Authorities List<String> authorities) {
    logger.info("The request has been received to update {} group.", group.getName());
    return AbstractResponse.build(groupService.update(group));
  }

  @DeleteMapping(AuthenticationConstants.Paths.GROUPS + AuthenticationConstants.Paths.IDENTIFIER)
  public AbstractResponse deleteGroup(@PathVariable Long identifier) {
    logger.info("The request has been received to delete group with id {}.", identifier);
    groupService.delete(identifier);

    return AbstractResponse.OK();
  }

}
