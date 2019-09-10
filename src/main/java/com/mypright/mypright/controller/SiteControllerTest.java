package com.mypright.mypright.controller;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.model.UserDetail;
import com.mypright.mypright.service.SiteRequestService;
import lombok.var;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SiteControllerTest {
    @Mock
    SiteRequestService siteRequestServiceMock;

    @Test
    public void testForGenerateUniqueId(){

        SiteRequest siteRequest = getSiteRequest();
        String uniqueId = "12345";
        when(siteRequestServiceMock.generateUniqueSiteID()).thenReturn(uniqueId);
        when(siteRequestServiceMock.createSiteRequestHook(siteRequest,uniqueId)).thenReturn(new SiteRequestHook());

        SiteRequestController siteRequestController = new SiteRequestController();
       var result= siteRequestController.generateUniqueId(siteRequest);
        Assert.assertEquals(result.getStatusCode(),HttpStatus.OK);

    }

    private SiteRequest getSiteRequest() {
        UserDetail userDetail = new UserDetail();
        userDetail.setDetailName("PAN");
        userDetail.setDetailValue("wer");
        userDetail.setReason("pan");
        List<UserDetail> userDetailList = new ArrayList<>();
        userDetailList.add(userDetail);
        SiteRequest siteRequest = new SiteRequest();
        siteRequest.setSiteUrl("www.godaddy.com");
        siteRequest.setUserDetail(userDetailList);
        return siteRequest;
    }

    @Test
    public void testForSendSiteRequestHook(){

        String uniqueId = "12345";
        when(siteRequestServiceMock.fetchPortalDetails(uniqueId)).thenReturn(new SiteRequestHook());
        SiteRequestController siteRequestController = new SiteRequestController();
        var result= siteRequestController.sendSiteRequestHook(uniqueId);
        Assert.assertEquals(result.getStatusCode(),HttpStatus.NO_CONTENT);
    }

    @Test
    public void testForSendAllUserData(){

    }

    @Test
    public void testForApproveSite(){

    }

    @Test
    public void testForGetUserDetailsFor(){

    }
}
