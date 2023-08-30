package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.repository.*;
import com.turkuazgame.btlig.request.CompetitorSeasonRequest;
import com.turkuazgame.btlig.response.CompetitorSeasonResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitorSeasonService {

    CompetitorSeasonRepository competitorSeasonRepository;
    CompetitorRepository competitorRepository;
    SeasonRepository seasonRepository;
    BaseService service;

    @Autowired
    public CompetitorSeasonService(CompetitorSeasonRepository competitorSeasonRepository,
                                   CompetitorRepository competitorRepository,
                                   SeasonRepository seasonRepository) {
        this.competitorSeasonRepository = competitorSeasonRepository;
        this.competitorRepository = competitorRepository;
        this.seasonRepository = seasonRepository;
        this.service = new BaseService(competitorSeasonRepository, CompetitorSeason.class, CompetitorSeasonResponse.class);
    }

    public List<CompetitorSeasonResponse> getCompetitorSeasons(Optional<Long> competitorId, Optional<Long> seasonId){
        if(competitorId.isPresent() && seasonId.isPresent())
            return getCompetitorSeasonByCompetitorAndSeason(competitorId.get(), seasonId.get());
        else if(competitorId.isPresent())
            return getCompetitorSeasonByCompetitor(competitorId.get());
        else if(seasonId.isPresent())
            return getCompetitorSeasonBySeason(seasonId.get());
        else
            return getAllCompetitorSeasons();
    }
    public List<CompetitorSeasonResponse> getAllCompetitorSeasons(){
        List<CompetitorSeasonResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((CompetitorSeasonResponse) iresponse);
        return responseList;
    }

    public CompetitorSeasonResponse getCompetitorSeason(Long competitorSeasonId) {
        return (CompetitorSeasonResponse) service.getEntity(competitorSeasonId);
    }

    public CompetitorSeasonResponse createCompetitorSeason(CompetitorSeasonRequest competitorSeasonRequest) {
        Competitor competitor = competitorRepository.findById(competitorSeasonRequest.getCompetitorId()).orElse(null);
        competitorSeasonRequest.setCompetitor(competitor);
        Season season = seasonRepository.findById(competitorSeasonRequest.getSeasonId()).orElse(null);
        competitorSeasonRequest.setSeason(season);
        return (CompetitorSeasonResponse) service.createEntity(competitorSeasonRequest);
    }

    public CompetitorSeasonResponse updateCompetitorSeason(Long competitorSeasonId, CompetitorSeasonRequest competitorSeasonRequest) {
        Competitor competitor = competitorRepository.findById(competitorSeasonRequest.getCompetitorId()).orElse(null);
        competitorSeasonRequest.setCompetitor(competitor);
        Season season = seasonRepository.findById(competitorSeasonRequest.getSeasonId()).orElse(null);
        competitorSeasonRequest.setSeason(season);
        return (CompetitorSeasonResponse) service.updateEntity(competitorSeasonId, competitorSeasonRequest);
    }

    public CompetitorSeasonResponse mergeCompetitorSeason(Long competitorSeasonId, Map<Object, Object> fields) {
        if(fields.containsKey("competitorId")) {
            Competitor competitor = competitorRepository.findById(Long.parseLong(fields.get("competitorId").toString())).orElse(null);
            fields.remove("competitorId");
            fields.put("competitor", competitor);
        }
        if(fields.containsKey("seasonId")) {
            Season season = seasonRepository.findById(Long.parseLong(fields.get("seasonId").toString())).orElse(null);
            fields.remove("seasonId");
            fields.put("season", season);
        }
        return (CompetitorSeasonResponse) service.mergeEntity(competitorSeasonId,fields);
    }

    public void deleteCompetitorSeason(Long competitorSeasonId) {
        service.deleteEntity(competitorSeasonId);
    }

    public List<CompetitorSeasonResponse> getCompetitorSeasonByCompetitor(Long competitorId) {
        if(competitorId!=null && competitorId>0) {
            Competitor competitor = competitorRepository.findById(competitorId).orElse(null);
            if(competitor!=null) {
                List<CompetitorSeason> list = competitorSeasonRepository.findByCompetitor(competitor);
                List<CompetitorSeasonResponse> responseList = new ArrayList<>();
                for(CompetitorSeason competitorSeason : list) {
                    CompetitorSeasonResponse response = new CompetitorSeasonResponse(competitorSeason);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorSeasonResponse> getCompetitorSeasonBySeason(Long seasonId) {
        if(seasonId!=null && seasonId>0) {
            Season season = seasonRepository.findById(seasonId).orElse(null);
            if(season!=null) {
                List<CompetitorSeason> list = competitorSeasonRepository.findBySeason(season);
                List<CompetitorSeasonResponse> responseList = new ArrayList<>();
                for(CompetitorSeason competitorSeason : list) {
                    CompetitorSeasonResponse response = new CompetitorSeasonResponse(competitorSeason);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<CompetitorSeasonResponse> getCompetitorSeasonByCompetitorAndSeason(Long competitorId, Long seasonId) {
        if(competitorId!=null && competitorId>0 && seasonId!=null && seasonId>0) {
            Competitor competitor = competitorRepository.findById(competitorId).orElse(null);
            Season season = seasonRepository.findById(seasonId).orElse(null);
            if(competitor!=null && season!=null) {
                List<CompetitorSeason> list = competitorSeasonRepository.findBySeason(season);
                List<CompetitorSeasonResponse> responseList = new ArrayList<>();
                for(CompetitorSeason competitorSeason : list) {
                    CompetitorSeasonResponse response = new CompetitorSeasonResponse(competitorSeason);
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
