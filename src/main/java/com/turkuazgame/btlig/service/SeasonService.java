package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Season;
import com.turkuazgame.btlig.repository.SeasonRepository;
import com.turkuazgame.btlig.request.SeasonRequest;
import com.turkuazgame.btlig.response.SeasonResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SeasonService {

    SeasonRepository seasonRepository;
    BaseService service;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
        this.service = new BaseService(seasonRepository, Season.class, SeasonResponse.class);
    }

    public List<SeasonResponse> getAllSeasons(){
        List<SeasonResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((SeasonResponse) iresponse);
        return responseList;
    }

    public SeasonResponse getSeason(Long seasonId) {
        return (SeasonResponse) service.getEntity(seasonId);
    }

    public SeasonResponse createSeason(SeasonRequest seasonRequest) {
        return (SeasonResponse) service.createEntity(seasonRequest);
    }

    public SeasonResponse updateSeason(Long seasonId, SeasonRequest seasonRequest) {
        return (SeasonResponse) service.updateEntity(seasonId, seasonRequest);
    }

    public SeasonResponse mergeSeason(Long seasonId, Map<Object, Object> fields) {
        return (SeasonResponse) service.mergeEntity(seasonId,fields);
    }

    public void deleteSeason(Long seasonId) {
        service.deleteEntity(seasonId);
    }

    public SeasonResponse getSeasonByCode(String seasonCode) {
        if(seasonCode!=null && !seasonCode.equals("")) {
            List<Season> list = seasonRepository.findBySeasonCode(seasonCode);
            Season season = !list.isEmpty() ? list.get(0) : null;
            SeasonResponse response = new SeasonResponse(season);
            return response;
        } else
            return null;
    }

}
