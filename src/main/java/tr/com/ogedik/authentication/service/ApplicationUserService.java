package tr.com.ogedik.authentication.service;

import java.util.List;

import tr.com.ogedik.authentication.model.ApplicationUser;

/**
 * @author orkun.gedik
 */
public interface ApplicationUserService {

  ApplicationUser getUserByUsername(String username);

  List<ApplicationUser> getAllUsers();

  /**
   * Registers a new user
   *
   * @param requestUser {@link ApplicationUser}
   * @return {@link ApplicationUser}
   */
  ApplicationUser create(ApplicationUser requestUser);

  /**
   * Updates the given user
   *
   * @param requestUser {@link ApplicationUser}
   * @return {@link ApplicationUser}
   */
  ApplicationUser update(ApplicationUser requestUser);

  /**
   * Removes the given user
   *
   * @param username {@link String} value of username
   */
  void delete(String username);

}
