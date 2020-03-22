/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.entity.UserEntity;
import tr.com.ogedik.commons.models.User;

/**
 * Mapper class for {@link UserEntity} and {@link tr.com.ogedik.commons.models.User}
 * 
 * @author orkun.gedik
 */
@Service
public class UserMapper extends AuthenticationMapper<UserEntity, User> {

  @Autowired
  private GroupMapper groupMapper;

  /**
   * Maps from {@link UserEntity} to {@link User}
   * 
   * @param entity {@link UserEntity}
   * @return {@link User}
   */
  @Override
  public User convert(UserEntity entity) {
    if (entity == null) {
      return null;
    }
    return User.builder()
        .resourceId(entity.getResourceId())
        .username(entity.getUsername())
        .enrolmentDate(entity.getEnrolmentDate())
        .groups(groupMapper.convertToBoList(entity.getGroups()))
        .lastLogonDate(entity.getLastLogonDate())
        .metaInformation(getMetaInformation(entity))
        .role(entity.getRole())
        .password(entity.getPassword())
        .team(entity.getTeam())
        .build();
  }

  /**
   * Maps from {@link User} to {@link UserEntity}
   *
   * @param bo {@link User}
   * @return {@link UserEntity}
   */
  @Override
  public UserEntity convert(User bo) {
    if (bo == null) {
      return null;
    }
    UserEntity entity = new UserEntity();
    entity.setTeam(bo.getTeam());
    entity.setRole(bo.getRole());
    entity.setEnrolmentDate(bo.getEnrolmentDate());
    entity.setGroups(groupMapper.convertToEntityList(bo.getGroups()));
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
