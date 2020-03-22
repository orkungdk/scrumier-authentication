/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.commons.models.AbstractGroup;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class Group extends AbstractGroup {

  private Long resourceId;

  @NotNull
  private String name;

  private String description;

  private MetaInformation metaInformation;

  private List<GrantedAuthority> authorities;

}
