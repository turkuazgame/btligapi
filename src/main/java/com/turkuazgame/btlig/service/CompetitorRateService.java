package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.CompetitorMatch;
import com.turkuazgame.btlig.entity.CompetitorRate;
import com.turkuazgame.btlig.entity.MatchRate;
import com.turkuazgame.btlig.repository.*;
import com.turkuazgame.btlig.request.CompetitorRateRequest;
import com.turkuazgame.btlig.response.CompetitorRateResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitorRateService {

    CompetitorRateRepository competitorRateRepository;
    CompetitorMatchRepository competitorMatchRepository;
    MatchRateRepository matchRateRepository;
    BaseService service;

    @Autowired
    public CompetitorRateService(CompetitorRateRepository competitorRateRepository,
                                 CompetitorMatchRepository competitorMatchRepository,
                                 MatchRateRepository matchRateRepository) {
        this.competitorRateRepository = competitorRateRepository;
        this.competitorMatchRepository = competitorMatchRepository;
        this.matchRateRepository = matchRateRepository;
        this.service = new BaseService(competitorRateRepository, CompetitorRate.class, CompetitorRateResponse.class);
    }

    public List<CompetitorRateResponse> getCompetitorRates(Optional<Long> competitorMatchId, Optional<Long> matchRateId){
        if(competitorMatchId.isPresent() && matchRateId.isPresent())
            return getCompetitorRateByCompetitorMatchAndMatchRate(competitorMatchId.get(), matchRateId.get());
        else if(competitorMatchId.isPresent())
            return getCompetitorRateByCompetitorMatch(competitorMatchId.get());
        else if(matchRateId.isPresent())
            return getCompetitorRateByMatchRate(matchRateId.get());
        else
            return getAllCompetitorRates();
    }
    public List<CompetitorRateResponse> getAllCompetitorRates(){
        List<CompetitorRateResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((CompetitorRateResponse) iresponse);
        return responseList;
    }

    public CompetitorRateResponse getCompetitorRate(Long competitorRateId) {
        return (CompetitorRateResponse) service.getEntity(competitorRateId);
    }

    public CompetitorRateResponse createCompetitorRate(CompetitorRateRequest competitorRateRequest) {
        CompetitorMatch competitorMatch = competitorMatchRepository.findById(competitorRateRequest.getCompetitorMatchId()).orElse(null);
        competitorRateRequest.setCompetitorMatch(competitorMatch);
        MatchRate matchRate = matchRateRepository.findById(competitorRateRequest.getMatchRateId()).orElse(null);
        competitorRateRequest.setMatchRate(matchRate);
        return (CompetitorRateResponse) service.createEntity(competitorRateRequest);
    }

    public CompetitorRateResponse updateCompetitorRate(Long competitorRateId, CompetitorRateRequest competitorRateRequest) {
        CompetitorMatch competitorMatch = competitorMatchRepository.findById(competitorRateRequest.getCompetitorMatchId()).orElse(null);
        competitorRateRequest.setCompetitorMatch(competitorMatch);
        MatchRate matchRate = matchRateRepository.findById(competitorRateRequest.getMatchRateId()).orElse(null);
        competitorRateRequest.setMatchRate(matchRate);
        return (CompetitorRateResponse) service.updateEntity(competitorRateId, competitorRateRequest);
    }

    public CompetitorRateResponse mergeCompetitorRate(Long competitorRateId, Map<Object, Object> fields) {
        if(fields.containsKey("competitorMatchId")) {
            CompetitorMatch competitorMatch = competitorMatchRepository.findById(Long.parseLong(fields.get("competitorMatchId").toString())).orElse(null);
            fields.remove("competitorMatchId");
            fields.put("competitorMatch", competitorMatch);
        }
        if(fields.containsKey("matchRateId")) {
            MatchRate matchRate = matchRateRepository.findById(Long.parseLong(fields.get("matchRateId").toString())).orElse(null);
            fields.remove("matchRateId");
            fields.put("matchRate", matchRate);
        }
        return (CompetitorRateResponse) service.mergeEntity(competitorRateId,fields);
    }

    public void deleteCompetitorRate(Long competitorRateId) {
        service.deleteEntity(competitorRateId);
    }

    public List<CompetitorRateResponse> getCompetitorRateByCompetitorMatch(Long competitorMatchId) {
        if(competitorMatchId!=null && competitorMatchId>0) {
            CompetitorMatch competitorMatch = competitorMatchRepository.findById(competitorMatchId).orElse(null);
            if(competitorMatch!=null) {
                List<CompetitorRate> list = competitorRateRepository.findByCompetitorMatch(competitorMatch);
                List<CompetitorRateResponse> responseList = new ArrayList<>();
                for(CompetitorRate competitorRate : list) {
                    CompetitorRateResponse response = new CompetitorRateResponse(competitorRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorRateResponse> getCompetitorRateByMatchRate(Long matchRateId) {
        if(matchRateId!=null && matchRateId>0) {
            MatchRate matchRate = matchRateRepository.findById(matchRateId).orElse(null);
            if(matchRate !=null) {
                List<CompetitorRate> list = competitorRateRepository.findByMatchRate(matchRate);
                List<CompetitorRateResponse> responseList = new ArrayList<>();
                for(CompetitorRate competitorRate : list) {
                    CompetitorRateResponse response = new CompetitorRateResponse(competitorRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorRateResponse> getCompetitorRateByCompetitorMatchAndMatchRate(Long competitorMatchId, Long matchRateId) {
        if(competitorMatchId!=null && competitorMatchId>0 && matchRateId!=null && matchRateId>0) {
            CompetitorMatch competitorMatch = competitorMatchRepository.findById(competitorMatchId).orElse(null);
            MatchRate matchRate = matchRateRepository.findById(matchRateId).orElse(null);
            if(competitorMatch!=null && matchRate !=null) {
                List<CompetitorRate> list = competitorRateRepository.findByCompetitorMatchAndMatchRate(competitorMatch, matchRate);
                List<CompetitorRateResponse> responseList = new ArrayList<>();
                for(CompetitorRate competitorRate : list) {
                    CompetitorRateResponse response = new CompetitorRateResponse(competitorRate);
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
