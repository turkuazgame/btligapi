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
    public ResponseEntity<?> getWeeks(@RequestParam @ExistsSeason Optional<Long> seasonId){
        List<WeekResponse> responseList = weekService.getWeeks(seasonId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{weekId}")
    public ResponseEntity<?> getWeek(@PathVariable @ExistsWeek Long weekId) {
        WeekResponse response = weekService.getWeek(weekId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createWeek(@RequestBody @Valid WeekRequest weekRequest) {
        WeekResponse response = weekService.createWeek(weekRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{weekId}")
    public ResponseEntity<?> updateWeek(@PathVariable @ExistsWeek Long weekId,
                                        @RequestBody @Valid WeekRequest weekRequest) {
        WeekResponse response = weekService.updateWeek(weekId, weekRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{weekId}")
    public ResponseEntity<?> mergeWeek(@PathVariable @ExistsWeek Long weekId,
                                       @RequestBody Map<Object, Object> fields) {
        WeekResponse response = weekService.mergeWeek(weekId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{weekId}")
    public ResponseEntity<?> deleteWeek(@PathVariable @ExistsWeek Long weekId) {
        weekService.deleteWeek(weekId);
        return ResponseEntity.ok(weekId);
    }
}
