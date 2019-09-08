package com.mypright.mypright.state;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.model.UserDetail;
import java.util.ArrayList;
import java.util.List;

public class ApplicationState {

  private List<SiteRequestHook> siteRequestHooks = new ArrayList<>();

  private static ApplicationState INSTANCE = null;

  public static ApplicationState getINSTANCE(){
    if(INSTANCE == null){
      INSTANCE = new ApplicationState();
    }
    return INSTANCE;
  }

  public List<SiteRequestHook> getSiteRequestHooks(){
    return siteRequestHooks;
  }

  public SiteRequestHook getGrantedUserDetailsFor(SiteRequestHook siteRequestHook){
    List<UserDetail> userDetails = new ArrayList<>();
    for (UserDetail userDetail : siteRequestHook.getSiteRequest().getUserDetail()){
      if (userDetail.isRequired()){
        userDetails.add(userDetail);
      }
    }
    SiteRequest siteRequest = new SiteRequest();
    siteRequest.setSiteUrl(siteRequestHook.getSiteRequest().getSiteUrl());
    siteRequest.setUserDetail(userDetails);
    return new SiteRequestHook(siteRequestHook.getUniqueId(), siteRequest);
  }

}
