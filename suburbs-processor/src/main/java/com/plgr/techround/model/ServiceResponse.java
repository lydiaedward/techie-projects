package com.plgr.techround.model;

import java.io.Serializable;

/**
 * Base Response model. Can be used to define any common attributes.
 */
public class ServiceResponse implements Serializable {
    private ResponseContext responseContext;

    public ResponseContext getResponseContext() {
        if(responseContext == null) {
            responseContext = new ResponseContext();
        }
        return responseContext;
    }

    public void setResponseContext(ResponseContext responseContext) {
        this.responseContext = responseContext;
    }
}
