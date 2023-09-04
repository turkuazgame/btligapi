package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsCompetitorWeek;
import com.turkuazgame.btlig.annotation.ExistsCompetitorMatch;
import com.turkuazgame.btlig.annotation.ExistsMatch;
import com.turkuazgame.btlig.request.CompetitorMatchRequest;
import com.turkuazgame.btlig.response.CompetitorMatchResponse;
import com.turkuazgame.btlig.service.CompetitorMatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competitorMatchs")
@Validated
public class CompetitorMatchController {

    @Autowired
    private CompetitorMatchService competitorMatchService;

    @GetMapping
    public List<CompetitorMatchResponse> getCompetitorMatchs(@RequestParam @ExistsCompetitorWeek Optional<Long> competitorWeekId,
                                                             @RequestParam @ExistsMatch Optional<Long> matchId){
        return competitorMatchService.getCompetitorMatchs(competitorWeekId, matchId);
    }

    @GetMapping("/{competitorMatchId}")
    public CompetitorMatchResponse getCompetitorMatch(@PathVariable @ExistsCompetitorMatch Long competitorMatchId) {
        return competitorMatchService.getCompetitorMatch(competitorMatchId);
    }

    @PostMapping
    public  CompetitorMatchResponse createCompetitorMatch(@RequestBody @Valid CompetitorMatchRequest competitorMatchRequest) {
        return competitorMatchService.createCompetitorMatch(competitorMatchRequest);
    }

    @PutMapping("/{competitorMatchId}")
    public CompetitorMatchResponse updateCompetitorMatch(@PathVariable @ExistsCompetitorMatch Long competitorMatchId,
                                                         @RequestBody @Valid CompetitorMatchRequest competitorMatchRequest) {
        return competitorMatchService.updateCompetitorMatch(competitorMatchId, competitorMatchRequest);
    }

    @DeleteMapping("/{competitorMatchId}")
    public void deleteCompetitorMatch(@PathVariable @ExistsCompetitorMatch Long competitorMatchId) {
        competitorMatchService.deleteCompetitorMatch(competitorMatchId);
    }
}
