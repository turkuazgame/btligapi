package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsCompetitor;
import com.turkuazgame.btlig.annotation.ExistsFirebaseUser;
import com.turkuazgame.btlig.request.CompetitorRequest;
import com.turkuazgame.btlig.response.CompetitorResponse;
import com.turkuazgame.btlig.service.CompetitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/competitors")
@Validated
public class CompetitorController {

    @Autowired
    private CompetitorService competitorService;

    @GetMapping
    public ResponseEntity<?> getAllCompetitors(){
        List<CompetitorResponse> responseList = competitorService.getAllCompetitors();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{competitorId}")
    public ResponseEntity<?> getCompetitor(@PathVariable @ExistsCompetitor Long competitorId) {
        CompetitorResponse response = competitorService.getCompetitor(competitorId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userUID}")
    public ResponseEntity<?> getCompetitorByUser(@PathVariable @ExistsFirebaseUser String userUID){
        CompetitorResponse response = competitorService.getCompetitorByUser(userUID);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createCompetitor(@Valid @RequestBody CompetitorRequest competitorRequest) {
        CompetitorResponse response = competitorService.createCompetitor(competitorRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{competitorId}")
    public ResponseEntity<?> updateCompetitor(@PathVariable @ExistsCompetitor Long competitorId, @Valid @RequestBody CompetitorRequest competitorRequest) {
        CompetitorResponse response = competitorService.updateCompetitor(competitorId, competitorRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{competitorId}")
    public ResponseEntity<?> updateCompetitor(@PathVariable @ExistsCompetitor Long competitorId, @RequestBody Map<Object, Object> fields) {
        CompetitorResponse response = competitorService.mergeCompetitor(competitorId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{competitorId}")
    public ResponseEntity<?> deleteCompetitor(@PathVariable @ExistsCompetitor Long competitorId) {
        competitorService.deleteCompetitor(competitorId);
        return ResponseEntity.ok(competitorId);
    }
}
