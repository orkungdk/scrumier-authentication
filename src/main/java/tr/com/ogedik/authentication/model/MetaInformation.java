/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class MetaInformation {

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private String createdBy;
  private String updatedBy;
}
