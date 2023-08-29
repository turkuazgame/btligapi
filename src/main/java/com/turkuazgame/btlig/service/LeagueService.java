package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.League;
import com.turkuazgame.btlig.repository.LeagueRepository;
import com.turkuazgame.btlig.request.LeagueRequest;
import com.turkuazgame.btlig.response.LeagueResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class LeagueService {

    LeagueRepository leagueRepository;
    BaseService service;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
        this.service = new BaseService(leagueRepository, League.class, LeagueResponse.class);
    }

    public List<LeagueResponse> getAllLeagues(){
        List<LeagueResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((LeagueResponse) iresponse);
        return responseList;
    }

    public LeagueResponse getLeague(Long leagueId) {
        return (LeagueResponse) service.getEntity(leagueId);
    }

    public LeagueResponse createLeague(LeagueRequest leagueRequest) {
        return (LeagueResponse) service.createEntity(leagueRequest);
    }

    public LeagueResponse updateLeague(Long leagueId, LeagueRequest leagueRequest) {
        return (LeagueResponse) service.updateEntity(leagueId, leagueRequest);
    }

    public LeagueResponse mergeLeague(Long leagueId, Map<Object, Object> fields) {
        return (LeagueResponse) service.mergeEntity(leagueId,fields);
    }

    public void deleteLeague(Long leagueId) {
        service.deleteEntity(leagueId);
    }

    public LeagueResponse getLeagueByCode(String leagueCode) {
        if(leagueCode!=null && !leagueCode.equals("")) {
            List<League> list = leagueRepository.findByLeagueCode(leagueCode);
            League league = !list.isEmpty() ? list.get(0) : null;
            LeagueResponse response = league!=null ? new LeagueResponse(league) : null;
            return response;
        } else
            return null;
    }

}
