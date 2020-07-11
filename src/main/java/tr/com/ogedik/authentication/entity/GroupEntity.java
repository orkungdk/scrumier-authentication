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
import tr.com.ogedik.commons.entity.ResourceEntity;

/**
 * @author orkun.gedik
 */
@Entity
@Table(name = AuthenticationConstants.Entity.APPLICATION_GROUP)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupEntity extends ResourceEntity {

  @Column(name = AuthenticationConstants.COLS.NAME)
  private String name;

  @Column(name = AuthenticationConstants.COLS.DESCRIPTION)
  private String description;

  @Column(name = AuthenticationConstants.COLS.PERMISSIONS)
  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Permission> permissions;
}
