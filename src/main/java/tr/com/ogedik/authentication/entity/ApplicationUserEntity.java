/**
 * © 2020 Copyright Amadeus Unauthorised use and disclosure strictly forbidden.
 */
package tr.com.ogedik.authentication.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.ogedik.authentication.constants.AuthenticationConstants;

/**
 * @author orkun.gedik
 */
@Entity
@Table(name = AuthenticationConstants.Entity.APPLICATION_USER)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationUserEntity extends AbstractEntity{

  @Column(name = COLS.USERNAME)
  private String username;

  @Column(name = COLS.PASSWORD)
  private String password;

  @Column(name = COLS.GROUPS)
  @OneToMany(fetch = FetchType.EAGER)
  private List<ApplicationGroupEntity> groups;

  @Column(name = COLS.TEAM)
  private String team;

  @Column(name = COLS.ROLE)
  private String role;

  @Column(name = COLS.ENROLMENT_DATE)
  private LocalDateTime enrolmentDate;

  @Column(name = COLS.LAST_LOGON_DATE)
  private LocalDateTime lastLogonDate;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApplicationUserEntity that = (ApplicationUserEntity)o;

    return ObjectUtils.equals(that.resourceId, this.resourceId) && ObjectUtils.equals(that.username, this.username)
        && ObjectUtils.equals(that.password, this.password) && ObjectUtils.equals(that.role, this.role)
        && ObjectUtils.equals(that.team, this.team) && ObjectUtils.equals(that.enrolmentDate, this.enrolmentDate)
        && ObjectUtils.equals(that.lastLogonDate, this.lastLogonDate);
  }
}