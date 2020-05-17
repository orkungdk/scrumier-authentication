/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import lombok.experimental.UtilityClass;
import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.exception.ErrorType;

/**
 * Utility class for {@link java.util.List}
 *
 * @author orkun.gedik
 */
@UtilityClass
public class ListUtils {

  /**
   * Returns an immutable empty list if the argument is {@code null},
   * or the argument itself otherwise.
   *
   * @param <T> the element type
   * @param list the list, possibly {@code null}
   * @return an empty list if the argument is {@code null}
   */
  public static <T> List<T> emptyIfNull(final List<T> list) {
    return list == null ? Collections.<T>emptyList() : list;
  }

  /**
   * Merges all elements of multiple lists into a single list. Does not allow duplication in the final list. Uses
   * {@link SingularList} to prevent duplication during object insertion to the list. This method uses in the following
   * situation: Let 'superVar' an object and 'subVar' is a {@link List} field in the superVar. Also, let 'subSubVar'
   * another {@link List} field in the subVar which is also a list field in 'superVar'. This method returns all elements
   * in 'subSubVar' field.
   *
   * @param <T>  the element type
   * @param list the first {@link List} in the field hierarchy
   * @param path path of second {@link List} to retrieve all elements of the list.
   * @return merged {@link List} of object
   */
  public static <T> List<?> mergeNested(final List<T> list, String path) throws IllegalAccessException {
    List<Object> singularList = new SingularList<>();

    for (T object : list) {
      Field[] fields = object.getClass().getDeclaredFields();
      Field field = Arrays.stream(fields)
          .filter(o -> path.equals(o.getName()))
          .findFirst()
          .orElseThrow(() -> new AuthenticationException(ErrorType.INTERNAL_ERROR, path + " is not exist."));
      field.setAccessible(true);
      List<?> values = (List<?>)field.get(object);
      singularList.addAll(values);
    }

    return singularList;
  }

  private static class SingularList<T> extends ArrayList<T> implements Supplier<T> {

    @Override
    public boolean add(T var) {
      if (var != null && !this.contains(var)) {
        return super.add(var);
      } else {
        return false;
      }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
      if (collection == null) {
        return false;
      }
      collection.stream().forEach(var -> this.add(var));
      return true;
    }

    @Override
    public T get() {
      return null;
    }
  }
}