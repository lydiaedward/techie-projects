package com.plgr.techround.model;

import lombok.Data;

/**
 * Search Suburbs Request Model
 */
@Data
public class SearchSuburbsRequest extends ServiceRequest {
    private SearchCriteria searchCriteria;
}
