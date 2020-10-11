package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.service.GroupService;
import tr.com.ogedik.commons.constants.Headers;
import tr.com.ogedik.commons.constants.Services;
import tr.com.ogedik.commons.rest.AbstractController;
import tr.com.ogedik.commons.rest.response.AbstractResponse;
import tr.com.ogedik.commons.util.MetaUtils;

/**
 * @author orkun.gedik
 */
@Controller
@RequestMapping(Services.Path.GROUPS)
public class GroupController extends AbstractController {

    private static final Logger logger = LogManager.getLogger(GroupController.class);

    @Autowired
    private GroupService groupService;

    @GetMapping
    public AbstractResponse getGroups() {
        logger.info("The request has been received to return all groups.");
        return AbstractResponse.build(groupService.getGroups());
    }

    @GetMapping(Services.Path.IDENTIFIER)
    public AbstractResponse getGroup(@PathVariable Long identifier) {
        logger.info("The request has been received to return group with id {}.", identifier);
        return AbstractResponse.build(groupService.getGroupById(identifier));
    }

    @PostMapping
    public AbstractResponse createGroup(@RequestBody AuthenticationGroup authenticationGroup,
                                        @RequestHeader(Headers.AUTH_USER) String authenticatedUsername) {
        logger.info("The request has been received to create a authenticationGroup.");
        MetaUtils.fillMeta(authenticationGroup, authenticatedUsername);
        return AbstractResponse.build(groupService.create(authenticationGroup));
    }

    @PutMapping
    public AbstractResponse updateGroup(@RequestBody AuthenticationGroup authenticationGroup,
                                        @RequestHeader(Headers.AUTH_USER) String authenticatedUsername) {
        logger.info("The request has been received to update {} group.", authenticationGroup.getName());
        MetaUtils.fillMeta(authenticationGroup, authenticatedUsername);
        return AbstractResponse.build(groupService.update(authenticationGroup));
    }

    @DeleteMapping(Services.Path.IDENTIFIER)
    public AbstractResponse deleteGroup(@PathVariable Long identifier) {
        logger.info("The request has been received to delete group with id {}.", identifier);
        groupService.delete(identifier);

        return AbstractResponse.OK();
    }

}
