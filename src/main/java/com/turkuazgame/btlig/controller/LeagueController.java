package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.request.LeagueRequest;
import com.turkuazgame.btlig.response.LeagueResponse;
import com.turkuazgame.btlig.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leagues")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping
    public ResponseEntity<?> getAllLeagues(){
        List<LeagueResponse> responseList = leagueService.getAllLeagues();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{leagueId}")
    public ResponseEntity<?> getLeague(@PathVariable Long leagueId) {
        LeagueResponse response = leagueService.getLeague(leagueId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{leagueCode}")
    public ResponseEntity<?> getLeagueByCode(@PathVariable String leagueCode){
        LeagueResponse response = leagueService.getLeagueByCode(leagueCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createLeague(@RequestBody LeagueRequest leagueRequest) {
        LeagueResponse response = leagueService.createLeague(leagueRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{leagueId}")
    public ResponseEntity<?> updateLeague(@PathVariable Long leagueId, @RequestBody LeagueRequest leagueRequest) {
        LeagueResponse response = leagueService.updateLeague(leagueId, leagueRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{leagueId}")
    public ResponseEntity<?> updateLeague(@PathVariable Long leagueId, @RequestBody Map<Object, Object> fields) {
        LeagueResponse response = leagueService.mergeLeague(leagueId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable Long leagueId) {
        leagueService.deleteLeague(leagueId);
        return ResponseEntity.ok(leagueId);
    }
}
