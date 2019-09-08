package com.mypright.mypright.controller;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.model.UserDetail;
import com.mypright.mypright.service.SiteRequestService;
import com.mypright.mypright.state.ApplicationState;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
@CrossOrigin(origins = "*")
@Slf4j
public class SiteRequestController {

  @Autowired
  private SiteRequestService siteRequestService;

  @PostMapping(value = "/request")
  public ResponseEntity<String> generateUniqueId(@RequestBody SiteRequest siteRequest) {
    JSONObject siteRequestJson;
    try {
      siteRequestJson = new JSONObject(siteRequest.toString());

      if (!siteRequestJson.get("siteUrl").toString().isEmpty()) {
        String uniqueSiteId = siteRequestService.generateUniqueSiteID();

        siteRequestService.createSiteRequestHook(siteRequest, uniqueSiteId);
        String responseBody = String.format("{\"uniqueSiteId\":%s}", uniqueSiteId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
      }
    } catch (Exception e) {
      log.error("Could not parse the request" + e);
    }
    return new ResponseEntity<>("Failed to create uniqueSiteId", HttpStatus.BAD_REQUEST);
  }

  @GetMapping(value = "/data")
  public ResponseEntity<SiteRequestHook> sendSiteRequestHook(
      @RequestParam("uniqueSiteId") String uniqueSiteId) {
    for (SiteRequestHook siteRequestHook : ApplicationState.getINSTANCE().getSiteRequestHooks()) {
      if (siteRequestHook.getUniqueId().equals(uniqueSiteId)) {
        return new ResponseEntity<>(siteRequestHook, HttpStatus.OK);
      }
    }
    SiteRequestHook syncedData = siteRequestService.fetchPortalDetails(uniqueSiteId);
    if(syncedData != null) {
      ApplicationState.getINSTANCE().getSiteRequestHooks().add(syncedData);
      return new ResponseEntity<>(syncedData, HttpStatus.OK);
    }

    return new ResponseEntity<>((SiteRequestHook) null, HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/data/all")
  public ResponseEntity<List<SiteRequestHook>> sendAllUserData() {
    List<SiteRequestHook> siteRequestHooks = siteRequestService.getAllSiteRequestHooks();
    return new ResponseEntity<>(siteRequestHooks, HttpStatus.OK);
  }

  @PostMapping(value = "/approve")
  public HttpStatus approveSite(@RequestBody String responseBody) {
    JSONObject responseJson;
    String uniqueSiteId;
    try {
      responseJson = new JSONObject(responseBody);
      uniqueSiteId = responseJson.get("uniqueSiteId").toString();

      for (SiteRequestHook siteRequestHook : ApplicationState.getINSTANCE().getSiteRequestHooks()) {
        if (siteRequestHook.getUniqueId().equals(uniqueSiteId)) {
          siteRequestHook.setApproved(responseJson.getBoolean("approved"));
          return HttpStatus.OK;
        }
      }
    } catch (Exception e) {
      log.error("Could not parse the request" + e);
    }
    return HttpStatus.NO_CONTENT;
  }

  @GetMapping(value = "/user/details")
  public ResponseEntity<List<UserDetail>> getUserDetailsFor(
      @RequestParam("uniqueSiteId") String uniqueSiteId) {
    List<UserDetail> userDetailsList = ApplicationState.getINSTANCE().getUserDetails();
    for (SiteRequestHook siteRequestHook : ApplicationState.getINSTANCE().getSiteRequestHooks()) {
      if (siteRequestHook.getUniqueId().equals(uniqueSiteId) && siteRequestHook.isApproved()) {
        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  @RequestMapping(
      value = "/*/**",
      method = RequestMethod.OPTIONS
  )
  public ResponseEntity handle() {
    return new ResponseEntity(HttpStatus.OK);
  }

}
