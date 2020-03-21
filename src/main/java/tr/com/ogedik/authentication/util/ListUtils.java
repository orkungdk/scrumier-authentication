/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.util;

import java.util.Collections;
import java.util.List;

/**
 * Utility class for {@link java.util.List}
 * 
 * @author orkun.gedik
 */
public class ListUtils {

  private ListUtils() {
    throw new UnsupportedOperationException("Utility class cannot be created!");
  }

  public static <T> List<T> emptyIfNull(final List<T> list) {
    return list == null ? Collections.<T> emptyList() : list;
  }

}
