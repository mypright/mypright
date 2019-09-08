package com.mypright.mypright.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

  private String detailName;

  private String reason;

  private String detailValue;

  @Override
  public String toString() {
    return "{" +
        "detailName:'" + detailName + '\'' +
        ", reason:'" + reason + '\'' +
        ", detailValue:'" + detailValue + '\'' +
        '}';
  }
}
