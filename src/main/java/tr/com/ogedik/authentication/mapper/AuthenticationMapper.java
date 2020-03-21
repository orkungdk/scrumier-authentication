/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.mapper;

import tr.com.ogedik.authentication.model.BusinessObject;

/**
 * Interface for the generic mapper between Json objects and java objects
 * 
 * @author orkun.gedik
 */
public interface AuthenticationMapper<A, B extends BusinessObject> {
  /**
   * Transforms implemented A type object to a JAVA object
   * 
   * @param object implemented A type object to transform
   * @return implemented A type object mapped onto a JAVA object
   */
  B convert(A object);

    /**
     * Transforms implemented B type object to a JAVA object
     *
     * @param object implemented B type object to transform
     * @return implemented A type object mapped onto a JAVA object
     */
  A convert(B object);
}
