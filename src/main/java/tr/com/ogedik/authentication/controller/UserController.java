package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.authentication.service.UserService;
import tr.com.ogedik.commons.constants.Headers;
import tr.com.ogedik.commons.constants.Services;
import tr.com.ogedik.commons.rest.response.AbstractResponse;
import tr.com.ogedik.commons.util.MetaUtils;
import tr.com.ogedik.commons.rest.AbstractController;

import javax.validation.Valid;

/**
 * @author orkun.gedik
 */
@Controller
@RequestMapping(Services.Path.USERS)
public class UserController extends AbstractController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public AbstractResponse getUsers() {
        logger.info("The request has been received to return all users.");

        return AbstractResponse.build(userService.getAllUsers());
    }

    @GetMapping(Services.Path.IDENTIFIER)
    public AbstractResponse getUser(@PathVariable String identifier) {
        logger.info("The request has been received to return an user with id {}.", identifier);

        return AbstractResponse.build(userService.getUserByUsername(identifier));
    }

    @PostMapping
    public AbstractResponse createUser(
            @Valid @RequestBody AuthenticationUser authenticationUser,
            @RequestHeader(value = Headers.AUTH_USER,
                    defaultValue = Headers.ANONYMOUS) String authenticatedUsername) {
        logger.info("The request has been received to create an user.");
        MetaUtils.fillMeta(authenticationUser, authenticatedUsername);

        return AbstractResponse.build(userService.create(authenticationUser));
    }

    @PutMapping
    public AbstractResponse updateUser(@Valid @RequestBody AuthenticationUser authenticationUser,
                                       @RequestHeader(Headers.AUTH_USER) String authenticatedUsername) {
        logger.info("The request has been received to update an user.");
        MetaUtils.fillMeta(authenticationUser, authenticatedUsername);

        return AbstractResponse.build(userService.update(authenticationUser));
    }

    @DeleteMapping(Services.Path.IDENTIFIER)
    public AbstractResponse deleteUser(@PathVariable String identifier) {
        logger.info("The request has been received to delete an user with id {}.", identifier);
        userService.delete(identifier);

        return AbstractResponse.OK();
    }

}
