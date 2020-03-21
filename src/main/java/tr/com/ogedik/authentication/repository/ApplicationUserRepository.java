/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tr.com.ogedik.authentication.entity.ApplicationUserEntity;
import tr.com.ogedik.authentication.model.ApplicationUser;

/**
 * @author orkun.gedik
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, Long> {

  ApplicationUserEntity findByUsername(String username);

  Boolean existsByUsername(String username);

  void deleteByUsername(String username);

}
