package com.plgr.techround.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Search Criteria Model
 */
@Data
public class SearchCriteria implements Serializable {
    private int startPostCode;
    private int endPostCode;
}
