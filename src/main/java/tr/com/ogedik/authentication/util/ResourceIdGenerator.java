/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.util;

import lombok.experimental.UtilityClass;

/**
 * @author orkun.gedik
 */
@UtilityClass
public class ResourceIdGenerator {

  public static Long generate() {
    return (long)((System.nanoTime() * 17) / Math.pow(10.0, 11));
  }
}
