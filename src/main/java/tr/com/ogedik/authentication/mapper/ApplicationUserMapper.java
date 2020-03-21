/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ogedik.authentication.entity.ApplicationUserEntity;
import tr.com.ogedik.authentication.model.AbstractUser;
import tr.com.ogedik.authentication.model.ApplicationUser;

/**
 * Mapper class for {@link ApplicationUserEntity} and {@link AbstractUser}
 * 
 * @author orkun.gedik
 */
@Service
public class ApplicationUserMapper extends AbstractAuthenticationMapper<ApplicationUserEntity, ApplicationUser> {

  @Autowired
  ApplicationGroupMapper applicationGroupMapper;

  /**
   * Maps from {@link ApplicationUserEntity} to {@link ApplicationUser}
   * 
   * @param entity {@link ApplicationUserEntity}
   * @return {@link ApplicationUser}
   */
  @Override
  public ApplicationUser convert(ApplicationUserEntity entity) {
    if (entity == null) {
      return null;
    }
    return ApplicationUser.builder()
        .resourceId(entity.getResourceId())
        .username(entity.getUsername())
        .enrolmentDate(entity.getEnrolmentDate())
        .groups(applicationGroupMapper.convertToBoList(entity.getGroups()))
        .lastLogonDate(entity.getLastLogonDate())
        .metaInformation(getMetaInformation(entity))
        .role(entity.getRole())
        .password(entity.getPassword())
        .team(entity.getTeam())
        .build();
  }

  /**
   * Maps from {@link ApplicationUser} to {@link ApplicationUserEntity}
   * 
   * @param bo {@link ApplicationUser}
   * @return {@link ApplicationUserEntity}
   */
  @Override
  public ApplicationUserEntity convert(ApplicationUser bo) {
    if (bo == null) {
      return null;
    }
    ApplicationUserEntity entity = new ApplicationUserEntity();
    entity.setTeam(bo.getTeam());
    entity.setRole(bo.getRole());
    entity.setEnrolmentDate(bo.getEnrolmentDate());
    entity.setGroups(applicationGroupMapper.convertToEntityList(bo.getGroups()));
    entity.setLastLogonDate(bo.getLastLogonDate());
    entity.setEnrolmentDate(bo.getEnrolmentDate());
    entity.setUsername(bo.getUsername());
    entity.setPassword(bo.getPassword());
    
    if (bo.getMetaInformation() != null) {
      entity.setUpdatedAt(bo.getMetaInformation().getUpdatedAt());
      entity.setUpdateBy(bo.getMetaInformation().getUpdatedBy());
      entity.setCreatedAt(bo.getMetaInformation().getCreatedAt());
      entity.setCreatedBy(bo.getMetaInformation().getCreatedBy());
      entity.setResourceId(bo.getResourceId());
    }
    return entity;
  }
}
