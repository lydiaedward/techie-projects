package com.plgr.techround.service;

import com.plgr.techround.model.AddSuburbsRequest;
import com.plgr.techround.model.SearchSuburbsRequest;
import com.plgr.techround.model.SearchSuburbsResponse;
import com.plgr.techround.model.ServiceResponse;
import org.springframework.stereotype.Service;

/**
 * Service interface
 */
@Service("SuburbsService")
public interface SuburbsService {

    public ServiceResponse addSuburbs(AddSuburbsRequest addSuburbsRequest);
    public SearchSuburbsResponse searchSuburbs(SearchSuburbsRequest searchSuburbsRequest);

}
