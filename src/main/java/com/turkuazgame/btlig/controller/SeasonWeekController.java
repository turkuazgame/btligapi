package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.annotation.ExistsSeasonWeek;
import com.turkuazgame.btlig.request.SeasonWeekRequest;
import com.turkuazgame.btlig.response.SeasonWeekResponse;
import com.turkuazgame.btlig.service.SeasonWeekService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/seasonWeeks")
@Validated
public class SeasonWeekController {

    @Autowired
    private SeasonWeekService seasonWeekService;

    @GetMapping
    public ResponseEntity<?> getSeasonWeeks(@RequestParam @ExistsSeason Optional<Long> seasonId){
        List<SeasonWeekResponse> responseList = seasonWeekService.getSeasonWeeks(seasonId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{seasonWeekId}")
    public ResponseEntity<?> getSeasonWeek(@PathVariable @ExistsSeasonWeek Long seasonWeekId) {
        SeasonWeekResponse response = seasonWeekService.getSeasonWeek(seasonWeekId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createSeasonWeek(@RequestBody @Valid SeasonWeekRequest seasonWeekRequest) {
        SeasonWeekResponse response = seasonWeekService.createSeasonWeek(seasonWeekRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{seasonWeekId}")
    public ResponseEntity<?> updateSeasonWeek(@PathVariable @ExistsSeasonWeek Long seasonWeekId,
                                              @RequestBody @Valid SeasonWeekRequest seasonWeekRequest) {
        SeasonWeekResponse response = seasonWeekService.updateSeasonWeek(seasonWeekId, seasonWeekRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{seasonWeekId}")
    public ResponseEntity<?> mergeSeasonWeek(@PathVariable @ExistsSeasonWeek Long seasonWeekId,
                                             @RequestBody Map<Object, Object> fields) {
        SeasonWeekResponse response = seasonWeekService.mergeSeasonWeek(seasonWeekId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{seasonWeekId}")
    public ResponseEntity<?> deleteSeasonWeek(@PathVariable @ExistsSeasonWeek Long seasonWeekId) {
        seasonWeekService.deleteSeasonWeek(seasonWeekId);
        return ResponseEntity.ok(seasonWeekId);
    }
}
