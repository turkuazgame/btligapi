package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.RateType;
import com.turkuazgame.btlig.repository.RateTypeRepository;
import com.turkuazgame.btlig.request.RateTypeRequest;
import com.turkuazgame.btlig.response.RateTypeResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RateTypeService {

    RateTypeRepository rateTypeRepository;
    BaseService service;

    @Autowired
    public RateTypeService(RateTypeRepository rateTypeRepository) {
        this.rateTypeRepository = rateTypeRepository;
        this.service = new BaseService(rateTypeRepository, RateType.class, RateTypeResponse.class);
    }

    public List<RateTypeResponse> getAllRateTypes(){
        List<RateTypeResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((RateTypeResponse) iresponse);
        return responseList;
    }

    public RateTypeResponse getRateType(Long rateTypeId) {
        return (RateTypeResponse) service.getEntity(rateTypeId);
    }

    public RateTypeResponse createRateType(RateTypeRequest rateTypeRequest) {
        return (RateTypeResponse) service.createEntity(rateTypeRequest);
    }

    public RateTypeResponse updateRateType(Long rateTypeId, RateTypeRequest rateTypeRequest) {
        return (RateTypeResponse) service.updateEntity(rateTypeId, rateTypeRequest);
    }

    public RateTypeResponse mergeRateType(Long rateTypeId, Map<Object, Object> fields) {
        return (RateTypeResponse) service.mergeEntity(rateTypeId,fields);
    }

    public void deleteRateType(Long rateTypeId) {
        service.deleteEntity(rateTypeId);
    }

}
