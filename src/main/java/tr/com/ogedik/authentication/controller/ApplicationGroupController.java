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
import tr.com.ogedik.authentication.model.ApplicationGroup;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.response.AuthenticationResponse;
import tr.com.ogedik.authentication.service.ApplicationGroupService;

/**
 * @author orkun.gedik
 */
@RestController
public class ApplicationGroupController {

  @Autowired
  ApplicationGroupService applicationGroupService;

  @GetMapping(AuthenticationConstants.Paths.GROUPS)
  public AuthenticationResponse getGroups() {
    return AuthenticationResponse.build(applicationGroupService.getGroups());
  }

  @GetMapping(AuthenticationConstants.Paths.GROUPS + AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse getGroup(@PathVariable Long identifier) {
    return AuthenticationResponse.build(applicationGroupService.getGroupById(identifier));
  }

  @PostMapping(AuthenticationConstants.Paths.GROUPS)
  public AuthenticationResponse createGroup(@RequestBody ApplicationGroup applicationGroup) {
    return AuthenticationResponse.build(applicationGroupService.create(applicationGroup));
  }

  @PutMapping(AuthenticationConstants.Paths.GROUPS)
  public AuthenticationResponse updateGroup(@RequestBody ApplicationGroup applicationGroup) {
    return AuthenticationResponse.build(applicationGroupService.update(applicationGroup));
  }

  @DeleteMapping(AuthenticationConstants.Paths.GROUPS + AuthenticationConstants.Paths.IDENTIFIER)
  public AuthenticationResponse deleteGroup(@PathVariable Long identifier) {
    applicationGroupService.delete(identifier);

    return AuthenticationResponse.OK();
  }

}
