/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import tr.com.ogedik.authentication.entity.GroupEntity;
import tr.com.ogedik.authentication.model.AuthenticationGroup;

/**
 * @author orkun.gedik
 */
@Mapper(componentModel = "spring")
public abstract class GroupMapper {

  /**
   * Maps from {@link GroupEntity} to {@link AuthenticationGroup}
   *
   * @param entity {@link GroupEntity}
   * @return {@link AuthenticationGroup}
   */
  public abstract AuthenticationGroup convert(GroupEntity entity);

  /**
   * Maps from {@link AuthenticationGroup} to {@link GroupEntity}
   *
   * @param bo {@link AuthenticationGroup}
   * @return {@link GroupEntity}
   */
  public abstract GroupEntity convert(AuthenticationGroup bo);

  /**
   * Maps from List<{@link GroupEntity}> to List<{@link AuthenticationGroup}>
   *
   * @param entities List<{@link GroupEntity}>
   * @return {@link List<AuthenticationGroup}>
   */
  public List<AuthenticationGroup> convert(List<GroupEntity> entities) {
    return entities.stream().map(entity -> convert(entity)).collect(Collectors.toList());
  }
}
