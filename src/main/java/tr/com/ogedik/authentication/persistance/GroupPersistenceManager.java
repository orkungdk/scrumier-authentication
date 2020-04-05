/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.persistance;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.entity.GroupEntity;
import tr.com.ogedik.authentication.persistance.repository.GroupRepository;

/**
 * @author orkun.gedik
 */
@Service
public class GroupPersistenceManager {

  @Autowired
  private GroupRepository repository;

  public List<GroupEntity> findAll() {
    return repository.findAll();
  }

  public GroupEntity findByResourceId(@NotNull Long id) {
    return repository.findByResourceId(id);
  }

  public GroupEntity save(@NotNull GroupEntity groupEntity) {
    return repository.save(groupEntity);
  }

  public void deleteByResourceId(@NotNull Long id) {
    repository.deleteByResourceId(id);
  }

  public boolean existsByResourceId(@NotNull Long id) {
    return repository.existsByResourceId(id);
  }
}
