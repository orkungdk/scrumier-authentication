package tr.com.ogedik.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tr.com.ogedik.authentication.entity.ApplicationGroupEntity;

/**
 * @author orkun.gedik
 */
@Repository
public interface ApplicationGroupRepository extends JpaRepository<ApplicationGroupEntity, Long> {

    ApplicationGroupEntity findByResourceId(Long id);

    void deleteByResourceId(Long id);

    Boolean existsByResourceId(Long id);
}
