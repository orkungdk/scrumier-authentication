package tr.com.ogedik.authentication.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import tr.com.ogedik.authentication.model.AuthenticationUser;

/**
 * @author orkun.gedik
 */
public interface UserService extends UserDetailsService {

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
   * @return found {@link AuthenticationUser}
   */
  AuthenticationUser getUserByUsername(String username);

  /**
   * Returns all users
   *
   * @return List of {@link AuthenticationUser}
   */
  List<AuthenticationUser> getAllUsers();

  /**
   * Registers a new user
   *
   * @param requestUser {@link AuthenticationUser}
   * @return {@link AuthenticationUser}
   */
  AuthenticationUser create(AuthenticationUser requestUser);

  /**
   * Updates the given user
   *
   * @param requestUser {@link AuthenticationUser}
   * @return {@link AuthenticationUser}
   */
  AuthenticationUser update(AuthenticationUser requestUser);

  /**
   * Removes the given user
   *
   * @param username {@link String} value of username
   */
  void delete(String username);

}
