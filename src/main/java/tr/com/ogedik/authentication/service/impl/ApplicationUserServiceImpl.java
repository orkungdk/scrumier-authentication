/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.entity.ApplicationUserEntity;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.mapper.ApplicationUserMapper;
import tr.com.ogedik.authentication.model.ApplicationUser;
import tr.com.ogedik.authentication.repository.ApplicationUserRepository;
import tr.com.ogedik.authentication.service.ApplicationUserService;
import tr.com.ogedik.authentication.validation.user.ApplicationUserValidationFacade;

/**
 * @author orkun.gedik
 */
@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

  @Autowired
  private ApplicationUserRepository repository;
  @Autowired
  private ApplicationUserValidationFacade validationFacade;
  @Autowired
  private ApplicationUserMapper mapper;

  @Override
  public ApplicationUser getUserByUsername(String username) {
    ApplicationUserEntity entity = repository.findByUsername(username);
    return ObjectUtils.isEmpty(entity) ? null : mapper.convert(entity);
  }

  @Override
  public List<ApplicationUser> getAllUsers() {
    return mapper.convertToBoList(repository.findAll());
  }

  @Override
  public ApplicationUser create(ApplicationUser applicationUser) {
    validationFacade.validateCreate(applicationUser);
    applicationUser.setEnrolmentDate(LocalDateTime.now());

    ApplicationUserEntity savedEntity = repository.save(mapper.convert(applicationUser));

    return mapper.convert(savedEntity);
  }

  @Override
  public ApplicationUser update(ApplicationUser applicationUser) {
    validationFacade.validateUpdate(applicationUser);
    ApplicationUserEntity entity = repository.save(mapper.convert(applicationUser));

    return mapper.convert(entity);
  }

  @Override
  public void delete(String username) {
    if (BooleanUtils.isFalse(repository.existsByUsername(username))) {
      throw new AuthenticationException(AuthenticationConstants.Exception.USER_NOT_FOUND);
    }

    repository.deleteByUsername(username);
  }
}
