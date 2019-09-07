package com.mypright.mypright.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteRequest {

  private String siteUrl;

  private List<UserDetail> userDetail;

  @Override
  public String toString() {
    return "{" +
        "siteUrl='" + siteUrl + '\'' +
        ", userDetail:" + userDetail +
        '}';
  }
}
