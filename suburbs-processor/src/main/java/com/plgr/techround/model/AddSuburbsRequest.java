package com.plgr.techround.model;

import com.plgr.techround.domain.entity.Suburb;
import lombok.Data;

import java.util.List;

/**
 * Add Suburbs Request Class
 */
@Data
public class AddSuburbsRequest extends ServiceRequest {
    private List<Suburb> suburbDetails;
}
