package tr.com.ogedik.authentication.service;

import tr.com.ogedik.authentication.model.AuthenticationGroup;

import java.util.List;

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
