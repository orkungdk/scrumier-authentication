/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;

/**
 * @author orkun.gedik
 */
@Entity
@Table(name = AuthenticationConstants.Entity.APPLICATION_GROUP)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupEntity extends AbstractEntity {

  @Column(name = COLS.NAME)
  private String name;

  @Column(name = COLS.DESCRIPTION)
  private String description;

  @Column(name = COLS.PERMISSIONS)
  @ElementCollection(targetClass=String.class)
  private List<String> permissions;

}
