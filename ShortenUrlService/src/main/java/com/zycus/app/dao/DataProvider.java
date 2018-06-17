package com.zycus.app.dao;

import com.zycus.app.model.UrlModel;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DataProvider {
    private final Map<Long, String> urlMap;

    public DataProvider() {
        this.urlMap = new ConcurrentHashMap<>();
    }

    public Map<Long, String> getUrlMap() {
        return urlMap;
    }
}