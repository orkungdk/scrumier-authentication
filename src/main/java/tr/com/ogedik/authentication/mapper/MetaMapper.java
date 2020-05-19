/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import tr.com.ogedik.authentication.entity.AuthenticationEntity;
import tr.com.ogedik.authentication.model.AbstractAuthenticationModel;
import tr.com.ogedik.authentication.model.MetaInformation;

/**
 * @author orkun.gedik
 */
@Mapper(componentModel = "spring")
public abstract class MetaMapper {

  @AfterMapping
  public void fillEntityMeta(AbstractAuthenticationModel model, @MappingTarget AuthenticationEntity entity) {
    if (model.getMetaInformation() == null) {
      return;
    }
    entity.setCreatedAt(model.getMetaInformation().getCreatedAt());
    entity.setCreatedBy(model.getMetaInformation().getCreatedBy());
    entity.setUpdatedAt(model.getMetaInformation().getUpdatedAt());
    entity.setUpdatedBy(model.getMetaInformation().getUpdatedBy());
  }

  @AfterMapping
  public void fillModelMeta(AuthenticationEntity entity, @MappingTarget AbstractAuthenticationModel model) {
    if (model.getMetaInformation() == null) {
      model.setMetaInformation(new MetaInformation());
    }
    model.getMetaInformation().setCreatedAt(entity.getCreatedAt());
    model.getMetaInformation().setCreatedBy(entity.getCreatedBy());
    model.getMetaInformation().setUpdatedAt(entity.getUpdatedAt());
    model.getMetaInformation().setUpdatedBy(entity.getUpdatedBy());
  }
}
