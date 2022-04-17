package com.plgr.techround.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Response Context to return the response code and description
 * for any API.
 */
@Data
public class ResponseContext implements Serializable {
    private String responseCode;
    private String responseDescription;
}
