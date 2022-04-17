package com.plgr.techround.service.impl;

import com.plgr.techround.constant.CommonConstants;
import com.plgr.techround.domain.repo.SuburbRepo;
import com.plgr.techround.exception.ValidationException;
import com.plgr.techround.model.*;
import com.plgr.techround.service.SuburbsService;
import com.plgr.techround.util.APICommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation class that contains the business logic
 * for add and search Suburbs.
 */
@Slf4j
@Component
public class SuburbsServiceImpl implements SuburbsService {

    @Autowired
    private SuburbRepo suburbRepo;

    /**
     * This method adds the suburbDetails provided in request to DB.
     *
     * @param addSuburbsRequest
     * @return
     */
    @Override
    public ServiceResponse addSuburbs(AddSuburbsRequest addSuburbsRequest) {
        log.debug("addSuburbs() method - Start");
        ServiceResponse serviceResponse = new ServiceResponse();
        try {
            validateAddRequest(addSuburbsRequest);
            addSuburbsRequest.getSuburbDetails().stream().forEach(suburb -> {
                suburbRepo.save(suburb);
            });
            log.info("Successfully saved {} Suburb records to DB", addSuburbsRequest.getSuburbDetails().size());
            serviceResponse.setResponseContext(APICommonUtil.getResponseContext(CommonConstants.SUCCESS_CODE, CommonConstants.SUCCESS_DESC));
        } catch (ValidationException validationException) {
            log.error("ValidationException occurred in addSuburbs() method ", validationException);
            serviceResponse.setResponseContext(APICommonUtil.getResponseContext(validationException.getErrorCode(), validationException.getErrorDescription()));
        } catch (Exception e) {
            log.error("Exception occurred while saving the suburb details.", e);
            serviceResponse.setResponseContext(APICommonUtil.getResponseContext(CommonConstants.ERRORCODE_999, CommonConstants.ERRORDESC_999));
        }
        log.debug("addSuburbs() method - End");
        return serviceResponse;
    }

    /**
     * This method searches for the suburbs within the postcode range provided
     * in request.
     *
     * @param searchSuburbsRequest
     * @return searchSuburbsResponse
     */
    @Override
    public SearchSuburbsResponse searchSuburbs(SearchSuburbsRequest searchSuburbsRequest) {
        log.debug("searchSuburbs() method - Start");
        SearchSuburbsResponse searchSuburbsResponse = new SearchSuburbsResponse();
        try {
            validateSearchRequest(searchSuburbsRequest);
            SearchCriteria searchCriteria = searchSuburbsRequest.getSearchCriteria();
            log.info("Start Code {} End Code {}", searchCriteria.getStartPostCode(), searchCriteria.getEndPostCode());

            List<String> suburbsResult = suburbRepo.findByPostCodeRange(searchCriteria.getStartPostCode(), searchCriteria.getEndPostCode());
            log.info("Result {} Size {}", suburbsResult, suburbsResult.size());
            if (suburbsResult.isEmpty()) {
                log.info("No results found");
                searchSuburbsResponse.setResponseContext(APICommonUtil.getResponseContext(CommonConstants.NO_RESULTS_CODE, CommonConstants.NO_RESULTS_DESC));
            } else {
                searchSuburbsResponse.setSuburbDetails(suburbsResult.stream().sorted().collect(Collectors.toList()));
                searchSuburbsResponse.setTotalCharactersCount(suburbsResult.stream().mapToInt(String::length).sum());
                searchSuburbsResponse.setResponseContext(APICommonUtil.getResponseContext(CommonConstants.SUCCESS_CODE, CommonConstants.SUCCESS_DESC));
            }
        } catch (ValidationException validationException) {
            log.error("ValidationException occurred in searchSuburbs() method.", validationException);
            searchSuburbsResponse.setResponseContext(APICommonUtil.getResponseContext(validationException.getErrorCode(), validationException.getErrorDescription()));
        } catch (Exception e) {
            log.error("Exception occurred while searching suburb details.", e);
            searchSuburbsResponse.setResponseContext(APICommonUtil.getResponseContext(CommonConstants.ERRORCODE_999, CommonConstants.ERRORDESC_999));
        }
        log.debug("searchSuburbs() method - End");
        return searchSuburbsResponse;
    }

    /**
     * Validate the Search Request.
     *
     * @param searchSuburbsRequest
     * @throws ValidationException
     */
    private void validateSearchRequest(SearchSuburbsRequest searchSuburbsRequest) throws ValidationException {
        if (searchSuburbsRequest.getSearchCriteria() == null || searchSuburbsRequest.getSearchCriteria().getEndPostCode() == 0
                || searchSuburbsRequest.getSearchCriteria().getStartPostCode() == 0) {
            throw new ValidationException(CommonConstants.ERRORCODE_002, CommonConstants.ERRORDESC_002);
        } else if (searchSuburbsRequest.getSearchCriteria().getStartPostCode() > searchSuburbsRequest.getSearchCriteria().getEndPostCode()) {
            throw new ValidationException(CommonConstants.ERRORCODE_001, CommonConstants.ERRORDESC_001);
        }
    }

    /**
     * Validate Add Request.
     *
     * @param addSuburbsRequest
     * @throws ValidationException
     */
    private void validateAddRequest(AddSuburbsRequest addSuburbsRequest) throws ValidationException {
        if (CollectionUtils.isEmpty(addSuburbsRequest.getSuburbDetails())) {
            throw new ValidationException(CommonConstants.ERRORCODE_002, CommonConstants.ERRORDESC_002);
        }
    }

}
