package com.turkuazgame.btlig.controller;

import com.turkuazgame.btlig.request.RateTypeRequest;
import com.turkuazgame.btlig.response.RateTypeResponse;
import com.turkuazgame.btlig.service.RateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rateTypes")
public class RateTypeController {

    @Autowired
    private RateTypeService rateTypeService;

    @GetMapping
    public ResponseEntity<?> getAllRateTypes(){
        List<RateTypeResponse> responseList = rateTypeService.getAllRateTypes();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{rateTypeId}")
    public ResponseEntity<?> getRateType(@PathVariable Long rateTypeId) {
        RateTypeResponse response = rateTypeService.getRateType(rateTypeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public  ResponseEntity<?> createRateType(@RequestBody RateTypeRequest rateTypeRequest) {
        RateTypeResponse response = rateTypeService.createRateType(rateTypeRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{rateTypeId}")
    public ResponseEntity<?> updateRateType(@PathVariable Long rateTypeId, @RequestBody RateTypeRequest rateTypeRequest) {
        RateTypeResponse response = rateTypeService.updateRateType(rateTypeId, rateTypeRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{rateTypeId}")
    public ResponseEntity<?> updateRateType(@PathVariable Long rateTypeId, @RequestBody Map<Object, Object> fields) {
        RateTypeResponse response = rateTypeService.mergeRateType(rateTypeId, fields);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{rateTypeId}")
    public ResponseEntity<?> deleteRateType(@PathVariable Long rateTypeId) {
        rateTypeService.deleteRateType(rateTypeId);
        return ResponseEntity.ok(rateTypeId);
    }
}
