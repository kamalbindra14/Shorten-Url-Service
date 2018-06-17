package com.zycus.app.controller;

import com.zycus.app.services.intf.ShorteningServiceInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppController.class, secure = false)
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String expected = "{\"url\": \"http://BA5Wv/}";

    private String redirectUrl = "https://gist.github.com/nguyennhatlinh1990/b6ecfc702c1bf0852f6b";

    @Test
    public void shortenUrl() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/app/urlShorteningService").accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{url\": \"https://gist.github.com/nguyennhatlinh1990/b6ecfc702c1bf0852f6b\"}").contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void redirectShortUrl() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/app/shortUrlRedirectService").accept(MediaType.APPLICATION_JSON_VALUE)
                .content("{url\": \"http://BA5Wv/}").contentType(MediaType.APPLICATION_JSON_VALUE);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(redirectUrl, result.getResponse().getRedirectedUrl());
    }
}