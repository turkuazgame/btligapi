package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.SeasonWeek;
import com.turkuazgame.btlig.exception.ResourceNotFoundException;
import com.turkuazgame.btlig.repository.SeasonRepository;
import com.turkuazgame.btlig.repository.SeasonWeekRepository;
import com.turkuazgame.btlig.request.SeasonWeekRequest;
import com.turkuazgame.btlig.response.SeasonWeekResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SeasonWeekService {

    SeasonWeekRepository seasonWeekRepository;
    SeasonRepository seasonRepository;
    BaseService service;

    @Autowired
    public SeasonWeekService(SeasonWeekRepository seasonWeekRepository, SeasonRepository seasonRepository) {
        this.seasonWeekRepository = seasonWeekRepository;
        this.seasonRepository = seasonRepository;
        this.service = new BaseService(seasonWeekRepository, SeasonWeek.class, SeasonWeekResponse.class);
    }

    public List<SeasonWeekResponse> getSeasonWeeks(Optional<Long> seasonId){
        if(seasonId.isPresent())
            return getSeasonWeeksBySeason(seasonId.get());
        else
            return getAllSeasonWeeks();
    }

    public List<SeasonWeekResponse> getAllSeasonWeeks(){
        List<SeasonWeekResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((SeasonWeekResponse) iresponse);
        return responseList;
    }

    public SeasonWeekResponse getSeasonWeek(Long seasonWeekId) {
        return (SeasonWeekResponse) service.getEntity(seasonWeekId);
    }

    public SeasonWeekResponse createSeasonWeek(SeasonWeekRequest seasonWeekRequest) throws ResourceNotFoundException {
        Season season = seasonRepository.findById(seasonWeekRequest.getSeasonId()).orElse(null);
        seasonWeekRequest.setSeason(season);
        return (SeasonWeekResponse) service.createEntity(seasonWeekRequest);
    }

    public SeasonWeekResponse updateSeasonWeek(Long seasonWeekId, SeasonWeekRequest seasonWeekRequest) {
        Season season = seasonRepository.findById(seasonWeekRequest.getSeasonId()).orElse(null);
        seasonWeekRequest.setSeason(season);
        return (SeasonWeekResponse) service.updateEntity(seasonWeekId, seasonWeekRequest);
    }

    public SeasonWeekResponse mergeSeasonWeek(Long seasonWeekId, Map<Object, Object> fields) {
        if(fields.containsKey("seasonId")) {
            Season season = seasonRepository.findById(Long.parseLong(fields.get("seasonId").toString())).orElse(null);
            fields.remove("seasonId");
            fields.put("season", season);
        }
        return (SeasonWeekResponse) service.mergeEntity(seasonWeekId,fields);
    }

    public void deleteSeasonWeek(Long seasonWeekId) {
        service.deleteEntity(seasonWeekId);
    }

    public List<SeasonWeekResponse> getSeasonWeeksBySeason(Long seasonId) {
        if(seasonId!=null && seasonId>0) {
            Season season = seasonRepository.findById(seasonId).orElse(null);
            if(season!=null) {
                List<SeasonWeek> list = seasonWeekRepository.findBySeason(season);
                List<SeasonWeekResponse> responseList = new ArrayList<>();
                for(SeasonWeek seasonWeek : list) {
                    SeasonWeekResponse response = new SeasonWeekResponse(seasonWeek);
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
