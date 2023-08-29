package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.League;
import com.turkuazgame.btlig.entity.RateType;
import com.turkuazgame.btlig.entity.Rate;
import com.turkuazgame.btlig.exception.ResourceNotFoundException;
import com.turkuazgame.btlig.repository.RateTypeRepository;
import com.turkuazgame.btlig.repository.RateRepository;
import com.turkuazgame.btlig.request.RateRequest;
import com.turkuazgame.btlig.response.LeagueResponse;
import com.turkuazgame.btlig.response.RateResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RateService {

    RateRepository rateRepository;
    RateTypeRepository rateTypeRepository;
    BaseService service;

    @Autowired
    public RateService(RateRepository rateRepository, RateTypeRepository rateTypeRepository) {
        this.rateRepository = rateRepository;
        this.rateTypeRepository = rateTypeRepository;
        this.service = new BaseService(rateRepository, Rate.class, RateResponse.class);
    }

    public List<RateResponse> getRates(Optional<Long> rateTypeId){
        if(rateTypeId.isPresent())
            return getRatesByRateType(rateTypeId.get());
        else
            return getAllRates();
    }

    public List<RateResponse> getAllRates(){
        List<RateResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((RateResponse) iresponse);
        return responseList;
    }

    public RateResponse getRate(Long rateId) {
        return (RateResponse) service.getEntity(rateId);
    }

    public RateResponse createRate(RateRequest rateRequest) throws ResourceNotFoundException {
        RateType rateType = rateTypeRepository.findById(rateRequest.getRateTypeId()).orElse(null);
        rateRequest.setRateType(rateType);
        return (RateResponse) service.createEntity(rateRequest);
    }

    public RateResponse updateRate(Long rateId, RateRequest rateRequest) {
        RateType rateType = rateTypeRepository.findById(rateRequest.getRateTypeId()).orElse(null);
        rateRequest.setRateType(rateType);
        return (RateResponse) service.updateEntity(rateId, rateRequest);
    }

    public RateResponse mergeRate(Long rateId, Map<Object, Object> fields) {
        if(fields.containsKey("rateTypeId")) {
            RateType rateType = rateTypeRepository.findById(Long.parseLong(fields.get("rateTypeId").toString())).orElse(null);
            fields.remove("rateTypeId");
            fields.put("rateType", rateType);
        }
        return (RateResponse) service.mergeEntity(rateId,fields);
    }

    public void deleteRate(Long rateId) {
        service.deleteEntity(rateId);
    }

    public List<RateResponse> getRatesByRateType(Long rateTypeId) {
        if(rateTypeId!=null && rateTypeId>0) {
            RateType rateType = rateTypeRepository.findById(rateTypeId).orElse(null);
            if(rateType!=null) {
                List<Rate> list = rateRepository.findByRateType(rateType);
                List<RateResponse> responseList = new ArrayList<>();
                for(Rate rate : list) {
                    RateResponse response = new RateResponse(rate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public RateResponse getRateByCode(String rateCode) {
        if(rateCode!=null && !rateCode.equals("")) {
            List<Rate> list = rateRepository.findByRateCode(rateCode);
            Rate rate = !list.isEmpty() ? list.get(0) : null;
            RateResponse response = rate!=null ? new RateResponse(rate) : null;
            return response;
        } else
            return null;
    }

}
