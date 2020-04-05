/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.entity.GroupEntity;
import tr.com.ogedik.commons.models.Group;
import tr.com.ogedik.commons.constants.Permission;

/**
 * @author orkun.gedik
 */
@Service
public class GroupMapper extends AuthenticationMapper<GroupEntity, Group> {

  /**
   * Maps from {@link GroupEntity} to {@link Group}
   *
   * @param entity {@link GroupEntity}
   * @return {@link Group}
   */
  @Override
  public Group convert(GroupEntity entity) {
    return Group.builder()
        .resourceId(entity.getResourceId())
        .description(entity.getDescription())
        .metaInformation(getMetaInformation(entity))
        .name(entity.getName())
        .permissions(entity.getPermissions())
        .build();
  }

  /**
   * Maps from {@link Group} to {@link GroupEntity}
   *
   * @param bo {@link Group}
   * @return {@link GroupEntity}
   */
  @Override
  public GroupEntity convert(Group bo) {
    GroupEntity entity = new GroupEntity();
    entity.setResourceId(bo.getResourceId());
    entity.setDescription(bo.getDescription());
    entity.setName(bo.getName());
    entity.setPermissions(bo.getPermissions());

    if (bo.getMetaInformation() != null) {
      entity.setCreatedAt(bo.getMetaInformation().getCreatedAt());
      entity.setCreatedBy(bo.getMetaInformation().getCreatedBy());
      entity.setUpdatedAt(bo.getMetaInformation().getUpdatedAt());
      entity.setUpdateBy(bo.getMetaInformation().getUpdatedBy());
    }

    return entity;
  }

}
