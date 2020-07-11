/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import tr.com.ogedik.authentication.entity.UserEntity;
import tr.com.ogedik.authentication.model.AuthenticationUser;
import tr.com.ogedik.commons.mapper.AbstractBoMapper;

/**
 * Mapper class for {@link UserEntity} and {@link AuthenticationUser}
 * 
 * @author orkun.gedik
 */
@Mapper(componentModel = "spring", uses = { GroupMapper.class })
public abstract class UserMapper extends AbstractBoMapper<AuthenticationUser, UserEntity> {

  @Autowired
  public PasswordEncoder passwordEncoder;

  /**
   * Maps from {@link UserEntity} to {@link AuthenticationUser}
   *
   * @param entity {@link UserEntity}
   * @return {@link AuthenticationUser}
   */
  @Override
  public abstract AuthenticationUser convert(UserEntity entity);

  /**
   * Maps from {@link AuthenticationUser} to {@link UserEntity}
   *
   * @param user {@link AuthenticationUser}
   * @return {@link UserEntity}
   */
  @Override
  @Mapping(target = "password", ignore = true)
  public abstract UserEntity convert(AuthenticationUser user);

  @AfterMapping
  public void encodePassword(AuthenticationUser user, @MappingTarget UserEntity entity) {
    if (user.getPassword() == null) {
      return;
    }
    entity.setPassword(passwordEncoder.encode(user.getPassword()));
  }
}
