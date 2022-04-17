package com.plgr.techround.controller;

import com.plgr.techround.SuburbsProcessorApplication;
import com.plgr.techround.constant.CommonConstants;
import com.plgr.techround.model.*;
import com.plgr.techround.service.SuburbsService;
import com.plgr.techround.test.util.TestDataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuburbsProcessorApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class SuburbsAPIControllerTest {

    @Autowired
    private SuburbsService suburbsService;

    @Autowired
    private SuburbsAPIController suburbsAPIController;

    private AddSuburbsRequest addSuburbsRequest;
    private SearchSuburbsRequest searchSuburbsRequest;

    @Before
    public void setupdata() {
        addSuburbsRequest = new AddSuburbsRequest();
        addSuburbsRequest.setSuburbDetails(TestDataUtil.mockSuburbsData());
        searchSuburbsRequest = new SearchSuburbsRequest();
        searchSuburbsRequest.setSearchCriteria(TestDataUtil.mockSearchCriteria());
    }
    @Test
    public void testAddSuburbs() {
        ServiceResponse response = suburbsAPIController.add(addSuburbsRequest);
        assertNotNull(response);
        assertEquals(CommonConstants.SUCCESS_CODE, response.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.SUCCESS_DESC, response.getResponseContext().getResponseDescription());
    }

    @Test
    public void testSearchSuburbs() {
        //Add some records before executing search API
        suburbsAPIController.add(addSuburbsRequest);

        SearchSuburbsResponse response = suburbsAPIController.search(searchSuburbsRequest);
        assertNotNull(response);
        assertEquals(CommonConstants.SUCCESS_CODE, response.getResponseContext().getResponseCode());
        assertEquals(CommonConstants.SUCCESS_DESC, response.getResponseContext().getResponseDescription());
        assertEquals(2, response.getSuburbDetails().size());
        assertEquals(24, response.getTotalCharactersCount());
    }
}
