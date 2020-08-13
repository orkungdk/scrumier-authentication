package tr.com.ogedik.authentication.constants;

import lombok.Getter;

/**
 * @author orkun.gedik
 */
@Getter
public enum Permission {

  DEFAULT("Default user permission.", true),
  ADMIN("Admin permission for all application.", false),

  RETRIEVE_WORK_LOG("Users with this permission are able to retrieve their work-logs.", true),
  CREATE_WORK_LOG("Users with this permission are able to create a work-log.", false),
  UPDATE_WORK_LOG("Users with this permission are able to update their work-logs.", false),
  DELETE_WORK_LOG("Users with this permission are able to delete their work-logs.", false),

  RETRIEVE_OTHERS_WORK_LOG("Users with this permission are able to retrieve team members' work-logs.", false),
  CREATE_OTHERS_WORK_LOG("Users with this permission are able to create members' work-logs.", false),
  UPDATE_OTHERS_WORK_LOG("Users with this permission are able to update members' work-logs.", false),
  DELETE_OTHERS_WORK_LOG("Users with this permission are able to delete members' work-logs.", false);

  private String description;
  private Boolean defaultValue;

  Permission(String description, boolean defaultValue) {
    this.description = description;
    this.defaultValue = defaultValue;
  }
}
