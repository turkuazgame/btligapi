package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.request.SeasonRequest;
import com.turkuazgame.btlig.response.SeasonResponse;
import com.turkuazgame.btlig.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping
    public ResponseEntity<?> getAllSeasons(){
        List<SeasonResponse> responseList = seasonService.getAllSeasons();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{seasonId}")
    public ResponseEntity<?> getSeason(@PathVariable Long seasonId) {
        SeasonResponse response = seasonService.getSeason(seasonId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{seasonCode}")
    public ResponseEntity<?> getSeasonByCode(@PathVariable String seasonCode){
        SeasonResponse response = seasonService.getSeasonByCode(seasonCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createSeason(@RequestBody SeasonRequest seasonRequest) {
        SeasonResponse response = seasonService.createSeason(seasonRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{seasonId}")
    public ResponseEntity<?> updateSeason(@PathVariable Long seasonId, @RequestBody SeasonRequest seasonRequest) {
        SeasonResponse response = seasonService.updateSeason(seasonId, seasonRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{seasonId}")
    public ResponseEntity<?> mergeSeason(@PathVariable Long seasonId, @RequestBody Map<Object, Object> fields) {
        SeasonResponse response = seasonService.mergeSeason(seasonId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{seasonId}")
    public ResponseEntity<?> deleteSeason(@PathVariable Long seasonId) {
        seasonService.deleteSeason(seasonId);
        return ResponseEntity.ok(seasonId);
    }
}
