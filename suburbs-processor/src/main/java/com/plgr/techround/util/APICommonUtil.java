package com.plgr.techround.util;

import com.plgr.techround.model.ResponseContext;

public class APICommonUtil {

    public static ResponseContext getResponseContext(String responseCode, String responseDescription) {
        ResponseContext responseContext = new ResponseContext();
        responseContext.setResponseCode(responseCode);
        responseContext.setResponseDescription(responseDescription);
        return responseContext;
    }
}
