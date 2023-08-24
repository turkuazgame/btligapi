package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.request.CompetitorSeasonRequest;
import com.turkuazgame.btlig.response.CompetitorSeasonResponse;
import com.turkuazgame.btlig.service.CompetitorSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competitorSeasons")
public class CompetitorSeasonController {

    @Autowired
    private CompetitorSeasonService competitorSeasonService;

    @GetMapping
    public List<CompetitorSeasonResponse> getAllCompetitorSeasons(@RequestParam Optional<Long> competitorId){
        return competitorSeasonService.getAllCompetitorSeasons(competitorId);
    }

    @PostMapping
    public  CompetitorSeasonResponse createCompetitorSeason(@RequestBody CompetitorSeasonRequest competitorSeasonRequest) {
        return competitorSeasonService.createCompetitorSeason(competitorSeasonRequest);
    }

    @GetMapping("/{competitorSeasonId}")
    public CompetitorSeasonResponse getCompetitorSeason(@PathVariable Long competitorSeasonId) {
        return competitorSeasonService.getCompetitorSeason(competitorSeasonId);
    }

    @PutMapping("/{competitorSeasonId}")
    public CompetitorSeasonResponse updateCompetitorSeason(@PathVariable Long competitorSeasonId, @RequestBody CompetitorSeasonRequest competitorSeasonRequest) {
        return competitorSeasonService.updateCompetitorSeason(competitorSeasonId, competitorSeasonRequest);
    }

    @DeleteMapping("/{competitorSeasonId}")
    public void deleteCompetitorSeason(@PathVariable Long competitorSeasonId) {
        competitorSeasonService.deleteCompetitorSeason(competitorSeasonId);
    }
}
