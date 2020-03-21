/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import org.springframework.stereotype.Service;
import tr.com.ogedik.authentication.constants.Permission;
import tr.com.ogedik.authentication.entity.ApplicationGroupEntity;
import tr.com.ogedik.authentication.model.ApplicationGroup;

import java.util.stream.Collectors;

/**
 * @author orkun.gedik
 */
@Service
public class ApplicationGroupMapper extends AbstractAuthenticationMapper<ApplicationGroupEntity, ApplicationGroup> {

    /**
     * Maps from {@link ApplicationGroupEntity} to {@link ApplicationGroup}
     *
     * @param entity {@link ApplicationGroupEntity}
     * @return {@link ApplicationGroup}
     */
  @Override
  public ApplicationGroup convert(ApplicationGroupEntity entity) {
    return ApplicationGroup.builder()
        .resourceId(entity.getResourceId())
        .description(entity.getDescription())
        .metaInformation(getMetaInformation(entity))
        .name(entity.getName())
        .permissions(entity.getPermissions()
            .stream()
            .map(permission -> Enum.valueOf(Permission.class, permission))
            .collect(Collectors.toList()))
        .build();
  }

    /**
     * Maps from {@link ApplicationGroup} to {@link ApplicationGroupEntity}
     *
     * @param bo {@link ApplicationGroup}
     * @return {@link ApplicationGroupEntity}
     */
  @Override
  public ApplicationGroupEntity convert(ApplicationGroup bo) {
    ApplicationGroupEntity entity = new ApplicationGroupEntity();
    entity.setResourceId(bo.getResourceId());
    entity.setDescription(bo.getDescription());
    entity.setName(bo.getName());
    entity.setPermissions(bo.getPermissions().stream().map(Permission::name).collect(Collectors.toList()));
    entity.setCreatedAt(bo.getMetaInformation().getCreatedAt());
    entity.setCreatedBy(bo.getMetaInformation().getCreatedBy());
    entity.setUpdatedAt(bo.getMetaInformation().getUpdatedAt());
    entity.setUpdateBy(bo.getMetaInformation().getUpdatedBy());

    return entity;
  }
}
