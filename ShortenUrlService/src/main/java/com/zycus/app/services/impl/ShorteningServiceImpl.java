package com.zycus.app.services.impl;

import com.zycus.app.dao.DataProvider;
import com.zycus.app.model.UrlModel;
import com.zycus.app.services.intf.ShorteningServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("shorteningService")
public class ShorteningServiceImpl implements ShorteningServiceInterface {
    @Autowired
    private DataProvider dataProvider;

    @Override
    public String shortenUrl(UrlModel urlObj) {
        long id = generateKey(urlObj.getUrl());
        dataProvider.getUrlMap().put(id, urlObj.getUrl());
        return idToShortUrl(id);
    }

    private long generateKey(String str) {
        long result = 1;
        result = 31 * result + str.hashCode();
        return result;
    }

    private String idToShortUrl(long n) {
        // Map to store 62 possible characters
        char[] map = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        StringBuilder shortUrl = new StringBuilder();

        // Convert given id to a base 62 number
        while (n > 0) {
            // store actual characte in short url
            shortUrl.append(map[Math.toIntExact(n % 62)]);
            n = n / 62;
        }

        // Reverse shortUrl to complete base conversion
        shortUrl = shortUrl.reverse();

        return shortUrl.toString();
    }
}