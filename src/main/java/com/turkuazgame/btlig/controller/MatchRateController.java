package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsMatch;
import com.turkuazgame.btlig.annotation.ExistsMatchRate;
import com.turkuazgame.btlig.annotation.ExistsRate;
import com.turkuazgame.btlig.request.MatchRateRequest;
import com.turkuazgame.btlig.response.MatchRateResponse;
import com.turkuazgame.btlig.service.MatchRateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/matchRates")
@Validated
public class MatchRateController {

    @Autowired
    private MatchRateService matchRateService;

    @GetMapping
    public List<MatchRateResponse> getMatchRates(@RequestParam @ExistsMatch Optional<Long> matchId,
                                                 @RequestParam @ExistsRate Optional<Long> rateId){
        return matchRateService.getMatchRates(matchId, rateId);
    }

    @GetMapping("/{matchRateId}")
    public MatchRateResponse getMatchRate(@PathVariable @ExistsMatchRate Long matchRateId) {
        return matchRateService.getMatchRate(matchRateId);
    }

    @PostMapping
    public  MatchRateResponse createMatchRate(@RequestBody @Valid MatchRateRequest matchRateRequest) {
        return matchRateService.createMatchRate(matchRateRequest);
    }

    @PutMapping("/{matchRateId}")
    public MatchRateResponse updateMatchRate(@PathVariable @ExistsMatchRate Long matchRateId,
                                             @RequestBody @Valid MatchRateRequest matchRateRequest) {
        return matchRateService.updateMatchRate(matchRateId, matchRateRequest);
    }

    @DeleteMapping("/{matchRateId}")
    public void deleteMatchRate(@PathVariable @ExistsMatchRate Long matchRateId) {
        matchRateService.deleteMatchRate(matchRateId);
    }
}
