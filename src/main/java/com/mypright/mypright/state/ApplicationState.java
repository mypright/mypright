package com.mypright.mypright.state;

import com.mypright.mypright.model.SiteRequestHook;
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

}
