package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.annotation.ExistsWeek;
import com.turkuazgame.btlig.request.WeekRequest;
import com.turkuazgame.btlig.response.WeekResponse;
import com.turkuazgame.btlig.service.WeekService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/weeks")
@Validated
public class WeekController {

    @Autowired
    private WeekService weekService;

    @GetMapping
    public ResponseEntity<?> getSeasonWeeks(@RequestParam @ExistsSeason Optional<Long> seasonId){
        List<WeekResponse> responseList = weekService.getSeasonWeeks(seasonId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{weekId}")
    public ResponseEntity<?> getSeasonWeek(@PathVariable @ExistsWeek Long weekId) {
        WeekResponse response = weekService.getSeasonWeek(weekId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createSeasonWeek(@RequestBody @Valid WeekRequest weekRequest) {
        WeekResponse response = weekService.createSeasonWeek(weekRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{weekId}")
    public ResponseEntity<?> updateSeasonWeek(@PathVariable @ExistsWeek Long weekId,
                                              @RequestBody @Valid WeekRequest weekRequest) {
        WeekResponse response = weekService.updateSeasonWeek(weekId, weekRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{weekId}")
    public ResponseEntity<?> mergeSeasonWeek(@PathVariable @ExistsWeek Long weekId,
                                             @RequestBody Map<Object, Object> fields) {
        WeekResponse response = weekService.mergeSeasonWeek(weekId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{weekId}")
    public ResponseEntity<?> deleteSeasonWeek(@PathVariable @ExistsWeek Long weekId) {
        weekService.deleteSeasonWeek(weekId);
        return ResponseEntity.ok(weekId);
    }
}
