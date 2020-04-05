/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.util;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import tr.com.ogedik.authentication.model.UserGrantedAuthority;
import tr.com.ogedik.commons.constants.Permission;
import tr.com.ogedik.commons.models.Group;
import tr.com.ogedik.commons.utils.ListUtils;

/**
 * @author orkun.gedik
 */
@UtilityClass
public class AuthenticationUtil {
  private static final Logger logger = LogManager.getLogger(AuthenticationUtil.class);

    /**
     * Returns an list of {@link GrantedAuthority} by given groups
     *
     * @param groups {@link List}<{@link Group}>
     * @return {@link List}<{@link UserGrantedAuthority}>
     */
  public static List<UserGrantedAuthority> getAuthorities(List<Group> groups) {
    try {
      List<Permission> permissions = (List<Permission>)ListUtils.mergeNested(groups, "permissions");
      logger.info("Retrieved permission list: {}", permissions);
      return permissions.stream().map(permission -> new UserGrantedAuthority(permission)).collect(Collectors.toList());
    } catch (IllegalAccessException e) {
      logger.warn("Cannot parse user permissions. Authentication will be provided without authorities");
      return null;
    }
  }
}
