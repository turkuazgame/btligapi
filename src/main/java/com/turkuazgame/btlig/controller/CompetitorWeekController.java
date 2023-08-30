package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsCompetitorSeason;
import com.turkuazgame.btlig.annotation.ExistsCompetitorWeek;
import com.turkuazgame.btlig.annotation.ExistsWeek;
import com.turkuazgame.btlig.request.CompetitorWeekRequest;
import com.turkuazgame.btlig.response.CompetitorWeekResponse;
import com.turkuazgame.btlig.service.CompetitorWeekService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competitorWeeks")
@Validated
public class CompetitorWeekController {

    @Autowired
    private CompetitorWeekService competitorWeekService;

    @GetMapping
    public List<CompetitorWeekResponse> getCompetitorWeeks(@RequestParam @ExistsCompetitorSeason Optional<Long> competitorSeasonId,
                                                           @RequestParam @ExistsWeek Optional<Long> seasonWeekId){
        return competitorWeekService.getCompetitorWeeks(competitorSeasonId, seasonWeekId);
    }

    @GetMapping("/{competitorWeekId}")
    public CompetitorWeekResponse getCompetitorWeek(@PathVariable @ExistsCompetitorWeek Long competitorWeekId) {
        return competitorWeekService.getCompetitorWeek(competitorWeekId);
    }

    @PostMapping
    public  CompetitorWeekResponse createCompetitorWeek(@RequestBody @Valid CompetitorWeekRequest competitorWeekRequest) {
        return competitorWeekService.createCompetitorWeek(competitorWeekRequest);
    }

    @PutMapping("/{competitorWeekId}")
    public CompetitorWeekResponse updateCompetitorWeek(@PathVariable @ExistsCompetitorWeek Long competitorWeekId,
                                                       @RequestBody @Valid CompetitorWeekRequest competitorWeekRequest) {
        return competitorWeekService.updateCompetitorWeek(competitorWeekId, competitorWeekRequest);
    }

    @DeleteMapping("/{competitorWeekId}")
    public void deleteCompetitorWeek(@PathVariable @ExistsCompetitorWeek Long competitorWeekId) {
        competitorWeekService.deleteCompetitorWeek(competitorWeekId);
    }
}
