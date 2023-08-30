package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.entity.Week;
import com.turkuazgame.btlig.exception.ResourceNotFoundException;
import com.turkuazgame.btlig.repository.SeasonRepository;
import com.turkuazgame.btlig.repository.WeekRepository;
import com.turkuazgame.btlig.request.WeekRequest;
import com.turkuazgame.btlig.response.WeekResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class WeekService {

    WeekRepository weekRepository;
    SeasonRepository seasonRepository;
    BaseService service;

    @Autowired
    public WeekService(WeekRepository weekRepository, SeasonRepository seasonRepository) {
        this.weekRepository = weekRepository;
        this.seasonRepository = seasonRepository;
        this.service = new BaseService(weekRepository, Week.class, WeekResponse.class);
    }

    public List<WeekResponse> getWeeks(Optional<Long> seasonId){
        if(seasonId.isPresent())
            return getWeeksBySeason(seasonId.get());
        else
            return getAllWeeks();
    }

    public List<WeekResponse> getAllWeeks(){
        List<WeekResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((WeekResponse) iresponse);
        return responseList;
    }

    public WeekResponse getWeek(Long weekId) {
        return (WeekResponse) service.getEntity(weekId);
    }

    public WeekResponse createWeek(WeekRequest weekRequest) throws ResourceNotFoundException {
        Season season = seasonRepository.findById(weekRequest.getSeasonId()).orElse(null);
        weekRequest.setSeason(season);
        return (WeekResponse) service.createEntity(weekRequest);
    }

    public WeekResponse updateWeek(Long weekId, WeekRequest weekRequest) {
        Season season = seasonRepository.findById(weekRequest.getSeasonId()).orElse(null);
        weekRequest.setSeason(season);
        return (WeekResponse) service.updateEntity(weekId, weekRequest);
    }

    public WeekResponse mergeWeek(Long weekId, Map<Object, Object> fields) {
        if(fields.containsKey("seasonId")) {
            Season season = seasonRepository.findById(Long.parseLong(fields.get("seasonId").toString())).orElse(null);
            fields.remove("seasonId");
            fields.put("season", season);
        }
        return (WeekResponse) service.mergeEntity(weekId,fields);
    }

    public void deleteWeek(Long weekId) {
        service.deleteEntity(weekId);
    }

    public List<WeekResponse> getWeeksBySeason(Long seasonId) {
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
