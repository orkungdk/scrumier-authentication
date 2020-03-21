/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service;

import tr.com.ogedik.authentication.model.ApplicationGroup;

import java.util.List;

/**
 * @author orkun.gedik
 */
public interface ApplicationGroupService {

  List<ApplicationGroup> getGroups();

  ApplicationGroup getGroupById(Long id);

  ApplicationGroup create(ApplicationGroup applicationGroup);

  ApplicationGroup update(ApplicationGroup applicationGroup);

  void delete(Long id);
}
