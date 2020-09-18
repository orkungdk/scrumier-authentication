package tr.com.ogedik.authentication.persistance.manager;

import java.util.List;

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

  public GroupEntity findByResourceId(Long id) {
    return repository.findByResourceId(id);
  }

  public GroupEntity save(GroupEntity groupEntity) {
    return repository.save(groupEntity);
  }

  public void deleteByResourceId(Long id) {
    repository.deleteByResourceId(id);
  }

  public boolean existsByResourceId(Long id) {
    return repository.existsByResourceId(id);
  }

    public boolean existByGroupName(String groupName) {
    return repository.existsByName(groupName);
    }
}
