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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TeamService {

    @Value("${btlig.error.team.league.NotFound.message}")
    private String leagueNotFoundErrorMsg;

    TeamRepository teamRepository;
    LeagueRepository leagueRepository;
    BaseService service;

    @Autowired
    public TeamService(TeamRepository teamRepository, LeagueRepository leagueRepository) {
        this.teamRepository = teamRepository;
        this.leagueRepository = leagueRepository;
        this.service = new BaseService(teamRepository, Team.class, TeamResponse.class);
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
        League league = getLeague(teamRequest.getLeagueId());
        teamRequest.setLeague(league);
        return (TeamResponse) service.createEntity(teamRequest);
    }

    public TeamResponse updateTeam(Long teamId, TeamRequest teamRequest) throws ResourceNotFoundException {
        League league = getLeague(teamRequest.getLeagueId());
        teamRequest.setLeague(league);
        return (TeamResponse) service.updateEntity(teamId, teamRequest);
    }

    public TeamResponse mergeTeam(Long teamId, Map<Object, Object> fields) throws ResourceNotFoundException {
        if(fields.containsKey("leagueId")) {
            League league = getLeague(Long.parseLong(fields.get("leagueId").toString()));
            fields.remove("leagueId");
            fields.put("league", league);
        }
        return (TeamResponse) service.mergeEntity(teamId,fields);
    }

    public void deleteTeam(Long teamId) {
        service.deleteEntity(teamId);
    }

    public TeamResponse getTeamByLeague(String leagueId) throws ResourceNotFoundException {
        if(leagueId!=null && !leagueId.equals("")) {
            League league = getLeague(Long.parseLong(leagueId));
            List<Team> list = teamRepository.findByLeague(league);
            Team team = !list.isEmpty() ? list.get(0) : null;
            TeamResponse response = team!=null ? new TeamResponse(team) : null;
            return response;
        } else
            throw new ResourceNotFoundException(leagueNotFoundErrorMsg);
    }

    private League getLeague(Long leagueId) throws ResourceNotFoundException {
        League league = leagueRepository.findById(leagueId).orElse(null);
        if(league==null) {
            throw new ResourceNotFoundException(leagueNotFoundErrorMsg);
        }
        return league;
    }
}
