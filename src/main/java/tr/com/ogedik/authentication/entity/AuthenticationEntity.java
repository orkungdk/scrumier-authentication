/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author orkun.gedik
 */
public interface AuthenticationEntity {

  LocalDateTime getCreatedAt();

  void setCreatedAt(LocalDateTime createdAt);

  String getCreatedBy();

  void setCreatedBy(String createdBy);

  LocalDateTime getUpdatedAt();

  void setUpdatedAt(LocalDateTime updatedAt);

  String getUpdatedBy();

  void setUpdatedBy(String updatedBy);
}
