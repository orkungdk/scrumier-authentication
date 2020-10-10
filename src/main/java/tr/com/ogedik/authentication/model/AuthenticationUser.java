package tr.com.ogedik.authentication.model;

import java.time.LocalDateTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.ogedik.commons.annotation.NotNull;
import tr.com.ogedik.commons.model.AbstractBo;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationUser extends AbstractBo {

  private Long resourceId;

  @NotNull
  private String username;

  @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
  private String password;

  private String displayName;

  @NotNull
  private String team;

  @NotNull
  private String email;

  private String avatarUrl;

  private List<AuthenticationGroup> groups;

  private LocalDateTime enrolmentDate;

  private LocalDateTime lastLoginDate;

}
