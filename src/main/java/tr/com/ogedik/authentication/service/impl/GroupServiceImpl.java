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
import tr.com.ogedik.authentication.exception.AuthenticationErrorType;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.mapper.GroupMapper;
import tr.com.ogedik.authentication.model.AuthenticationGroup;
import tr.com.ogedik.authentication.persistance.manager.GroupPersistenceManager;
import tr.com.ogedik.authentication.service.GroupService;
import tr.com.ogedik.authentication.validation.group.GroupValidationFacade;

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
  private GroupMapper groupMapper;

  @Override
  public boolean isExist(String groupName) {
    return persistenceManager.existByGroupName(groupName);
  }

  @Override
  public List<AuthenticationGroup> getGroups() {
    return groupMapper.convert(persistenceManager.findAll());
  }

  @Override
  public AuthenticationGroup getGroupById(Long id) {
    return groupMapper.convert(persistenceManager.findByResourceId(id));
  }

  @Override
  public AuthenticationGroup create(AuthenticationGroup group) {
    validationFacade.validateCreate(group);
    GroupEntity entity = persistenceManager.save(groupMapper.convert(group));

    return groupMapper.convert(entity);
  }

  @Override
  public AuthenticationGroup update(AuthenticationGroup group) {
    validationFacade.validateUpdate(group);
    GroupEntity entity = persistenceManager.save(groupMapper.convert(group));

    return groupMapper.convert(entity);
  }

  @Override
  public void delete(Long id) {
    if (BooleanUtils.isFalse(persistenceManager.existsByResourceId(id))) {
      throw new AuthenticationException(AuthenticationErrorType.GROUP_NOT_FOUND, "Requested group id is " + id);

    }
    persistenceManager.deleteByResourceId(id);
  }
}
