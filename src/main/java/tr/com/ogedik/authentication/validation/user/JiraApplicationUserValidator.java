/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation.user;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.commons.constants.Services;
import tr.com.ogedik.commons.request.model.AuthenticationRequest;
import tr.com.ogedik.commons.request.rest.HttpRestClient;
import tr.com.ogedik.commons.request.rest.helper.RequestURLDetails;
import tr.com.ogedik.commons.validator.Validator;

/**
 * @author orkun.gedik
 */
@Component
public class JiraApplicationUserValidator<T extends AuthenticationUser> implements Validator<T> {

  @Qualifier("eurekaClient")
  @Autowired
  private EurekaClient discoveryClient;

  @Override
  public void validate(T validationRequest) {
    String username = validationRequest.getUsername();
    String password = validationRequest.getPassword();

    InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka(Services.INTEGRATION, false);
    RequestURLDetails requestURLDetails = new RequestURLDetails(instanceInfo.getHomePageUrl(),
        instanceInfo.getVIPAddress(), Services.Path.JIRA_AUTH, null);
    HttpRestClient.doPost(requestURLDetails,
        AuthenticationRequest.builder().username(username).password(password).build(), null);
  }
}
