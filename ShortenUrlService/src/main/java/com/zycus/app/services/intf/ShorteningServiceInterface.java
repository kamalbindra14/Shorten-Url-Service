package com.zycus.app.services.intf;

import com.zycus.app.model.UrlModel;

public interface ShorteningServiceInterface {
    String shortenUrl(UrlModel UrlToShorten);
}