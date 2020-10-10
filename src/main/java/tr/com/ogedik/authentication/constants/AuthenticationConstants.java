package tr.com.ogedik.authentication.constants;

import lombok.experimental.UtilityClass;

/**
 * @author orkun.gedik
 */
@UtilityClass
public class AuthenticationConstants {

    public static class Header {
        public static final String AUTH_TOKEN = "X-Auth-Token";
        public static final String AUTH_USER = "X-Auth-User";
        public static final String ANONYMOUS = "Anonymous";
    }

    public static class Entity {
        public static final String APPLICATION_USER = "APP_USER";
        public static final String APPLICATION_GROUP = "APP_GROUP";
    }

    public static class COLS {
        public static final String USERNAME = "username";
        public static final String DISPLAY_NAME = "displayName";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String TEAM = "team";
        public static final String ENROLMENT_DATE = "enrolmentDate";
        public static final String LAST_LOGIN_DATE = "lastLoginDate";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String GROUPS = "groups";
        public static final String PERMISSIONS = "permissions";
        public static final String AVATAR_URL = "avatarUrl";
    }
}
