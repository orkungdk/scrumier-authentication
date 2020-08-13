package tr.com.ogedik.authentication.service;

import java.util.List;

import tr.com.ogedik.authentication.model.AuthenticationGroup;

/**
 * @author orkun.gedik
 */
public interface GroupService {

  boolean isExist(String groupName);

  List<AuthenticationGroup> getGroups();

  AuthenticationGroup getGroupById(Long id);

  AuthenticationGroup create(AuthenticationGroup group);

  AuthenticationGroup update(AuthenticationGroup group);

  void delete(Long id);
}
