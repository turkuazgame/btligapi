package com.turkuazgame.btlig.service;

import com.turkuazgame.btlig.entity.*;
import com.turkuazgame.btlig.repository.*;
import com.turkuazgame.btlig.request.MatchRateRequest;
import com.turkuazgame.btlig.response.MatchRateResponse;
import com.turkuazgame.btlig.response.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchRateService {

    MatchRateRepository matchRateRepository;
    MatchRepository matchRepository;
    RateRepository rateRepository;
    BaseService service;

    @Autowired
    public MatchRateService(MatchRateRepository matchRateRepository,
                                 MatchRepository matchRepository,
                                 RateRepository rateRepository) {
        this.matchRateRepository = matchRateRepository;
        this.matchRepository = matchRepository;
        this.rateRepository = rateRepository;
        this.service = new BaseService(matchRateRepository, MatchRate.class, MatchRateResponse.class);
    }

    public List<MatchRateResponse> getMatchRates(Optional<Long> matchId, Optional<Long> rateId){
        if(matchId.isPresent() && rateId.isPresent())
            return getMatchRateByMatchAndRate(matchId.get(), rateId.get());
        else if(matchId.isPresent())
            return getMatchRateByMatch(matchId.get());
        else if(rateId.isPresent())
            return getMatchRateByRate(rateId.get());
        else
            return getAllMatchRates();
    }
    public List<MatchRateResponse> getAllMatchRates(){
        List<MatchRateResponse> responseList = new ArrayList<>();
        for(IResponse iresponse : service.getAllEntities())
            responseList.add((MatchRateResponse) iresponse);
        return responseList;
    }

    public MatchRateResponse getMatchRate(Long matchRateId) {
        return (MatchRateResponse) service.getEntity(matchRateId);
    }

    public MatchRateResponse createMatchRate(MatchRateRequest matchRateRequest) {
        Match match = matchRepository.findById(matchRateRequest.getMatchId()).orElse(null);
        matchRateRequest.setMatch(match);
        Rate rate = rateRepository.findById(matchRateRequest.getRateId()).orElse(null);
        matchRateRequest.setRate(rate);
        return (MatchRateResponse) service.createEntity(matchRateRequest);
    }

    public MatchRateResponse updateMatchRate(Long matchRateId, MatchRateRequest matchRateRequest) {
        Match match = matchRepository.findById(matchRateRequest.getMatchId()).orElse(null);
        matchRateRequest.setMatch(match);
        Rate rate = rateRepository.findById(matchRateRequest.getRateId()).orElse(null);
        matchRateRequest.setRate(rate);
        return (MatchRateResponse) service.updateEntity(matchRateId, matchRateRequest);
    }

    public MatchRateResponse mergeMatchRate(Long matchRateId, Map<Object, Object> fields) {
        if(fields.containsKey("matchId")) {
            Match match = matchRepository.findById(Long.parseLong(fields.get("matchId").toString())).orElse(null);
            fields.remove("matchId");
            fields.put("match", match);
        }
        if(fields.containsKey("rateId")) {
            Rate rate = rateRepository.findById(Long.parseLong(fields.get("rateId").toString())).orElse(null);
            fields.remove("rateId");
            fields.put("rate", rate);
        }
        return (MatchRateResponse) service.mergeEntity(matchRateId,fields);
    }

    public void deleteMatchRate(Long matchRateId) {
        service.deleteEntity(matchRateId);
    }

    public List<MatchRateResponse> getMatchRateByMatch(Long matchId) {
        if(matchId!=null && matchId>0) {
            Match match = matchRepository.findById(matchId).orElse(null);
            if(match!=null) {
                List<MatchRate> list = matchRateRepository.findByMatch(match);
                List<MatchRateResponse> responseList = new ArrayList<>();
                for(MatchRate matchRate : list) {
                    MatchRateResponse response = new MatchRateResponse(matchRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<MatchRateResponse> getMatchRateByRate(Long rateId) {
        if(rateId!=null && rateId>0) {
            Rate rate = rateRepository.findById(rateId).orElse(null);
            if(rate !=null) {
                List<MatchRate> list = matchRateRepository.findByRate(rate);
                List<MatchRateResponse> responseList = new ArrayList<>();
                for(MatchRate matchRate : list) {
                    MatchRateResponse response = new MatchRateResponse(matchRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public List<MatchRateResponse> getMatchRateByMatchAndRate(Long matchId, Long rateId) {
        if(matchId!=null && matchId>0 && rateId!=null && rateId>0) {
            Match match = matchRepository.findById(matchId).orElse(null);
            Rate rate = rateRepository.findById(rateId).orElse(null);
            if(match!=null && rate !=null) {
                List<MatchRate> list = matchRateRepository.findByMatchAndRate(match, rate);
                List<MatchRateResponse> responseList = new ArrayList<>();
                for(MatchRate matchRate : list) {
                    MatchRateResponse response = new MatchRateResponse(matchRate);
                    responseList.add(response);
                }
                return responseList;
            }
            else
                return null;
        } else
            return null;
    }

    public boolean calculateRateRealization(MatchRate matchRate) {

        ResultSubject resultSubject = matchRate.getRate().getRateType().getResultSubject();
        ResultPeriod resultPeriod = matchRate.getRate().getRateType().getResultPeriod();
        ResultAction resultAction = matchRate.getRate().getRateType().getResultAction();
        Match match = matchRate.getMatch();

        if(resultSubject.equals(ResultSubject.WIN)) {
            if(resultAction.equals(ResultAction.RESULT)) {
                if(resultPeriod.equals(ResultPeriod.HALF)) {
                    return switch (matchRate.getRate().getFirstValue()) {
                        case "1" -> match.getHomeTeamHalfScore() > match.getAwayTeamHalfScore();
                        case "2" -> match.getAwayTeamHalfScore() > match.getHomeTeamHalfScore();
                        case "0" -> match.getHomeTeamHalfScore() == match.getAwayTeamHalfScore();
                        default -> false;
                    };
                }
                else if(resultPeriod.equals(ResultPeriod.FINAL)) {
                    return switch (matchRate.getRate().getFirstValue()) {
                        case "1" -> match.getHomeTeamFinalScore() > match.getAwayTeamFinalScore();
                        case "2" -> match.getAwayTeamFinalScore() > match.getHomeTeamFinalScore();
                        case "0" -> match.getHomeTeamFinalScore() == match.getAwayTeamFinalScore();
                        default -> false;
                    };
                }
                else
                    return false;
            }
            else
                return false;
        }
        else if(matchRate.getRate().getRateType().getResultSubject().equals(ResultSubject.SCORE)) {
            if(resultAction.equals(ResultAction.UPDOWN)) {
                double updown = Double.parseDouble(matchRate.getRate().getFirstValue().charAt(0)+"."+matchRate.getRate().getFirstValue().substring(1));
                if(resultPeriod.equals(ResultPeriod.HALF)) {
                    double halfTotalScore = match.getHomeTeamHalfScore()+match.getAwayTeamHalfScore();
                    if(matchRate.getRate().getSecondValue().equals("UP"))
                        return halfTotalScore > updown;
                    else if(matchRate.getRate().getSecondValue().equals("DOWN"))
                        return halfTotalScore < updown;
                    else
                        return false;
                }
                else if(resultPeriod.equals(ResultPeriod.FINAL)) {
                    double finalTotalScore = match.getHomeTeamFinalScore()+match.getAwayTeamFinalScore();
                    if(matchRate.getRate().getSecondValue().equals("UP"))
                        return finalTotalScore > updown;
                    else if(matchRate.getRate().getSecondValue().equals("DOWN"))
                        return finalTotalScore < updown;
                    else
                        return false;
                }
            }
            else if(resultAction.equals(ResultAction.TOTAL)) {
                short startScore = Short.parseShort(matchRate.getRate().getFirstValue());
                short endScore = Short.parseShort(matchRate.getRate().getSecondValue());
                if(resultPeriod.equals(ResultPeriod.HALF)) {
                    short halfTotalScore = (short) (match.getHomeTeamHalfScore()+match.getAwayTeamHalfScore());
                    return halfTotalScore >= startScore && halfTotalScore <= endScore;
                }
                else if(resultPeriod.equals(ResultPeriod.FINAL)) {
                    short finalTotalScore = (short) (match.getHomeTeamFinalScore()+match.getAwayTeamFinalScore());
                    return finalTotalScore >= startScore && finalTotalScore <= endScore;
                }
            }
            else if(resultAction.equals(ResultAction.RECIPROCAL)) {
                boolean reciprocalFlag = Boolean.parseBoolean(matchRate.getRate().getFirstValue());
                if(resultPeriod.equals(ResultPeriod.HALF))
                    return reciprocalFlag && match.getHomeTeamHalfScore() > 0 && match.getAwayTeamHalfScore() > 0;
                else if(resultPeriod.equals(ResultPeriod.FINAL))
                    return reciprocalFlag && match.getHomeTeamFinalScore() > 0 && match.getAwayTeamFinalScore() > 0;
            }
            else
                return false;
        }

        return false;
    }

}
