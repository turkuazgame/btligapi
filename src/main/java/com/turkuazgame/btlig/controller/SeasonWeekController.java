package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.annotation.ExistsWeek;
import com.turkuazgame.btlig.request.SeasonWeekRequest;
import com.turkuazgame.btlig.response.WeekResponse;
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
        List<WeekResponse> responseList = seasonWeekService.getSeasonWeeks(seasonId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{seasonWeekId}")
    public ResponseEntity<?> getSeasonWeek(@PathVariable @ExistsWeek Long seasonWeekId) {
        WeekResponse response = seasonWeekService.getSeasonWeek(seasonWeekId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createSeasonWeek(@RequestBody @Valid SeasonWeekRequest seasonWeekRequest) {
        WeekResponse response = seasonWeekService.createSeasonWeek(seasonWeekRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{seasonWeekId}")
    public ResponseEntity<?> updateSeasonWeek(@PathVariable @ExistsWeek Long seasonWeekId,
                                              @RequestBody @Valid SeasonWeekRequest seasonWeekRequest) {
        WeekResponse response = seasonWeekService.updateSeasonWeek(seasonWeekId, seasonWeekRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{seasonWeekId}")
    public ResponseEntity<?> mergeSeasonWeek(@PathVariable @ExistsWeek Long seasonWeekId,
                                             @RequestBody Map<Object, Object> fields) {
        WeekResponse response = seasonWeekService.mergeSeasonWeek(seasonWeekId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{seasonWeekId}")
    public ResponseEntity<?> deleteSeasonWeek(@PathVariable @ExistsWeek Long seasonWeekId) {
        seasonWeekService.deleteSeasonWeek(seasonWeekId);
        return ResponseEntity.ok(seasonWeekId);
    }
}
