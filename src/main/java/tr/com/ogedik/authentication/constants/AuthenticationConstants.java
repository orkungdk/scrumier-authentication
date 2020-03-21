/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.constants;

/**
 * @author orkun.gedik
 */
public class AuthenticationConstants {

  public static class Qualifier {
    public static final String AUTH_MANAGER = "WorklogTrackerAuthenticationManager";
  }

  public static class Exception {
    public static final String AUTH_FAIL = "Authentication failed. ";
    public static final String USER_DISABLED = AUTH_FAIL + "User has been disabled.";
    public static final String INVALID_CREDENTIALS = AUTH_FAIL + "Invalid credentials.";
    public static final String UNABLE_GET_TOKEN = "Unable to get token.";
    public static final String TOKEN_EXPIRED = "Token has expired.";
    public static final String UNAUTHORIZED = "Unauthorized request.";
    public static final String USER_NOT_FOUND = "User record cannot be found.";
    public static final String USER_EXIST = "User is already exist.";

    public static final String GROUP_NOT_FOUND = "Group record cannot be found.";
  }

  public static class Header {
    public static final String AUTH = "Authorization";
  }

  public static class Paths {
    public static final String AUTHENTICATE = "/authenticate";
    public static final String USERS = "/users";
    public static final String GROUPS = "/groups";
    public static final String IDENTIFIER = "/{identifier}";
      public static final String ROLES = "/roles";
  }

  public static class Entity {
    public static final String APPLICATION_USER = "APP_USER";
    public static final String APPLICATION_GROUP = "APP_GROUP";
  }
}
