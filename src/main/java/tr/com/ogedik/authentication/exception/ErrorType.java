/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.exception;

import lombok.experimental.UtilityClass;

/**
 * @author orkun.gedik
 */
@UtilityClass
public class ErrorType {

  public static final String INTERNAL_ERROR = "Internal Error";
  public static final String AUTHORIZATION_FAILED = "Authorization is failed!";
  public static final String RECORD_NOT_FOUND = "Record not found!";
  public static final String RECORD_EXIST = "Record already exist!";
  public static final String WRONG_CONFIG_KEY = "Configuration key is not valid!";
}