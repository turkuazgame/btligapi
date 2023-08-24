package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.repository.CompetitorRepository;
import com.turkuazgame.btlig.request.CompetitorRequest;
import com.turkuazgame.btlig.response.CompetitorResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitorService {

    CompetitorRepository competitorRepository;
    BaseService service;

    @Autowired
    public CompetitorService(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
        this.service = new BaseService(competitorRepository, Competitor.class, CompetitorResponse.class);
    }

    public List<CompetitorResponse> getAllCompetitors(){
        List<CompetitorResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((CompetitorResponse) iresponse);
        return responseList;
    }

    public CompetitorResponse getCompetitor(Long competitorId) {
        return (CompetitorResponse) service.getEntity(competitorId);
    }

    public CompetitorResponse createCompetitor(CompetitorRequest competitorRequest) {
        return (CompetitorResponse) service.createEntity(competitorRequest);
    }

    public CompetitorResponse updateCompetitor(Long competitorId, CompetitorRequest competitorRequest) {
        return (CompetitorResponse) service.updateEntity(competitorId, competitorRequest);
    }

    public CompetitorResponse mergeCompetitor(Long competitorId, Map<Object, Object> fields) {
        return (CompetitorResponse) service.mergeEntity(competitorId,fields);
    }

    public void deleteCompetitor(Long competitorId) {
        service.deleteEntity(competitorId);
    }

    public CompetitorResponse getCompetitorByUser(String userUID) {
        if(userUID!=null && !userUID.equals("")) {
            List<Competitor> list = competitorRepository.findByUserUID(userUID);
            Competitor competitor = !list.isEmpty() ? list.get(0) : null;
            CompetitorResponse response = new CompetitorResponse(competitor);
            return response;
        } else
            return null;
    }

}
