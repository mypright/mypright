package com.mypright.mypright.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {

  private String detailName;

  private boolean isRequired;

  private String reason;

  @Override
  public String toString() {
    return "{" +
        "detailName='" + detailName + '\'' +
        ", isRequired=" + isRequired +
        ", reason='" + reason + '\'' +
        '}';
  }
}
