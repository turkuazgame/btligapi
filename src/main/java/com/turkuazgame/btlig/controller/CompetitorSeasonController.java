package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsCompetitor;
import com.turkuazgame.btlig.annotation.ExistsCompetitorSeason;
import com.turkuazgame.btlig.annotation.ExistsSeason;
import com.turkuazgame.btlig.request.CompetitorSeasonRequest;
import com.turkuazgame.btlig.response.CompetitorSeasonResponse;
import com.turkuazgame.btlig.service.CompetitorSeasonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competitorSeasons")
@Validated
public class CompetitorSeasonController {

    @Autowired
    private CompetitorSeasonService competitorSeasonService;

    @GetMapping
    public List<CompetitorSeasonResponse> getAllCompetitorSeasons(@RequestParam @ExistsCompetitor Optional<Long> competitorId,
                                                                  @RequestParam @ExistsSeason Optional<Long> seasonId){
        return competitorSeasonService.getCompetitorSeasons(competitorId, seasonId);
    }

    @GetMapping("/{competitorSeasonId}")
    public CompetitorSeasonResponse getCompetitorSeason(@PathVariable @ExistsCompetitorSeason Long competitorSeasonId) {
        return competitorSeasonService.getCompetitorSeason(competitorSeasonId);
    }

    @PostMapping
    public  CompetitorSeasonResponse createCompetitorSeason(@RequestBody @Valid CompetitorSeasonRequest competitorSeasonRequest) {
        return competitorSeasonService.createCompetitorSeason(competitorSeasonRequest);
    }

    @PutMapping("/{competitorSeasonId}")
    public CompetitorSeasonResponse updateCompetitorSeason(@PathVariable @ExistsCompetitorSeason Long competitorSeasonId,
                                                           @RequestBody @Valid CompetitorSeasonRequest competitorSeasonRequest) {
        return competitorSeasonService.updateCompetitorSeason(competitorSeasonId, competitorSeasonRequest);
    }

    @DeleteMapping("/{competitorSeasonId}")
    public void deleteCompetitorSeason(@PathVariable @ExistsCompetitorSeason Long competitorSeasonId) {
        competitorSeasonService.deleteCompetitorSeason(competitorSeasonId);
    }
}
