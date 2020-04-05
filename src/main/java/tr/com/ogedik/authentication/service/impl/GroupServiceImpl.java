/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.entity.GroupEntity;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.mapper.GroupMapper;
import tr.com.ogedik.authentication.persistance.GroupPersistenceManager;
import tr.com.ogedik.authentication.service.GroupService;
import tr.com.ogedik.authentication.validation.group.GroupValidationFacade;
import tr.com.ogedik.commons.models.Group;
import tr.com.ogedik.commons.utils.ResourceIdGenerator;

/**
 * @author orkun.gedik
 */
@Service
public class GroupServiceImpl implements GroupService {

  @Autowired
  private GroupPersistenceManager persistenceManager;
  @Autowired
  private GroupValidationFacade validationFacade;
  @Autowired
  private GroupMapper mapper;

  @Override
  public List<Group> getGroups() {
    return mapper.convertToBoList(persistenceManager.findAll());
  }

  @Override
  public Group getGroupById(Long id) {
    return mapper.convert(persistenceManager.findByResourceId(id));
  }

  @Override
  public Group create(Group group) {
    group.setResourceId(ResourceIdGenerator.generate());
    validationFacade.validate(group);
    GroupEntity entity = persistenceManager.save(mapper.convert(group));

    return mapper.convert(entity);
  }

  @Override
  public Group update(Group group) {
    validationFacade.validate(group);
    GroupEntity entity = persistenceManager.save(mapper.convert(group));

    return mapper.convert(entity);
  }

  @Override
  public void delete(Long id) {
    if (BooleanUtils.isFalse(persistenceManager.existsByResourceId(id))) {
      throw new AuthenticationException(AuthenticationConstants.Exception.GROUP_NOT_FOUND);

    }
    persistenceManager.deleteByResourceId(id);
  }
}
