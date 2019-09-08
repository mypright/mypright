package com.mypright.mypright.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SiteRequestHook {

  private String uniqueId;

  private SiteRequest siteRequest;

  @Override
  public String toString() {
    return "{" +
        "uniqueSiteId:'" + uniqueId + '\'' +
        ", siteRequest:" + siteRequest +
        '}';
  }
}
