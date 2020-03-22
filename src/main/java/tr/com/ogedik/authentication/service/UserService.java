package tr.com.ogedik.authentication.service;

import java.util.List;

import tr.com.ogedik.commons.models.User;

/**
 * @author orkun.gedik
 */
public interface UserService {

  /**
   * Checks whether user is exist or not
   *
   * @param username value to search user
   * @return {@code true} iff user is exist
   */
  boolean isExist(String username);

  /**
   * Returns user by given username
   *
   * @param username value to retrieve user from database
   * @return found {@link User}
   */
  User getUserByUsername(String username);

  /**
   * ToDo : Add pagination
   * Returns all users
   *
   * @return List of {@link User}
   */
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
