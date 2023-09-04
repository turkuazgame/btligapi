package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.CompetitorWeek;
import com.turkuazgame.btlig.entity.CompetitorMatch;
import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.repository.*;
import com.turkuazgame.btlig.request.CompetitorMatchRequest;
import com.turkuazgame.btlig.response.CompetitorMatchResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitorMatchService {

    CompetitorMatchRepository competitorMatchRepository;
    CompetitorWeekRepository competitorWeekRepository;
    MatchRepository matchRepository;
    BaseService service;

    @Autowired
    public CompetitorMatchService(CompetitorMatchRepository competitorMatchRepository,
                                  CompetitorWeekRepository competitorWeekRepository,
                                  MatchRepository matchRepository) {
        this.competitorMatchRepository = competitorMatchRepository;
        this.competitorWeekRepository = competitorWeekRepository;
        this.matchRepository = matchRepository;
        this.service = new BaseService(competitorMatchRepository, CompetitorMatch.class, CompetitorMatchResponse.class);
    }

    public List<CompetitorMatchResponse> getCompetitorMatchs(Optional<Long> competitorWeekId, Optional<Long> matchId){
        if(competitorWeekId.isPresent() && matchId.isPresent())
            return getCompetitorMatchByCompetitorWeekAndMatch(competitorWeekId.get(), matchId.get());
        else if(competitorWeekId.isPresent())
            return getCompetitorMatchByCompetitorWeek(competitorWeekId.get());
        else if(matchId.isPresent())
            return getCompetitorMatchByMatch(matchId.get());
        else
            return getAllCompetitorMatchs();
    }
    public List<CompetitorMatchResponse> getAllCompetitorMatchs(){
        List<CompetitorMatchResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((CompetitorMatchResponse) iresponse);
        return responseList;
    }

    public CompetitorMatchResponse getCompetitorMatch(Long competitorMatchId) {
        return (CompetitorMatchResponse) service.getEntity(competitorMatchId);
    }

    public CompetitorMatchResponse createCompetitorMatch(CompetitorMatchRequest competitorMatchRequest) {
        CompetitorWeek competitorWeek = competitorWeekRepository.findById(competitorMatchRequest.getCompetitorWeekId()).orElse(null);
        competitorMatchRequest.setCompetitorWeek(competitorWeek);
        Match match = matchRepository.findById(competitorMatchRequest.getMatchId()).orElse(null);
        competitorMatchRequest.setMatch(match);
        return (CompetitorMatchResponse) service.createEntity(competitorMatchRequest);
    }

    public CompetitorMatchResponse updateCompetitorMatch(Long competitorMatchId, CompetitorMatchRequest competitorMatchRequest) {
        CompetitorWeek competitorWeek = competitorWeekRepository.findById(competitorMatchRequest.getCompetitorWeekId()).orElse(null);
        competitorMatchRequest.setCompetitorWeek(competitorWeek);
        Match match = matchRepository.findById(competitorMatchRequest.getMatchId()).orElse(null);
        competitorMatchRequest.setMatch(match);
        return (CompetitorMatchResponse) service.updateEntity(competitorMatchId, competitorMatchRequest);
    }

    public CompetitorMatchResponse mergeCompetitorMatch(Long competitorMatchId, Map<Object, Object> fields) {
        if(fields.containsKey("competitorWeekId")) {
            CompetitorWeek competitorWeek = competitorWeekRepository.findById(Long.parseLong(fields.get("competitorWeekId").toString())).orElse(null);
            fields.remove("competitorWeekId");
            fields.put("competitorWeek", competitorWeek);
        }
        if(fields.containsKey("matchId")) {
            Match match = matchRepository.findById(Long.parseLong(fields.get("matchId").toString())).orElse(null);
            fields.remove("matchId");
            fields.put("match", match);
        }
        return (CompetitorMatchResponse) service.mergeEntity(competitorMatchId,fields);
    }

    public void deleteCompetitorMatch(Long competitorMatchId) {
        service.deleteEntity(competitorMatchId);
    }

    public List<CompetitorMatchResponse> getCompetitorMatchByCompetitorWeek(Long competitorWeekId) {
        if(competitorWeekId!=null && competitorWeekId>0) {
            CompetitorWeek competitorWeek = competitorWeekRepository.findById(competitorWeekId).orElse(null);
            if(competitorWeek!=null) {
                List<CompetitorMatch> list = competitorMatchRepository.findByCompetitorWeek(competitorWeek);
                List<CompetitorMatchResponse> responseList = new ArrayList<>();
                for(CompetitorMatch competitorMatch : list) {
                    CompetitorMatchResponse response = new CompetitorMatchResponse(competitorMatch);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorMatchResponse> getCompetitorMatchByMatch(Long matchId) {
        if(matchId!=null && matchId>0) {
            Match match = matchRepository.findById(matchId).orElse(null);
            if(match !=null) {
                List<CompetitorMatch> list = competitorMatchRepository.findByMatch(match);
                List<CompetitorMatchResponse> responseList = new ArrayList<>();
                for(CompetitorMatch competitorMatch : list) {
                    CompetitorMatchResponse response = new CompetitorMatchResponse(competitorMatch);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorMatchResponse> getCompetitorMatchByCompetitorWeekAndMatch(Long competitorWeekId, Long matchId) {
        if(competitorWeekId!=null && competitorWeekId>0 && matchId!=null && matchId>0) {
            CompetitorWeek competitorWeek = competitorWeekRepository.findById(competitorWeekId).orElse(null);
            Match match = matchRepository.findById(matchId).orElse(null);
            if(competitorWeek!=null && match !=null) {
                List<CompetitorMatch> list = competitorMatchRepository.findByCompetitorWeekAndMatch(competitorWeek, match);
                List<CompetitorMatchResponse> responseList = new ArrayList<>();
                for(CompetitorMatch competitorMatch : list) {
                    CompetitorMatchResponse response = new CompetitorMatchResponse(competitorMatch);
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
