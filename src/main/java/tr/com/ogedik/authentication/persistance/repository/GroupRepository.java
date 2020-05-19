package tr.com.ogedik.authentication.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import tr.com.ogedik.authentication.entity.GroupEntity;

/**
 * @author orkun.gedik
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByResourceId(Long id);

    void deleteByResourceId(Long id);

    Boolean existsByResourceId(Long id);

    Boolean existsByName(String name);
}
