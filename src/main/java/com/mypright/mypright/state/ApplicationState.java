package com.mypright.mypright.state;

import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.model.UserDetail;
import java.util.ArrayList;
import java.util.List;

public class ApplicationState {

  private List<SiteRequestHook> siteRequestHooks = new ArrayList<>();

  private static ApplicationState INSTANCE = null;

  private UserDetail userNameDetail = new UserDetail("name",null,"Mozilla");

  private UserDetail userDobDetail = new UserDetail("dob",null,"September 23, 2002");

  private UserDetail userAadharDetail = new UserDetail("aadhar",null,"000000000000");

  private UserDetail userPanDetail = new UserDetail("pan",null,"AFTJUKNHG");

  public static ApplicationState getINSTANCE(){
    if(INSTANCE == null){
      INSTANCE = new ApplicationState();
    }
    return INSTANCE;
  }

  public List<SiteRequestHook> getSiteRequestHooks(){
    return siteRequestHooks;
  }

  public List<UserDetail> getUserDetails(){
    List<UserDetail> userDetailsList = new ArrayList<>();

    userDetailsList.add(userNameDetail);
    userDetailsList.add(userDobDetail);
    userDetailsList.add(userAadharDetail);
    userDetailsList.add(userPanDetail);

    return userDetailsList;
  }

}
