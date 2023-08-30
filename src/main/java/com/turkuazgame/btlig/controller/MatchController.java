package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsWeek;
import com.turkuazgame.btlig.annotation.ExistsMatch;
import com.turkuazgame.btlig.request.MatchRequest;
import com.turkuazgame.btlig.response.MatchResponse;
import com.turkuazgame.btlig.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/matchs")
@Validated
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public ResponseEntity<?> getMatchs(@RequestParam @ExistsWeek Optional<Long> weekId){
        List<MatchResponse> responseList = matchService.getMatchs(weekId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<?> getMatch(@PathVariable @ExistsMatch Long matchId) {
        MatchResponse response = matchService.getMatch(matchId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createMatch(@RequestBody @Valid MatchRequest matchRequest) {
        MatchResponse response = matchService.createMatch(matchRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<?> updateMatch(@PathVariable @ExistsMatch Long matchId,
                                         @RequestBody @Valid MatchRequest matchRequest) {
        MatchResponse response = matchService.updateMatch(matchId, matchRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{matchId}")
    public ResponseEntity<?> mergeMatch(@PathVariable @ExistsMatch Long matchId,
                                        @RequestBody Map<Object, Object> fields) {
        MatchResponse response = matchService.mergeMatch(matchId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<?> deleteMatch(@PathVariable @ExistsMatch Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.ok(matchId);
    }
}
