package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsCompetitorMatch;
import com.turkuazgame.btlig.annotation.ExistsCompetitorRate;
import com.turkuazgame.btlig.annotation.ExistsMatchRate;
import com.turkuazgame.btlig.request.CompetitorRateRequest;
import com.turkuazgame.btlig.response.CompetitorRateResponse;
import com.turkuazgame.btlig.service.CompetitorRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/competitorRates")
@Validated
public class CompetitorRateController {

    @Autowired
    private CompetitorRateService competitorRateService;

    @GetMapping
    public List<CompetitorRateResponse> getCompetitorRates(@RequestParam @ExistsCompetitorMatch Optional<Long> competitorMatchId,
                                                           @RequestParam @ExistsMatchRate Optional<Long> matchRateId){
        return competitorRateService.getCompetitorRates(competitorMatchId, matchRateId);
    }

    @GetMapping("/{competitorRateId}")
    public CompetitorRateResponse getCompetitorRate(@PathVariable @ExistsCompetitorRate Long competitorRateId) {
        return competitorRateService.getCompetitorRate(competitorRateId);
    }

    @PostMapping
    public  CompetitorRateResponse createCompetitorRate(@RequestBody @Valid CompetitorRateRequest competitorRateRequest) {
        return competitorRateService.createCompetitorRate(competitorRateRequest);
    }

    @PutMapping("/{competitorRateId}")
    public CompetitorRateResponse updateCompetitorRate(@PathVariable @ExistsCompetitorRate Long competitorRateId,
                                                       @RequestBody @Valid CompetitorRateRequest competitorRateRequest) {
        return competitorRateService.updateCompetitorRate(competitorRateId, competitorRateRequest);
    }

    @DeleteMapping("/{competitorRateId}")
    public void deleteCompetitorRate(@PathVariable @ExistsCompetitorRate Long competitorRateId) {
        competitorRateService.deleteCompetitorRate(competitorRateId);
    }
}
