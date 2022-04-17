package com.plgr.techround.test.util;

import com.plgr.techround.domain.entity.Suburb;
import com.plgr.techround.model.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static List<Suburb> mockSuburbsData() {
        Suburb suburb1 = new Suburb();
        Suburb suburb2 = new Suburb();
        suburb1.setPostCode(1001);
        suburb1.setName("Mock Suburb1");
        suburb2.setPostCode(1002);
        suburb2.setName("Mock Suburb2");
        List<Suburb> suburbs = new ArrayList<>();
        suburbs.add(suburb1);
        suburbs.add(suburb2);
        return suburbs;
    }

    public static SearchCriteria mockSearchCriteria() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setStartPostCode(1001);
        searchCriteria.setEndPostCode(1002);
        return searchCriteria;
    }
}
