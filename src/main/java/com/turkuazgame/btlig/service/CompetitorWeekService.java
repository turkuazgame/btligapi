package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.entity.CompetitorWeek;
import com.turkuazgame.btlig.entity.Week;
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
    WeekRepository weekRepository;
    BaseService service;

    @Autowired
    public CompetitorWeekService(CompetitorWeekRepository competitorWeekRepository,
                                 CompetitorSeasonRepository competitorSeasonRepository,
                                 WeekRepository weekRepository) {
        this.competitorWeekRepository = competitorWeekRepository;
        this.competitorSeasonRepository = competitorSeasonRepository;
        this.weekRepository = weekRepository;
        this.service = new BaseService(competitorWeekRepository, CompetitorWeek.class, CompetitorWeekResponse.class);
    }

    public List<CompetitorWeekResponse> getCompetitorWeeks(Optional<Long> competitorSeasonId, Optional<Long> weekId){
        if(competitorSeasonId.isPresent() && weekId.isPresent())
            return getCompetitorWeekByCompetitorSeasonAndWeek(competitorSeasonId.get(), weekId.get());
        else if(competitorSeasonId.isPresent())
            return getCompetitorWeekByCompetitorSeason(competitorSeasonId.get());
        else if(weekId.isPresent())
            return getCompetitorWeekByWeek(weekId.get());
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
        Week week = weekRepository.findById(competitorWeekRequest.getWeekId()).orElse(null);
        competitorWeekRequest.setWeek(week);
        return (CompetitorWeekResponse) service.createEntity(competitorWeekRequest);
    }

    public CompetitorWeekResponse updateCompetitorWeek(Long competitorWeekId, CompetitorWeekRequest competitorWeekRequest) {
        CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorWeekRequest.getCompetitorSeasonId()).orElse(null);
        competitorWeekRequest.setCompetitorSeason(competitorSeason);
        Week week = weekRepository.findById(competitorWeekRequest.getWeekId()).orElse(null);
        competitorWeekRequest.setWeek(week);
        return (CompetitorWeekResponse) service.updateEntity(competitorWeekId, competitorWeekRequest);
    }

    public CompetitorWeekResponse mergeCompetitorWeek(Long competitorWeekId, Map<Object, Object> fields) {
        if(fields.containsKey("competitorId")) {
            CompetitorSeason competitorSeason = competitorSeasonRepository.findById(Long.parseLong(fields.get("competitorSeasonId").toString())).orElse(null);
            fields.remove("competitorSeasonId");
            fields.put("competitorSeason", competitorSeason);
        }
        if(fields.containsKey("weekId")) {
            Week week = weekRepository.findById(Long.parseLong(fields.get("seasonWeekId").toString())).orElse(null);
            fields.remove("weekId");
            fields.put("week", week);
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

    public List<CompetitorWeekResponse> getCompetitorWeekByWeek(Long weekId) {
        if(weekId!=null && weekId>0) {
            Week week = weekRepository.findById(weekId).orElse(null);
            if(week !=null) {
                List<CompetitorWeek> list = competitorWeekRepository.findBySeasonWeek(week);
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

    public List<CompetitorWeekResponse> getCompetitorWeekByCompetitorSeasonAndWeek(Long competitorSeasonId, Long weekId) {
        if(competitorSeasonId!=null && competitorSeasonId>0 && weekId!=null && weekId>0) {
            CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorSeasonId).orElse(null);
            Week week = weekRepository.findById(weekId).orElse(null);
            if(competitorSeason!=null && week !=null) {
                List<CompetitorWeek> list = competitorWeekRepository.findBySeasonWeek(week);
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
