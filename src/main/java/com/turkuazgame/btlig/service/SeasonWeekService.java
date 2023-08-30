package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.Week;
import com.turkuazgame.btlig.exception.ResourceNotFoundException;
import com.turkuazgame.btlig.repository.SeasonRepository;
import com.turkuazgame.btlig.repository.WeekRepository;
import com.turkuazgame.btlig.request.SeasonWeekRequest;
import com.turkuazgame.btlig.response.WeekResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SeasonWeekService {

    WeekRepository weekRepository;
    SeasonRepository seasonRepository;
    BaseService service;

    @Autowired
    public SeasonWeekService(WeekRepository weekRepository, SeasonRepository seasonRepository) {
        this.weekRepository = weekRepository;
        this.seasonRepository = seasonRepository;
        this.service = new BaseService(weekRepository, Week.class, WeekResponse.class);
    }

    public List<WeekResponse> getSeasonWeeks(Optional<Long> seasonId){
        if(seasonId.isPresent())
            return getSeasonWeeksBySeason(seasonId.get());
        else
            return getAllSeasonWeeks();
    }

    public List<WeekResponse> getAllSeasonWeeks(){
        List<WeekResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((WeekResponse) iresponse);
        return responseList;
    }

    public WeekResponse getSeasonWeek(Long seasonWeekId) {
        return (WeekResponse) service.getEntity(seasonWeekId);
    }

    public WeekResponse createSeasonWeek(SeasonWeekRequest seasonWeekRequest) throws ResourceNotFoundException {
        Season season = seasonRepository.findById(seasonWeekRequest.getSeasonId()).orElse(null);
        seasonWeekRequest.setSeason(season);
        return (WeekResponse) service.createEntity(seasonWeekRequest);
    }

    public WeekResponse updateSeasonWeek(Long seasonWeekId, SeasonWeekRequest seasonWeekRequest) {
        Season season = seasonRepository.findById(seasonWeekRequest.getSeasonId()).orElse(null);
        seasonWeekRequest.setSeason(season);
        return (WeekResponse) service.updateEntity(seasonWeekId, seasonWeekRequest);
    }

    public WeekResponse mergeSeasonWeek(Long seasonWeekId, Map<Object, Object> fields) {
        if(fields.containsKey("seasonId")) {
            Season season = seasonRepository.findById(Long.parseLong(fields.get("seasonId").toString())).orElse(null);
            fields.remove("seasonId");
            fields.put("season", season);
        }
        return (WeekResponse) service.mergeEntity(seasonWeekId,fields);
    }

    public void deleteSeasonWeek(Long seasonWeekId) {
        service.deleteEntity(seasonWeekId);
    }

    public List<WeekResponse> getSeasonWeeksBySeason(Long seasonId) {
        if(seasonId!=null && seasonId>0) {
            Season season = seasonRepository.findById(seasonId).orElse(null);
            if(season!=null) {
                List<Week> list = weekRepository.findBySeason(season);
                List<WeekResponse> responseList = new ArrayList<>();
                for(Week week : list) {
                    WeekResponse response = new WeekResponse(week);
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
