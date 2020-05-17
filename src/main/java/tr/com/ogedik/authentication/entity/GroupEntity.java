/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.constants.Permission;

/**
 * @author orkun.gedik
 */
@Entity
@Table(name = AuthenticationConstants.Entity.APPLICATION_GROUP)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = AuthenticationConstants.COLS.RESOURCE_ID, updatable = false, nullable = false)
  protected Long resourceId;

  @Column(name = AuthenticationConstants.COLS.NAME)
  private String name;

  @Column(name = AuthenticationConstants.COLS.DESCRIPTION)
  private String description;

  @Column(name = AuthenticationConstants.COLS.PERMISSIONS)
  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Permission> permissions;

  @Column(name = AuthenticationConstants.COLS.CREATED_AT)
  private LocalDateTime createdAt;

  @Column(name = AuthenticationConstants.COLS.CREATED_BY)
  private String createdBy;

  @Column(name = AuthenticationConstants.COLS.UPDATED_AT)
  private LocalDateTime updatedAt;

  @Column(name = AuthenticationConstants.COLS.UPDATED_BY)
  private String updatedBy;

}
