package com.mypright.mypright.service;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.state.ApplicationState;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SiteRequestService {

  public String generateUniqueSiteID() {
    return new Long((long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L).toString();
  }

  public SiteRequestHook createSiteRequestHook(SiteRequest siteRequest, String uniqueId) {
    SiteRequestHook siteRequestHook = new SiteRequestHook(uniqueId,false,siteRequest);
    ApplicationState.getINSTANCE().getSiteRequestHooks().add(siteRequestHook);
    return siteRequestHook;
  }

  public List<SiteRequestHook> getAllSiteRequestHooks() {
    List<SiteRequestHook> siteRequestHooks = new ArrayList<>();
    siteRequestHooks.addAll(ApplicationState.getINSTANCE().getSiteRequestHooks());
    return siteRequestHooks;
  }
}
