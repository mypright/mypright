package com.mypright.mypright.controller;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.model.UserDetail;
import com.mypright.mypright.service.SiteRequestService;
import com.mypright.mypright.state.ApplicationState;
import lombok.var;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SiteControllerTest {

    @MockBean
    SiteRequestService siteRequestServiceMock;
    @MockBean
    SiteRequestHook siteRequestHookMock;
    @MockBean
    SiteRequest siteRequestMock;
    @MockBean
    ApplicationState applicationStateMock;

    @InjectMocks
    SiteRequestController siteRequestController;

    @Test
    public void testForGenerateUniqueId() {

        MockitoAnnotations.initMocks(this);
        SiteRequest siteRequest = getSiteRequest();
        String uniqueId = "12345";

        when(siteRequestServiceMock.generateUniqueSiteID()).thenReturn(uniqueId);
        when(siteRequestServiceMock.createSiteRequestHook(siteRequest, uniqueId)).thenReturn(getSiteRequestHook());

        var result = siteRequestController.generateUniqueId(siteRequest);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testForSendSiteRequestHook() {
        MockitoAnnotations.initMocks(this);

        when(siteRequestServiceMock.fetchPortalDetails("12345")).thenReturn(getSiteRequestHook());
        var result = siteRequestController.sendSiteRequestHook("12345");
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testForSendAllUserData() {
        MockitoAnnotations.initMocks(this);

        List<SiteRequestHook> siteRequestHooks = new ArrayList<>();
        siteRequestHooks.add(getSiteRequestHook());

        String uniqueId = "12345";
        when(siteRequestServiceMock.getAllSiteRequestHooks()).thenReturn(siteRequestHooks);
        var result = siteRequestController.sendAllUserData();
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testForApproveSite() {

        MockitoAnnotations.initMocks(this);


        List<SiteRequestHook> siteRequestHooks = new ArrayList<>();
        siteRequestHooks.add(getSiteRequestHook());
        when(applicationStateMock.getSiteRequestHooks()).thenReturn(siteRequestHooks);
        when(applicationStateMock.getUserDetails()).thenReturn(getUserDetails());

        var result = siteRequestController.approveSite("{\n" +
                "\"\": \"url\",\n" +
                "\"userDetail\": [\n" +
                "{\n" +
                "\"detailName\": \"name\",\n" +
                "\"required\": true,\n" +
                "              \"reason\": \"nameReason\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"detailName\": \"dob\",\n" +
                "              \"required\": true,\n" +
                "              \"reason\": \"dobReason\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"detailName\": \"aadhar\",\n" +
                "              \"required\": false,\n" +
                "              \"reason\": \"aadharReason\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"detailName\": \"pan\",\n" +
                "              \"required\": true,\n" +
                "              \"reason\": \"panReason\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }");

        Assert.assertEquals(result, HttpStatus.NO_CONTENT);
    }

    @Test
    public void testForGetUserDetailsFor() {
        MockitoAnnotations.initMocks(this);

        List<SiteRequestHook> siteRequestHooks = new ArrayList<>();
        siteRequestHooks.add(getSiteRequestHook());
        when(applicationStateMock.getSiteRequestHooks()).thenReturn(siteRequestHooks);
        when(applicationStateMock.getUserDetails()).thenReturn(getUserDetails());

        var result = siteRequestController.getUserDetailsFor("12345");
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);

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

    private SiteRequestHook getSiteRequestHook() {
        SiteRequestHook siteRequestHook = new SiteRequestHook();
        siteRequestHook.setApproved(true);
        siteRequestHook.setUniqueId("12345");
        siteRequestHook.setSiteRequest(getSiteRequest());
        return siteRequestHook;
    }

    private List<UserDetail> getUserDetails() {

        UserDetail userNameDetail = new UserDetail("name", null, "Mozilla");

        UserDetail userDobDetail = new UserDetail("dob", null, "September 23, 2002");

        UserDetail userAadharDetail = new UserDetail("aadhar", null, "000000000000");

        UserDetail userPanDetail = new UserDetail("pan", null, "AFTJUKNHG");
        List<UserDetail> userDetailsList = new ArrayList<>();

        userDetailsList.add(userNameDetail);
        userDetailsList.add(userDobDetail);
        userDetailsList.add(userAadharDetail);
        userDetailsList.add(userPanDetail);

        return userDetailsList;
    }
}
