package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.entity.Rate;
import com.turkuazgame.btlig.repository.*;
import com.turkuazgame.btlig.request.MatchRateRequest;
import com.turkuazgame.btlig.response.MatchRateResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchRateService {

    MatchRateRepository matchRateRepository;
    MatchRepository matchRepository;
    RateRepository rateRepository;
    BaseService service;

    @Autowired
    public MatchRateService(MatchRateRepository matchRateRepository,
                                 MatchRepository matchRepository,
                                 RateRepository rateRepository) {
        this.matchRateRepository = matchRateRepository;
        this.matchRepository = matchRepository;
        this.rateRepository = rateRepository;
        this.service = new BaseService(matchRateRepository, MatchRate.class, MatchRateResponse.class);
    }

    public List<MatchRateResponse> getMatchRates(Optional<Long> matchId, Optional<Long> rateId){
        if(matchId.isPresent() && rateId.isPresent())
            return getMatchRateByMatchAndRate(matchId.get(), rateId.get());
        else if(matchId.isPresent())
            return getMatchRateByMatch(matchId.get());
        else if(rateId.isPresent())
            return getMatchRateByRate(rateId.get());
        else
            return getAllMatchRates();
    }
    public List<MatchRateResponse> getAllMatchRates(){
        List<MatchRateResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((MatchRateResponse) iresponse);
        return responseList;
    }

    public MatchRateResponse getMatchRate(Long matchRateId) {
        return (MatchRateResponse) service.getEntity(matchRateId);
    }

    public MatchRateResponse createMatchRate(MatchRateRequest matchRateRequest) {
        Match match = matchRepository.findById(matchRateRequest.getMatchId()).orElse(null);
        matchRateRequest.setMatch(match);
        Rate rate = rateRepository.findById(matchRateRequest.getRateId()).orElse(null);
        matchRateRequest.setRate(rate);
        return (MatchRateResponse) service.createEntity(matchRateRequest);
    }

    public MatchRateResponse updateMatchRate(Long matchRateId, MatchRateRequest matchRateRequest) {
        Match match = matchRepository.findById(matchRateRequest.getMatchId()).orElse(null);
        matchRateRequest.setMatch(match);
        Rate rate = rateRepository.findById(matchRateRequest.getRateId()).orElse(null);
        matchRateRequest.setRate(rate);
        return (MatchRateResponse) service.updateEntity(matchRateId, matchRateRequest);
    }

    public MatchRateResponse mergeMatchRate(Long matchRateId, Map<Object, Object> fields) {
        if(fields.containsKey("matchId")) {
            Match match = matchRepository.findById(Long.parseLong(fields.get("matchId").toString())).orElse(null);
            fields.remove("matchId");
            fields.put("match", match);
        }
        if(fields.containsKey("rateId")) {
            Rate rate = rateRepository.findById(Long.parseLong(fields.get("rateId").toString())).orElse(null);
            fields.remove("rateId");
            fields.put("rate", rate);
        }
        return (MatchRateResponse) service.mergeEntity(matchRateId,fields);
    }

    public void deleteMatchRate(Long matchRateId) {
        service.deleteEntity(matchRateId);
    }

    public List<MatchRateResponse> getMatchRateByMatch(Long matchId) {
        if(matchId!=null && matchId>0) {
            Match match = matchRepository.findById(matchId).orElse(null);
            if(match!=null) {
                List<MatchRate> list = matchRateRepository.findByMatch(match);
                List<MatchRateResponse> responseList = new ArrayList<>();
                for(MatchRate matchRate : list) {
                    MatchRateResponse response = new MatchRateResponse(matchRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<MatchRateResponse> getMatchRateByRate(Long rateId) {
        if(rateId!=null && rateId>0) {
            Rate rate = rateRepository.findById(rateId).orElse(null);
            if(rate !=null) {
                List<MatchRate> list = matchRateRepository.findByRate(rate);
                List<MatchRateResponse> responseList = new ArrayList<>();
                for(MatchRate matchRate : list) {
                    MatchRateResponse response = new MatchRateResponse(matchRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<MatchRateResponse> getMatchRateByMatchAndRate(Long matchId, Long rateId) {
        if(matchId!=null && matchId>0 && rateId!=null && rateId>0) {
            Match match = matchRepository.findById(matchId).orElse(null);
            Rate rate = rateRepository.findById(rateId).orElse(null);
            if(match!=null && rate !=null) {
                List<MatchRate> list = matchRateRepository.findByMatchAndRate(match, rate);
                List<MatchRateResponse> responseList = new ArrayList<>();
                for(MatchRate matchRate : list) {
                    MatchRateResponse response = new MatchRateResponse(matchRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

}
