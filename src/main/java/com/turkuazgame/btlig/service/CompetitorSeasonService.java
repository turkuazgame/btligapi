package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.entity.CompetitorSeason;
import com.turkuazgame.btlig.repository.CompetitorRepository;
import com.turkuazgame.btlig.repository.CompetitorSeasonRepository;
import com.turkuazgame.btlig.request.CompetitorSeasonRequest;
import com.turkuazgame.btlig.response.CompetitorResponse;
import com.turkuazgame.btlig.response.CompetitorSeasonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitorSeasonService {

    @Autowired
    CompetitorSeasonRepository competitorSeasonRepository;

    @Autowired
    CompetitorRepository competitorRepository;

    public List<CompetitorSeasonResponse> getAllCompetitorSeasons(Optional<Long> competitorId){
        List<CompetitorSeasonResponse> responseList = new ArrayList<CompetitorSeasonResponse>();
        List<CompetitorSeason> competitorSeasons;
        if(competitorId.isPresent()) {
            Long compId = competitorId.get();
            Competitor competitor = competitorRepository.findById(compId).orElse(null);
            competitorSeasons = competitorSeasonRepository.findByCompetitor(competitor);
        } else {
            competitorSeasons = competitorSeasonRepository.findAll();
        }

        for(CompetitorSeason competitorSeason : competitorSeasons) {
            CompetitorSeasonResponse response = new CompetitorSeasonResponse(competitorSeason);
            responseList.add(response);
        }
        return responseList;
    }

    public CompetitorSeasonResponse createCompetitorSeason(CompetitorSeasonRequest competitorSeasonRequest) {
        Competitor competitor = competitorRepository.findById(competitorSeasonRequest.getCompetitorId()).orElse(null);
        if(competitor==null)
            return null;

        CompetitorSeason competitorSeason = new CompetitorSeason();
        competitorSeason.setFromRequest(competitorSeasonRequest);

        competitorSeason.getBaseInfo().setCreatedBy(competitorSeason.getBaseInfo().getUpdatedBy());
        CompetitorSeason newCompetitorSeason = competitorSeasonRepository.save(competitorSeason);

        CompetitorSeasonResponse competitorSeasonResponse = new CompetitorSeasonResponse(newCompetitorSeason);
        return competitorSeasonResponse;
    }

    public CompetitorSeasonResponse getCompetitorSeason(Long competitorSeasonId) {
        CompetitorSeason competitorSeason = competitorSeasonRepository.findById(competitorSeasonId).orElse(null);
        CompetitorSeasonResponse response = new CompetitorSeasonResponse(competitorSeason);
        return response;
    }

    public CompetitorSeasonResponse updateCompetitorSeason(Long competitorSeasonId, CompetitorSeasonRequest competitorSeasonRequest) {
        CompetitorSeason newCompetitorSeason = new CompetitorSeason();
        newCompetitorSeason.setFromRequest(competitorSeasonRequest);

        Optional<CompetitorSeason> findCompetitorSeason = competitorSeasonRepository.findById(competitorSeasonId);
        if(findCompetitorSeason.isPresent()) {
            CompetitorSeason competitorSeason = findCompetitorSeason.get();
            competitorSeason.setBaseInfo(newCompetitorSeason.getBaseInfo());
            competitorSeasonRepository.save(competitorSeason);
            CompetitorSeasonResponse competitorSeasonResponse = new CompetitorSeasonResponse(newCompetitorSeason);
            return  competitorSeasonResponse;
        }
        else
            return null;
    }

    public void deleteCompetitorSeason(Long competitorSeasonId) {
        competitorSeasonRepository.deleteById(competitorSeasonId);
    }

}
