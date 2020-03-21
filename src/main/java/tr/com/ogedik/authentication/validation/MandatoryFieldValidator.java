/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.validation;

import java.lang.reflect.Field;
import java.util.Arrays;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import tr.com.ogedik.authentication.exception.AuthenticationException;
import tr.com.ogedik.authentication.model.AbstractUser;
import tr.com.ogedik.authentication.model.AuthenticationRequest;

/**
 * @author orkun.gedik
 */
@Component
public class MandatoryFieldValidator<T extends AbstractUser> implements Validator<T> {

  /**
   * Validates whether all the fields of {@link AuthenticationRequest} are valid or not.
   *
   * @param object any object
   */
  public void validate(T object) {
    Field[] fields = object.getClass().getDeclaredFields();

    for (Field field : fields) {
      field.setAccessible(true);

      boolean isNotNullable = Arrays.stream(field.getDeclaredAnnotations())
          .filter(annotation -> annotation instanceof NotNull)
          .findFirst()
          .isPresent();
      if (isNotNullable) {
        validateFieldValue(field, object);
      }
    }
  }

  /**
   * Validates mandatory fields
   *
   * @param field mandatory field to validate
   * @param object instance of an object to validate
   *
   * @throws IllegalAccessException if the {@code field} is not accessible
   * @throws AuthenticationException if the {@code field} is empty
   */
  public void validateFieldValue(Field field, Object object) {
    try {
      Object value = field.get(object);
      if (ObjectUtils.isEmpty(value)) {
        throw new AuthenticationException(field.getName().concat(" cannot be empty!"));
      }

    } catch (IllegalAccessException e) {
      throw new AuthenticationException(field.getName().concat(" cannot be accessed!"));
    }
  }
}
