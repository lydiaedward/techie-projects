package com.plgr.techround.service.impl;

import com.plgr.techround.constant.CommonConstants;
import com.plgr.techround.domain.entity.Suburb;
import com.plgr.techround.domain.repo.SuburbRepo;
import com.plgr.techround.model.*;
import com.plgr.techround.test.util.TestDataUtil;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JMockit.class)
public class SuburbsServiceImplTest {

    @Tested
    private SuburbsServiceImpl suburbsService;

    @Injectable
    private SuburbRepo suburbRepo;

    @Tested
    private AddSuburbsRequest addSuburbsRequest;

    @Tested
    private SearchSuburbsRequest searchSuburbsRequest;

    @Before
    public void setupData() {

    }

    /** Positive Scenario Tests - Start **/
    @Test
    public void testAddSuburb() {
        setupAddRequest();
        // Assert that Repo Save is called twice
        new Expectations(){
            {
                suburbRepo.save((Suburb) any);
                times = 2;
            }
        };
        ServiceResponse serviceResponse = suburbsService.addSuburbs(addSuburbsRequest);
        assertEquals(CommonConstants.SUCCESS_CODE, serviceResponse.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.SUCCESS_DESC, serviceResponse.getResponseContext().getResponseDescription());
    }

    @Test
    public void testSearchSuburbs() {
        setupSearchRequest();
        new Expectations() {
            {
                suburbRepo.findByPostCodeRange(anyInt, anyInt);
                result = getSearchResult();
            }
        };
        SearchSuburbsResponse response = suburbsService.searchSuburbs(searchSuburbsRequest);
        assertEquals(6, response.getSuburbDetails().size());
        //Assert if result is arranged alphabetically
        assertEquals("East Perth", response.getSuburbDetails().get(0));
        assertEquals("Perth", response.getSuburbDetails().get(5));
    }
    /** Positive Scenario Tests - End **/

    /** Negative Scenario Tests - Start **/
    @Test
    public void testInvalidAddRequest() {
        ServiceResponse serviceResponse = suburbsService.addSuburbs(addSuburbsRequest);
        assertEquals(CommonConstants.ERRORCODE_002, serviceResponse.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.ERRORDESC_002, serviceResponse.getResponseContext().getResponseDescription());
    }

    @Test
    public void testAddRequestException() {
        setupAddRequest();
        new Expectations() {
            {
                suburbRepo.save((Suburb) any);
                result = new Exception();
            }
        };
        ServiceResponse serviceResponse = suburbsService.addSuburbs(addSuburbsRequest);
        assertEquals(CommonConstants.ERRORCODE_999, serviceResponse.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.ERRORDESC_999, serviceResponse.getResponseContext().getResponseDescription());
    }

    @Test
    public void testSearchRequestException() {
        setupSearchRequest();
        new Expectations() {
            {
                suburbRepo.findByPostCodeRange(anyInt, anyInt);
                result = new Exception();
            }
        };
        ServiceResponse serviceResponse = suburbsService.searchSuburbs(searchSuburbsRequest);
        assertEquals(CommonConstants.ERRORCODE_999, serviceResponse.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.ERRORDESC_999, serviceResponse.getResponseContext().getResponseDescription());
    }

    @Test
    public void testInvalidSearchRequest() {
        SearchSuburbsResponse serviceResponse = suburbsService.searchSuburbs(searchSuburbsRequest);
        assertEquals(CommonConstants.ERRORCODE_002, serviceResponse.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.ERRORDESC_002, serviceResponse.getResponseContext().getResponseDescription());
    }

    @Test
    public void testInvalidSearchRequest1() {
        setupInvalidSearchRequest();
        SearchSuburbsResponse serviceResponse = suburbsService.searchSuburbs(searchSuburbsRequest);
        assertEquals(CommonConstants.ERRORCODE_001, serviceResponse.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.ERRORDESC_001, serviceResponse.getResponseContext().getResponseDescription());
    }

    /** Negative Scenario Tests - End **/

    private void setupAddRequest() {
        addSuburbsRequest.setSuburbDetails(TestDataUtil.mockSuburbsData());
    }

    private void setupSearchRequest() {
        searchSuburbsRequest.setSearchCriteria(TestDataUtil.mockSearchCriteria());
    }

    private void setupInvalidSearchRequest() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setEndPostCode(1000);
        searchCriteria.setStartPostCode(1030);
        searchSuburbsRequest.setSearchCriteria(searchCriteria);
    }

    private List<String> getSearchResult() {
        List<String> suburbs = new ArrayList<>();
        suburbs.add("Perth");
        suburbs.add("Highgate");
        suburbs.add("East Perth");
        suburbs.add("Kings Park");
        suburbs.add("North Park");
        suburbs.add("Leederville");
        return suburbs;
    }

}
