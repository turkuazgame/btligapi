package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsLeague;
import com.turkuazgame.btlig.annotation.ExistsTeam;
import com.turkuazgame.btlig.request.TeamRequest;
import com.turkuazgame.btlig.response.TeamResponse;
import com.turkuazgame.btlig.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
@Validated
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<?> getTeams(@RequestParam @ExistsLeague Optional<Long> leagueId){
        List<TeamResponse> responseList = teamService.getTeams(leagueId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<?> getTeam(@PathVariable @ExistsTeam Long teamId) {
        TeamResponse response = teamService.getTeam(teamId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createTeam(@RequestBody @Valid TeamRequest teamRequest) {
        TeamResponse response = teamService.createTeam(teamRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<?> updateTeam(@PathVariable @ExistsTeam Long teamId,
                                        @RequestBody @Valid TeamRequest teamRequest) {
        TeamResponse response = teamService.updateTeam(teamId, teamRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{teamId}")
    public ResponseEntity<?> mergeTeam(@PathVariable @ExistsTeam Long teamId,
                                       @RequestBody Map<Object, Object> fields) {
        TeamResponse response = teamService.mergeTeam(teamId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable @ExistsTeam Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok(teamId);
    }
}
