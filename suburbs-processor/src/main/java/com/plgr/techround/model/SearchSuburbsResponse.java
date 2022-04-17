package com.plgr.techround.model;

import lombok.Data;

import java.util.List;

/**
 * Search Suburbs Response Model
 */
@Data
public class SearchSuburbsResponse extends ServiceResponse {
    private List<String> suburbDetails;
    private int totalCharactersCount;
}
