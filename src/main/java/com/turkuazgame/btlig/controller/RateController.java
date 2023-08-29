package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.annotation.ExistsLeague;
import com.turkuazgame.btlig.annotation.ExistsRate;
import com.turkuazgame.btlig.request.RateRequest;
import com.turkuazgame.btlig.response.LeagueResponse;
import com.turkuazgame.btlig.response.RateResponse;
import com.turkuazgame.btlig.service.RateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/rates")
@Validated
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping
    public ResponseEntity<?> getRates(@RequestParam @ExistsLeague Optional<Long> rateTypeId){
        List<RateResponse> responseList = rateService.getRates(rateTypeId);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{rateId}")
    public ResponseEntity<?> getRate(@PathVariable @ExistsRate Long rateId) {
        RateResponse response = rateService.getRate(rateId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/code/{rateCode}")
    public ResponseEntity<?> getRateByCode(@PathVariable String rateCode){
        RateResponse response = rateService.getRateByCode(rateCode);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createRate(@RequestBody @Valid RateRequest rateRequest) {
        RateResponse response = rateService.createRate(rateRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{rateId}")
    public ResponseEntity<?> updateRate(@PathVariable @ExistsRate Long rateId,
                                        @RequestBody @Valid RateRequest rateRequest) {
        RateResponse response = rateService.updateRate(rateId, rateRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{rateId}")
    public ResponseEntity<?> mergeRate(@PathVariable @ExistsRate Long rateId,
                                       @RequestBody Map<Object, Object> fields) {
        RateResponse response = rateService.mergeRate(rateId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{rateId}")
    public ResponseEntity<?> deleteRate(@PathVariable @ExistsRate Long rateId) {
        rateService.deleteRate(rateId);
        return ResponseEntity.ok(rateId);
    }
}
