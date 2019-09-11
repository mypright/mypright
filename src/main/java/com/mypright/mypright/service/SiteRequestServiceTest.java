package com.mypright.mypright.service;

import com.mypright.mypright.model.SiteRequest;
import com.mypright.mypright.model.SiteRequestHook;
import com.mypright.mypright.state.ApplicationState;
import lombok.var;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class SiteRequestServiceTest {


    @InjectMocks
    SiteRequestService siteRequestService;
    @MockBean
    SiteRequestHook siteRequestHookMock;
    @SpyBean
    RestTemplate restTemplate;
    @MockBean
    ApplicationState applicationState;

    @Test
    public void testForCreateSiteRequestHook() {
        MockitoAnnotations.initMocks(this);
        var result = siteRequestService.createSiteRequestHook(new SiteRequest(),"12345");
        Assert.assertNotNull(result);
    }

    @Test
    public void testForGetAllSiteRequestHooks() {
        MockitoAnnotations.initMocks(this);
        var result = siteRequestService.getAllSiteRequestHooks();
        Assert.assertNotNull(result);
    }

    @Ignore
    @Test
    public void testForFetchPortalDetails() {
        MockitoAnnotations.initMocks(this);

        doReturn(new ResponseEntity<>(HttpStatus.OK)).
                when(restTemplate).exchange("12345",
                HttpMethod.GET,
                new HttpEntity<>(new LinkedMultiValueMap<String, String>(), new HttpHeaders()),
                SiteRequestHook.class).getBody();

        var result = siteRequestService.fetchPortalDetails("12345");
        Assert.assertNotNull(result);

    }
}
