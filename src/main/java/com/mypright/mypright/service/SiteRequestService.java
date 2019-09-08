package com.mypright.mypright.service;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.state.ApplicationState;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.sun.jndi.toolkit.url.Uri;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.json.XMLTokener.entity;

@Service
@AllArgsConstructor
public class SiteRequestService {

  private final RestTemplate restTemplate;

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

  public SiteRequestHook fetchPortalDetails(String uniqueSiteId) {
    System.out.println("CommonInstance:"+System.getenv("COMMON_INSTANCE"));
    if("false" == System.getenv("LOCAL_INSTANCE") || "false".equals(System.getenv("LOCAL_INSTANCE"))) {
      return null;
    }
    try {
      URI uri = new URI("https://myprightservice.herokuapp.com/site/data?uniqueSiteId=" + uniqueSiteId);
      SiteRequestHook siteRequestHook =
              restTemplate.exchange(
                      uri,
                      HttpMethod.GET,
                      new HttpEntity<>(new LinkedMultiValueMap<String, String>(), new HttpHeaders()),
                      SiteRequestHook.class)
                      .getBody();
      return siteRequestHook;
    } catch (Exception exception) {
      System.out.println("Exception with uniqueId:");
      return null;
    }
  }
}
