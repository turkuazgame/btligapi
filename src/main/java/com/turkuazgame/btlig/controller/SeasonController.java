package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.request.SeasonRequest;
import com.turkuazgame.btlig.response.SeasonResponse;
import com.turkuazgame.btlig.service.SeasonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seasons")
@Validated
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping
    public ResponseEntity<?> getSeasons(){
        List<SeasonResponse> responseList = seasonService.getAllSeasons();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{seasonId}")
    public ResponseEntity<?> getSeason(@PathVariable @ExistsSeason Long seasonId) {
        SeasonResponse response = seasonService.getSeason(seasonId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{seasonCode}")
    public ResponseEntity<?> getSeasonByCode(@PathVariable String seasonCode){
        SeasonResponse response = seasonService.getSeasonByCode(seasonCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createSeason(@RequestBody @Valid SeasonRequest seasonRequest) {
        SeasonResponse response = seasonService.createSeason(seasonRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{seasonId}")
    public ResponseEntity<?> updateSeason(@PathVariable @ExistsSeason Long seasonId,
                                          @RequestBody @Valid SeasonRequest seasonRequest) {
        SeasonResponse response = seasonService.updateSeason(seasonId, seasonRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{seasonId}")
    public ResponseEntity<?> mergeSeason(@PathVariable @ExistsSeason Long seasonId,
                                         @RequestBody Map<Object, Object> fields) {
        SeasonResponse response = seasonService.mergeSeason(seasonId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{seasonId}")
    public ResponseEntity<?> deleteSeason(@PathVariable @ExistsSeason Long seasonId) {
        seasonService.deleteSeason(seasonId);
        return ResponseEntity.ok(seasonId);
    }
}
