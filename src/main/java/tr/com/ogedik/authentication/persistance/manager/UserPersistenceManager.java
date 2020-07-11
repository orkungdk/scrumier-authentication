/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.persistance.manager;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.entity.UserEntity;
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.persistance.repository.UserRepository;

import javax.validation.constraints.NotNull;

/**
 * @author orkun.gedik
 */
@Service
@CacheConfig(cacheNames = { "authenticationCache" })
public class UserPersistenceManager {

  @Autowired
  private UserRepository repository;

  public boolean existsByUsername(@NotNull String username) {
    return repository.existsByUsername(username);
  }

  public List<UserEntity> findAll() {
    return repository.findAll();
  }

  @Cacheable(key = "#username", unless = "#result==null")
  public UserEntity findByUsername(@NotNull String username) {
    return repository.findByUsername(username);
  }

  @CachePut(key = "#userEntity.username")
  public UserEntity save(@NotNull UserEntity userEntity) {
    return repository.save(userEntity);
  }

  @CacheEvict(key = "#userEntity.username")
  public UserEntity update(@NotNull UserEntity userEntity) {
    return repository.save(userEntity);
  }

  @CacheEvict(key = "#username")
  public void deleteByUsername(@NotNull String username) {
    if (BooleanUtils.isFalse(repository.existsByUsername(username))) {
      throw new AuthenticationException(AuthenticationErrorType.USER_NOT_FOUND, username);
    }

    repository.deleteByUsername(username);
  }
}
