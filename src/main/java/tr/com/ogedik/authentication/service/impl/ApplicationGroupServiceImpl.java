/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.entity.ApplicationGroupEntity;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.mapper.ApplicationGroupMapper;
import tr.com.ogedik.authentication.model.ApplicationGroup;
import tr.com.ogedik.authentication.repository.ApplicationGroupRepository;
import tr.com.ogedik.authentication.service.ApplicationGroupService;
import tr.com.ogedik.authentication.validation.group.ApplicationGroupValidationFacade;

import java.util.List;

/**
 * @author orkun.gedik
 */
@Service
public class ApplicationGroupServiceImpl implements ApplicationGroupService {

  @Autowired
  private ApplicationGroupRepository repository;
  @Autowired
  private ApplicationGroupValidationFacade validationFacade;
  @Autowired
  private ApplicationGroupMapper mapper;

  @Override
  public List<ApplicationGroup> getGroups() {
    return mapper.convertToBoList(repository.findAll());
  }

  @Override
  public ApplicationGroup getGroupById(Long id) {
    return mapper.convert(repository.findByResourceId(id));
  }

  @Override
  public ApplicationGroup create(ApplicationGroup applicationGroup) {
    return saveApplicationGroup(applicationGroup);
  }

  @Override
  public ApplicationGroup update(ApplicationGroup applicationGroup) {
    return saveApplicationGroup(applicationGroup);
  }

  private ApplicationGroup saveApplicationGroup(ApplicationGroup applicationGroup) {
    validationFacade.validate(applicationGroup);
    ApplicationGroupEntity entity = repository.save(mapper.convert(applicationGroup));

    return mapper.convert(entity);
  }

  @Override
  public void delete(Long id) {
    if (BooleanUtils.isFalse(repository.existsByResourceId(id))) {
      throw new AuthenticationException(AuthenticationConstants.Exception.GROUP_NOT_FOUND);

    }
      repository.deleteByResourceId(id);
  }
}
