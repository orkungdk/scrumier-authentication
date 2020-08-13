package tr.com.ogedik.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;
import tr.com.ogedik.authentication.constants.Permission;
import tr.com.ogedik.commons.entity.ResourceEntity;

import javax.persistence.*;
import java.util.List;

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
