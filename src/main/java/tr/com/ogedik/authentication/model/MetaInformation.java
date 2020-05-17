/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetaInformation {

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private String createdBy;
  private String updatedBy;
}
