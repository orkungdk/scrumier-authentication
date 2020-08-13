package tr.com.ogedik.authentication.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.ogedik.authentication.entity.UserEntity;

/**
 * @author orkun.gedik
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByUsername(String username);

  Boolean existsByUsername(String username);

  void deleteByUsername(String username);

}
