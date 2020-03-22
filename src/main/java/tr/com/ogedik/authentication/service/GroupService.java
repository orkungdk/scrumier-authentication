/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service;

import tr.com.ogedik.commons.models.Group;

import java.util.List;

/**
 * @author orkun.gedik
 */
public interface GroupService {

  List<Group> getGroups();

  Group getGroupById(Long id);

  Group create(Group group);

  Group update(Group group);

  void delete(Long id);
}
