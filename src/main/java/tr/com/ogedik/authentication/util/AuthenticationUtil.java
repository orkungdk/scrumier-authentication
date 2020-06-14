/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;

import lombok.experimental.UtilityClass;
import tr.com.ogedik.authentication.constants.Permission;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AbstractAuthenticationModel;
import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.model.MetaInformation;
import tr.com.ogedik.authentication.model.UserGrantedAuthority;

/**
 * @author orkun.gedik
 */
@UtilityClass
public class AuthenticationUtil {
  private static final Logger logger = LogManager.getLogger(AuthenticationUtil.class);

    /**
     * Returns an list of {@link GrantedAuthority} by given groups
     *
     * @param groups {@link List}<{@link tr.com.ogedik.authentication.model.AuthenticationGroup}>
     * @return {@link List}<{@link UserGrantedAuthority}>
     */
  public static List<UserGrantedAuthority> getAuthorities(List<AuthenticationGroup> groups) {
    try {
      List<Permission> permissions = (List<Permission>)ListUtils.mergeNested(groups, "permissions");
      logger.info("Retrieved permission list: {}", permissions);
      return permissions.stream().map(permission -> new UserGrantedAuthority(permission)).collect(Collectors.toList());
    } catch (IllegalAccessException e) {
      logger.warn("Cannot parse user permissions. Authentication will be provided without authorities");
      return null;
    }
  }

  /**
   * Fills meta information of the given authentication model
   *
   * @param model to be filled {@link AbstractAuthenticationModel}
   * @param authenticatedUsername request owner
   */
  public static void fillMeta(AbstractAuthenticationModel model, String authenticatedUsername) {
    if (model == null) {
      throw new AuthenticationException("Request Body must not be null.");
    }

    if (model.getMetaInformation() == null) {
      model.setMetaInformation(new MetaInformation());
    }

    if (model.getMetaInformation().getCreatedAt() == null) {
      model.getMetaInformation().setCreatedAt(LocalDateTime.now());
      model.getMetaInformation().setCreatedBy(authenticatedUsername);
    } else {
      model.getMetaInformation().setUpdatedAt(LocalDateTime.now());
      model.getMetaInformation().setUpdatedBy(authenticatedUsername);
    }
  }
}
