/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.Permission;

import java.util.List;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class ApplicationGroup implements BusinessObject{

  private Long resourceId;

  private String name;

  private String description;

  private MetaInformation metaInformation;

  private List<Permission> permissions;

}
