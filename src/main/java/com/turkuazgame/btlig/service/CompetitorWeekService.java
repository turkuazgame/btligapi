package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.entity.CompetitorWeek;
import com.turkuazgame.btlig.entity.SeasonWeek;
import com.turkuazgame.btlig.repository.*;
import com.turkuazgame.btlig.request.CompetitorWeekRequest;
import com.turkuazgame.btlig.response.CompetitorWeekResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitorWeekService {

    CompetitorWeekRepository competitorWeekRepository;
    CompetitorSeasonRepository competitorSeasonRepository;
    SeasonWeekRepository seasonWeekRepository;
    BaseService service;

    @Autowired
    public CompetitorWeekService(CompetitorWeekRepository competitorWeekRepository,
                                 CompetitorSeasonRepository competitorSeasonRepository,
                                 SeasonWeekRepository seasonWeekRepository) {
        this.competitorWeekRepository = competitorWeekRepository;
        this.competitorSeasonRepository = competitorSeasonRepository;
        this.seasonWeekRepository = seasonWeekRepository;
        this.service = new BaseService(competitorWeekRepository, CompetitorWeek.class, CompetitorWeekResponse.class);
    }

    public List<CompetitorWeekResponse> getCompetitorWeeks(Optional<Long> competitorSeasonId, Optional<Long> seasonWeekId){
        if(competitorSeasonId.isPresent() && seasonWeekId.isPresent())
            return getCompetitorWeekByCompetitorSeasonAndSeasonWeek(competitorSeasonId.get(), seasonWeekId.get());
        else if(competitorSeasonId.isPresent())
            return getCompetitorWeekByCompetitorSeason(competitorSeasonId.get());
        else if(seasonWeekId.isPresent())
            return getCompetitorWeekBySeasonWeek(seasonWeekId.get());
        else
            return getAllCompetitorWeeks();
    }
    public List<CompetitorWeekResponse> getAllCompetitorWeeks(){
        List<CompetitorWeekResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((CompetitorWeekResponse) iresponse);
        return responseList;
    }

    public CompetitorWeekResponse getCompetitorWeek(Long competitorWeekId) {
        return (CompetitorWeekResponse) service.getEntity(competitorWeekId);
    }

    public CompetitorWeekResponse createCompetitorWeek(CompetitorWeekRequest competitorWeekRequest) {
        CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorWeekRequest.getCompetitorSeasonId()).orElse(null);
        competitorWeekRequest.setCompetitorSeason(competitorSeason);
        SeasonWeek seasonWeek = seasonWeekRepository.findById(competitorWeekRequest.getSeasonWeekId()).orElse(null);
        competitorWeekRequest.setSeasonWeek(seasonWeek);
        return (CompetitorWeekResponse) service.createEntity(competitorWeekRequest);
    }

    public CompetitorWeekResponse updateCompetitorWeek(Long competitorWeekId, CompetitorWeekRequest competitorWeekRequest) {
        CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorWeekRequest.getCompetitorSeasonId()).orElse(null);
        competitorWeekRequest.setCompetitorSeason(competitorSeason);
        SeasonWeek seasonWeek = seasonWeekRepository.findById(competitorWeekRequest.getSeasonWeekId()).orElse(null);
        competitorWeekRequest.setSeasonWeek(seasonWeek);
        return (CompetitorWeekResponse) service.updateEntity(competitorWeekId, competitorWeekRequest);
    }

    public CompetitorWeekResponse mergeCompetitorWeek(Long competitorWeekId, Map<Object, Object> fields) {
        if(fields.containsKey("competitorId")) {
            CompetitorSeason competitorSeason = competitorSeasonRepository.findById(Long.parseLong(fields.get("competitorSeasonId").toString())).orElse(null);
            fields.remove("competitorSeasonId");
            fields.put("competitorSeason", competitorSeason);
        }
        if(fields.containsKey("seasonWeekId")) {
            SeasonWeek seasonWeek = seasonWeekRepository.findById(Long.parseLong(fields.get("seasonWeekId").toString())).orElse(null);
            fields.remove("seasonWeekId");
            fields.put("seasonWeek", seasonWeek);
        }
        return (CompetitorWeekResponse) service.mergeEntity(competitorWeekId,fields);
    }

    public void deleteCompetitorWeek(Long competitorWeekId) {
        service.deleteEntity(competitorWeekId);
    }

    public List<CompetitorWeekResponse> getCompetitorWeekByCompetitorSeason(Long competitorSeasonId) {
        if(competitorSeasonId!=null && competitorSeasonId>0) {
            CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorSeasonId).orElse(null);
            if(competitorSeason!=null) {
                List<CompetitorWeek> list = competitorWeekRepository.findByCompetitorSeason(competitorSeason);
                List<CompetitorWeekResponse> responseList = new ArrayList<>();
                for(CompetitorWeek competitorWeek : list) {
                    CompetitorWeekResponse response = new CompetitorWeekResponse(competitorWeek);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorWeekResponse> getCompetitorWeekBySeasonWeek(Long seasonWeekId) {
        if(seasonWeekId!=null && seasonWeekId>0) {
            SeasonWeek seasonWeek = seasonWeekRepository.findById(seasonWeekId).orElse(null);
            if(seasonWeek!=null) {
                List<CompetitorWeek> list = competitorWeekRepository.findBySeasonWeek(seasonWeek);
                List<CompetitorWeekResponse> responseList = new ArrayList<>();
                for(CompetitorWeek competitorWeek : list) {
                    CompetitorWeekResponse response = new CompetitorWeekResponse(competitorWeek);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorWeekResponse> getCompetitorWeekByCompetitorSeasonAndSeasonWeek(Long competitorSeasonId, Long seasonWeekId) {
        if(competitorSeasonId!=null && competitorSeasonId>0 && seasonWeekId!=null && seasonWeekId>0) {
            CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorSeasonId).orElse(null);
            SeasonWeek seasonWeek = seasonWeekRepository.findById(seasonWeekId).orElse(null);
            if(competitorSeason!=null && seasonWeek!=null) {
                List<CompetitorWeek> list = competitorWeekRepository.findBySeasonWeek(seasonWeek);
                List<CompetitorWeekResponse> responseList = new ArrayList<>();
                for(CompetitorWeek competitorWeek : list) {
                    CompetitorWeekResponse response = new CompetitorWeekResponse(competitorWeek);
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
