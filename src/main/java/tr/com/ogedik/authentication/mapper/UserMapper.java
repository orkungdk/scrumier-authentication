/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import tr.com.ogedik.authentication.entity.UserEntity;
import tr.com.ogedik.authentication.model.AuthenticationUser;

/**
 * Mapper class for {@link UserEntity} and {@link AuthenticationUser}
 * 
 * @author orkun.gedik
 */
@Mapper(componentModel = "spring", uses = { GroupMapper.class , MetaMapper.class })
public abstract class UserMapper {

  @Autowired
  public PasswordEncoder passwordEncoder;

  /**
   * Maps from {@link UserEntity} to {@link AuthenticationUser}
   *
   * @param entity {@link UserEntity}
   * @return {@link AuthenticationUser}
   */
  public abstract AuthenticationUser convert(UserEntity entity);

  /**
   * Maps from {@link AuthenticationUser} to {@link UserEntity}
   *
   * @param user {@link AuthenticationUser}
   * @return {@link UserEntity}
   */
  @Mapping(target = "password", ignore = true)
  public abstract UserEntity convert(AuthenticationUser user);

  /**
   * Maps from List<{@link UserEntity}> to List<{@link AuthenticationUser}>
   *
   * @param entities List<{@link UserEntity}>
   * @return {@link List<AuthenticationUser}>
   */
  public List<AuthenticationUser> convert(List<UserEntity> entities) {
    return entities.stream().map(entity -> convert(entity)).collect(Collectors.toList());
  }

  @AfterMapping
  public void encodePassword(AuthenticationUser user, @MappingTarget UserEntity entity) {
    entity.setPassword(passwordEncoder.encode(user.getPassword()));
  }
}
