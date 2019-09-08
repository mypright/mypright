package com.mypright.mypright.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteRequestHook {

  private String uniqueId;

  private boolean isApproved;

  private SiteRequest siteRequest;

  @Override
  public String toString() {
    return "{" +
        "uniqueId:'" + uniqueId + '\'' +
        ", isApproved:" + isApproved +
        ", siteRequest:" + siteRequest +
        '}';
  }
}
