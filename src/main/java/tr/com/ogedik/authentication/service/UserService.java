package tr.com.ogedik.authentication.service;

import java.util.List;

import tr.com.ogedik.authentication.model.User;

/**
 * @author orkun.gedik
 */
public interface UserService {

  User getUserByUsername(String username);

  List<User> getAllUsers();

  /**
   * Registers a new user
   *
   * @param requestUser {@link User}
   * @return {@link User}
   */
  User create(User requestUser);

  /**
   * Updates the given user
   *
   * @param requestUser {@link User}
   * @return {@link User}
   */
  User update(User requestUser);

  /**
   * Removes the given user
   *
   * @param username {@link String} value of username
   */
  void delete(String username);

}
