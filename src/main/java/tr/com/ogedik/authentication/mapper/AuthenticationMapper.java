/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import tr.com.ogedik.authentication.entity.AbstractEntity;
import tr.com.ogedik.commons.models.MetaInformation;
import tr.com.ogedik.commons.mapper.AbstractMapper;
import tr.com.ogedik.commons.models.BusinessObject;

/**
 * @author orkun.gedik
 */
public abstract class AuthenticationMapper<A, B extends BusinessObject> extends AbstractMapper<A, B> {

  public MetaInformation getMetaInformation(AbstractEntity entity) {
    if (entity == null) {
      return null;
    }

    return MetaInformation.builder()
        .createdAt(entity.getCreatedAt())
        .createdBy(entity.getCreatedBy())
        .updatedAt(entity.getUpdatedAt())
        .updatedBy(entity.getUpdateBy())
        .build();
  }
}
