package tr.com.ogedik.authentication.constants;

import lombok.Getter;

/**
 * @author orkun.gedik
 */
@Getter
public enum Permission {
  RETRIEVE_WORK_LOG("Users with this permission are able to retrieve their work-logs.", true),
  CREATE_WORK_LOG("Users with this permission are able to create a worklog.", false),
  UPDATE_WORK_LOG("Users with this permission are able to update their worklogs.", false),
  DELETE_WORK_LOG("Users with this permission are able to delete their worklogs.", false),

  RETRIEVE_OTHERS_WORK_LOG("Users with this permission are able to retrieve team members' work-logs.", true),
  CREATE_OTHERS_WORK_LOG("Users with this permission are able to create members' work-logs.", true),
  UPDATE_OTHERS_WORK_LOG("Users with this permission are able to update members' work-logs.", true),
  DELETE_OTHERS_WORK_LOG("Users with this permission are able to delete members' work-logs.", true);

  private String description;
  private boolean defaultValue;

  Permission(String description, boolean defaultValue) {
    this.description = description;
    this.defaultValue = defaultValue;
  }
}
