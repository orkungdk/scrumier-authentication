package tr.com.ogedik.authentication.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author orkun.gedik
 */
@Getter
@Setter
@Builder
public class AuthenticationDetails implements Authentication {

  @JsonIgnore
  private String principal;

  private String token;

  private List<UserGrantedAuthority> authorities;

  @JsonIgnore
  private boolean isAuthenticated;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String credentials;

  private AuthenticationUser details;

  @Override
  public Object getDetails() {
    return details;
  }

  @Override
  public String getName() {
    return principal;
  }
}
