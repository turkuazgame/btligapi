package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Team;
import com.turkuazgame.btlig.entity.Week;
import com.turkuazgame.btlig.entity.Match;
import com.turkuazgame.btlig.exception.ResourceNotFoundException;
import com.turkuazgame.btlig.repository.TeamRepository;
import com.turkuazgame.btlig.repository.WeekRepository;
import com.turkuazgame.btlig.repository.MatchRepository;
import com.turkuazgame.btlig.request.MatchRequest;
import com.turkuazgame.btlig.response.MatchResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchService {

    MatchRepository matchRepository;
    WeekRepository weekRepository;
    TeamRepository teamRepository;
    BaseService service;

    @Autowired
    public MatchService(MatchRepository matchRepository, WeekRepository weekRepository, TeamRepository teamRepository) {
        this.matchRepository = matchRepository;
        this.weekRepository = weekRepository;
        this.teamRepository = teamRepository;
        this.service = new BaseService(matchRepository, Match.class, MatchResponse.class);
    }

    public List<MatchResponse> getMatchs(Optional<Long> weekId){
        if(weekId.isPresent())
            return getMatchsByWeek(weekId.get());
        else
            return getAllMatchs();
    }

    public List<MatchResponse> getAllMatchs(){
        List<MatchResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((MatchResponse) iresponse);
        return responseList;
    }

    public MatchResponse getMatch(Long matchId) {
        return (MatchResponse) service.getEntity(matchId);
    }

    public MatchResponse createMatch(MatchRequest matchRequest) throws ResourceNotFoundException {
        Week week = weekRepository.findById(matchRequest.getWeekId()).orElse(null);
        matchRequest.setWeek(week);
        return (MatchResponse) service.createEntity(matchRequest);
    }

    public MatchResponse updateMatch(Long matchId, MatchRequest matchRequest) {
        Week week = weekRepository.findById(matchRequest.getWeekId()).orElse(null);
        matchRequest.setWeek(week);
        return (MatchResponse) service.updateEntity(matchId, matchRequest);
    }

    public MatchResponse mergeMatch(Long matchId, Map<Object, Object> fields) {
        if(fields.containsKey("weekId")) {
            Week week = weekRepository.findById(Long.parseLong(fields.get("weekId").toString())).orElse(null);
            fields.remove("weekId");
            fields.put("week", week);
        }
        if(fields.containsKey("homeTeamId")) {
            Team homeTeam = teamRepository.findById(Long.parseLong(fields.get("homeTeamId").toString())).orElse(null);
            fields.remove("homeTeamId");
            fields.put("homeTeam", homeTeam);
        }
        if(fields.containsKey("awayTeamId")) {
            Team awayTeam = teamRepository.findById(Long.parseLong(fields.get("awayTeamId").toString())).orElse(null);
            fields.remove("awayTeamId");
            fields.put("awayTeam", awayTeam);
        }
        return (MatchResponse) service.mergeEntity(matchId,fields);
    }

    public void deleteMatch(Long matchId) {
        service.deleteEntity(matchId);
    }

    public List<MatchResponse> getMatchsByWeek(Long weekId) {
        if(weekId!=null && weekId>0) {
            Week week = weekRepository.findById(weekId).orElse(null);
            if(week!=null) {
                List<Match> list = matchRepository.findByWeek(week);
                List<MatchResponse> responseList = new ArrayList<>();
                for(Match match : list) {
                    MatchResponse response = new MatchResponse(match);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public MatchResponse getMatchByCode(String matchCode) {
        if(matchCode!=null && !matchCode.equals("")) {
            List<Match> list = matchRepository.findByMatchCode(matchCode);
            Match match = !list.isEmpty() ? list.get(0) : null;
            return match!=null ? new MatchResponse(match) : null;
        } else
            return null;
    }

    // teamType{H-->Home, A-->Away, B-->Both}
    public List<MatchResponse> getMatchsByTeam(Long teamId, Optional<String> teamType) {
        List<Match> list;
        if(teamType.isPresent()) {
            if(teamType.get().equals("B")) {
                Team homeTeam = teamRepository.findById(teamId).orElse(null);
                List<Match> homeList = homeTeam!=null ? matchRepository.findByHomeTeam(homeTeam) : null;
                Team awayTeam = teamRepository.findById(teamId).orElse(null);
                List<Match> awayList = awayTeam!=null ? matchRepository.findByAwayTeam(awayTeam) : null;
                if(homeList!=null && awayList!=null)
                    homeList.addAll(awayList);
                list = homeList;
            }
            else if(teamType.get().equals("H")) {
                Team homeTeam = teamRepository.findById(teamId).orElse(null);
                list = homeTeam!=null ? matchRepository.findByHomeTeam(homeTeam) : null;
            }
            else if(teamType.get().equals("A")) {
                Team awayTeam = teamRepository.findById(teamId).orElse(null);
                list = awayTeam!=null ? matchRepository.findByAwayTeam(awayTeam) : null;
            }
            else
                return null;
        } else {
            Team homeTeam = teamRepository.findById(teamId).orElse(null);
            List<Match> homeList = homeTeam!=null ? matchRepository.findByHomeTeam(homeTeam) : null;
            Team awayTeam = teamRepository.findById(teamId).orElse(null);
            List<Match> awayList = awayTeam!=null ? matchRepository.findByAwayTeam(awayTeam) : null;
            if(homeList!=null && awayList!=null)
                homeList.addAll(awayList);
            list = homeList;
        }

        List<MatchResponse> responseList = new ArrayList<>();
        if(list!=null) {
            for (Match match : list) {
                MatchResponse response = new MatchResponse(match);
                responseList.add(response);
            }
            return responseList;
        }
        else
            return null;
    }
}
