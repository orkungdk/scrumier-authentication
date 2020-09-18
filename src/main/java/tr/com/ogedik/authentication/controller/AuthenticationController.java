package tr.com.ogedik.authentication.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tr.com.ogedik.authentication.constants.Permission;
import tr.com.ogedik.authentication.service.AuthenticationService;
import tr.com.ogedik.commons.constants.Services;
import tr.com.ogedik.commons.rest.AbstractController;
import tr.com.ogedik.commons.rest.request.model.AuthenticationRequest;
import tr.com.ogedik.commons.rest.response.AbstractResponse;

/**
 * @author orkun.gedik
 */
@RestController
public class AuthenticationController extends AbstractController {

    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(Services.Path.AUTHENTICATE)
    public AbstractResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("The request has been received to create authentication token.");
        return AbstractResponse.build(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping(Services.Path.VALIDATE)
    public AbstractResponse validate(@RequestBody String token) {
        logger.info("The request has been received to create authentication token.");
        return AbstractResponse.build(authenticationService.validateToken(token));
    }

    @GetMapping(Services.Path.PERMISSIONS)
    public AbstractResponse getPermissions() {
        logger.info("The request has been received to retrieve list of available permissions.");
        return AbstractResponse.build(Permission.class.getEnumConstants());
    }
}
