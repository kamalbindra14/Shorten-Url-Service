package com.zycus.app.controller;

import com.zycus.app.model.UrlModel;
import com.zycus.app.services.intf.RedirectionServiceInterface;
import com.zycus.app.services.intf.ShorteningServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    private ShorteningServiceInterface shorteningService;

    @Autowired
    private RedirectionServiceInterface redirectionService;

    @Value("${app.scheme}")
    private String scheme;

    @PostMapping(value = "/urlShorteningService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UrlModel shortenUrl(@RequestBody UrlModel urlReq) {
        if (urlReq == null || urlReq.getUrl().isEmpty()) {
            return null;
        }
        String shortUrl = shorteningService.shortenUrl(urlReq);
        UriComponents uri = UriComponentsBuilder.newInstance().scheme(scheme).host(shortUrl).path("/").build();
        UrlModel resp = new UrlModel();
        resp.setUrl(uri.toUriString());
        return resp;
    }

    @PostMapping(value = "/shortUrlRedirectService", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RedirectView redirectShortUrl(@RequestBody UrlModel requestInput) throws MalformedURLException{
        if (requestInput == null || requestInput.getUrl().isEmpty()) {
            return null;
        }
        URL url = new URL(requestInput.getUrl());
        String redirectUrl = redirectionService.redirectUrl(url.getHost());
        return new RedirectView(redirectUrl);
    }
}