/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = AuthenticationConstants.COLS.RESOURCE_ID, updatable = false, nullable = false)
  protected Long resourceId;

  @Column(name = AuthenticationConstants.COLS.CREATED_AT)
  private LocalDateTime createdAt;

  @Column(name = AuthenticationConstants.COLS.CREATED_BY)
  private String createdBy;

  @Column(name = AuthenticationConstants.COLS.UPDATED_AT)
  private LocalDateTime updatedAt;

  @Column(name = AuthenticationConstants.COLS.UPDATED_BY)
  private String updateBy;

}
