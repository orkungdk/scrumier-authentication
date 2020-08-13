package tr.com.ogedik.authentication.mapper;

import org.mapstruct.Mapper;
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
@Mapper(componentModel = "spring", uses = {GroupMapper.class})
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
    public abstract UserEntity convert(AuthenticationUser user);

    public UserEntity convertCreate(AuthenticationUser user) {
        UserEntity entity = convert(user);

        if (user.getPassword() != null && entity != null) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return entity;
    }
}
