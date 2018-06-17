package com.zycus.app.services.impl;

import com.zycus.app.dao.DataProvider;
import com.zycus.app.model.UrlModel;
import com.zycus.app.services.intf.RedirectionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("redirectionService")
public class RedirectionServiceImpl implements RedirectionServiceInterface {
    @Autowired
    DataProvider dataProvider;

    @Override
    public String redirectUrl(String shortUrlToRedirect) {
        if (shortUrlToRedirect == null) {
            return null;
        }
        long id = shortUrlToId(shortUrlToRedirect);
        Map<Long, String> map = dataProvider.getUrlMap();
        if (map.containsKey(id)) {
            return map.get(id);
        }
        return null;
    }

    // Function to get ID back from a short url
    private long shortUrlToId(String shortUrl) {
        long id = 0;

        // Base conversion logic
        for (int i = 0; i < shortUrl.length(); i++) {
            if ('a' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'z')
                id = id * 62 + shortUrl.charAt(i) - 'a';
            if ('A' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'Z')
                id = id * 62 + shortUrl.charAt(i) - 'A' + 26;
            if ('0' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= '9')
                id = id * 62 + shortUrl.charAt(i) - '0' + 52;
        }
        return id;
    }
}
