/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.entity;

import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = COLS.RESOURCE_ID, updatable = false, nullable = false)
  protected Long resourceId;

  @Column(name = COLS.CREATED_AT)
  private LocalDateTime createdAt;

  @Column(name = COLS.CREATED_BY)
  private String createdBy;

  @Column(name = COLS.UPDATED_AT)
  private LocalDateTime updatedAt;

  @Column(name = COLS.UPDATED_BY)
  private String updateBy;

  public static class COLS {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String TEAM = "team";
    public static final String ROLE = "role";
    public static final String ENROLMENT_DATE = "enrolmentDate";
    public static final String LAST_LOGON_DATE = "lastLogonDate";
    public static final String RESOURCE_ID = "resourceId";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";
    public static final String CREATED_BY = "createdBy";
    public static final String UPDATED_BY = "updatedBy";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String GROUPS = "groups";
    public static final String PERMISSIONS = "permissions";
  }
}
