package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.League;
import com.turkuazgame.btlig.entity.Team;
import com.turkuazgame.btlig.exception.ResourceNotFoundException;
import com.turkuazgame.btlig.repository.LeagueRepository;
import com.turkuazgame.btlig.repository.TeamRepository;
import com.turkuazgame.btlig.request.TeamRequest;
import com.turkuazgame.btlig.response.TeamResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TeamService {

    TeamRepository teamRepository;
    LeagueRepository leagueRepository;
    BaseService service;

    @Autowired
    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
        this.service = new BaseService(teamRepository, Team.class, TeamResponse.class);
    }

    public List<TeamResponse> getTeams(Optional<Long> leagueId){
        if(leagueId.isPresent())
            return getTeamsByLeague(leagueId.get());
        else
            return getAllTeams();
    }

    public List<TeamResponse> getAllTeams(){
        List<TeamResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((TeamResponse) iresponse);
        return responseList;
    }

    public TeamResponse getTeam(Long teamId) {
        return (TeamResponse) service.getEntity(teamId);
    }

    public TeamResponse createTeam(TeamRequest teamRequest) throws ResourceNotFoundException {
        League league = leagueRepository.findById(teamRequest.getLeagueId()).orElse(null);
        teamRequest.setLeague(league);
        return (TeamResponse) service.createEntity(teamRequest);
    }

    public TeamResponse updateTeam(Long teamId, TeamRequest teamRequest) {
        League league = leagueRepository.findById(teamRequest.getLeagueId()).orElse(null);
        teamRequest.setLeague(league);
        return (TeamResponse) service.updateEntity(teamId, teamRequest);
    }

    public TeamResponse mergeTeam(Long teamId, Map<Object, Object> fields) {
        if(fields.containsKey("leagueId")) {
            League league = leagueRepository.findById(Long.parseLong(fields.get("leagueId").toString())).orElse(null);
            fields.remove("leagueId");
            fields.put("league", league);
        }
        return (TeamResponse) service.mergeEntity(teamId,fields);
    }

    public void deleteTeam(Long teamId) {
        service.deleteEntity(teamId);
    }

    public List<TeamResponse> getTeamsByLeague(Long leagueId) {
        if(leagueId!=null && leagueId>0) {
            League league = leagueRepository.findById(leagueId).orElse(null);
            if(league!=null) {
                List<Team> list = teamRepository.findByLeague(league);
                List<TeamResponse> responseList = new ArrayList<>();
                for(Team team : list) {
                    TeamResponse response = new TeamResponse(team);
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
