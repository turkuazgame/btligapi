package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.request.TeamRequest;
import com.turkuazgame.btlig.response.TeamResponse;
import com.turkuazgame.btlig.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<?> getAllTeams(){
        List<TeamResponse> responseList = teamService.getAllTeams();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeam(@PathVariable Long teamId) {
        TeamResponse response = teamService.getTeam(teamId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/league/{leagueId}")
    public ResponseEntity<?> getTeamByCode(@PathVariable String leagueId){
        TeamResponse response = teamService.getTeamByLeague(leagueId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createTeam(@RequestBody TeamRequest teamRequest) {
        TeamResponse response = teamService.createTeam(teamRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable Long teamId, @RequestBody TeamRequest teamRequest) {
        TeamResponse response = teamService.updateTeam(teamId, teamRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{teamId}")
    public ResponseEntity<?> mergeTeam(@PathVariable Long teamId, @RequestBody Map<Object, Object> fields) {
        TeamResponse response = teamService.mergeTeam(teamId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok(teamId);
    }
}
