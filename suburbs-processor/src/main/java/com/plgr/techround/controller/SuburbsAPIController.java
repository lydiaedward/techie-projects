package com.plgr.techround.controller;

import com.plgr.techround.model.AddSuburbsRequest;
import com.plgr.techround.model.SearchSuburbsRequest;
import com.plgr.techround.model.SearchSuburbsResponse;
import com.plgr.techround.model.ServiceResponse;
import com.plgr.techround.service.SuburbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for the Suburbs Add and Search APIs.
 */
@RestController
public class SuburbsAPIController {

    @Autowired
    SuburbsService suburbsService;

    @PostMapping(value= "/suburbs/add", consumes = "application/json", produces = "application/json")
    public ServiceResponse add(@RequestBody AddSuburbsRequest addSuburbsRequest) {
        return suburbsService.addSuburbs(addSuburbsRequest);
    }

    @PostMapping(value= "/suburbs/search", consumes = "application/json", produces = "application/json")
    public SearchSuburbsResponse search(@RequestBody SearchSuburbsRequest searchSuburbsRequest) {
        return suburbsService.searchSuburbs(searchSuburbsRequest);
    }

}
