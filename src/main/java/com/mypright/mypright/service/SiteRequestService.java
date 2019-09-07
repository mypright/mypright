package com.mypright.mypright.service;

import org.springframework.stereotype.Service;

@Service
public class SiteRequestService {

  public String generateUniqueSiteID() {
    return new Long((long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L).toString();
  }

}
