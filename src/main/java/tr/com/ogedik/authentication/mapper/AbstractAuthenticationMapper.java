/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import java.util.ArrayList;
import java.util.List;

import tr.com.ogedik.authentication.entity.AbstractEntity;
import tr.com.ogedik.authentication.model.BusinessObject;
import tr.com.ogedik.authentication.model.MetaInformation;
import tr.com.ogedik.authentication.util.ListUtils;

/**
 * @author orkun.gedik
 */
public abstract class AbstractAuthenticationMapper<A, B extends BusinessObject> implements AuthenticationMapper<A, B> {

  /**
   * Transforms implemented List<A> type object to a List<?> object
   * 
   * @param bList of object implemented A type object to transform
   * @return list of implemented A type business object mapped onto an entity
   */
  public List<A> convertToEntityList(final List<B> bList) {
    final List<A> aList = new ArrayList<>();
    if (bList == null) {
      return aList;
    }
    for (final B b : ListUtils.emptyIfNull(bList)) {
      aList.add(convert(b));
    }
    return aList;
  }

  /**
   * Transforms implemented List<A> type object to a List<?> object
   * 
   * @param aList of object implemented B type object to transform
   * @return list of implemented B type entity mapped onto a business object
   */
  public List<B> convertToBoList(final List<A> aList) {
    final List<B> bList = new ArrayList<>();
    if (aList == null) {
      return bList;
    }
    for (final A b : aList) {
      bList.add(convert(b));
    }
    return bList;
  }

  protected MetaInformation getMetaInformation(AbstractEntity entity) {
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
