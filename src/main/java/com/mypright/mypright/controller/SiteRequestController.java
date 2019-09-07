package com.mypright.mypright.controller;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.service.SiteRequestService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
@Slf4j
public class SiteRequestController {

  @Autowired
  private SiteRequestService siteRequestService;

  @RequestMapping(value = "/request", method = RequestMethod.GET)
  public ResponseEntity<String> generateUniqueId(@RequestBody SiteRequest siteRequest) {
    JSONObject object = null;
    try {
      object = new JSONObject(siteRequest.toString());
      if (!object.get("siteUrl").toString().isEmpty()) {
        String uniqueSiteId = siteRequestService.generateUniqueSiteID();
        String responseBody = String.format("{\"uniqueSiteId\":%s}",uniqueSiteId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
      }
    } catch (JSONException e) {
      log.error("Could not parse the request" + e);
    }
    return new ResponseEntity<>("Failed to create uniqueId", HttpStatus.BAD_REQUEST);
  }

}
