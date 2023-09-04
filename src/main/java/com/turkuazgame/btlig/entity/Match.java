package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.MatchRequest;
import com.turkuazgame.btlig.request.WeekRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="matchs")
public class Match implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="match_id")
    private Long matchId;

    @Column(name="match_code", unique = true)
    private String matchCode;

    @Column(name="match_name")
    private String matchName;

    @Column(name="match_date")
    private Date matchDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "home_team_id", referencedColumnName = "team_id")
    private Team homeTeam;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "away_team_id", referencedColumnName = "team_id")
    private Team awayTeam;

    @Column(name="home_team_half_score")
    private short homeTeamHalfScore;

    @Column(name="away_team__half_score")
    private short awayTeamHalfScore;

    @Column(name="home_team_final_score")
    private short homeTeamFinalScore;

    @Column(name="away_team_final_score")
    private short awayTeamFinalScore;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="week_id", referencedColumnName = "week_id")
    private Week week;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private List<MatchRate> rates = new ArrayList<MatchRate>();

    @Embedded
    private BaseInfo baseInfo;

    public boolean updateMatchScore() {
        return true;
    }

    @Override
    public void setFromOther(IEntity other) {
        Match newEntity = (Match) other;
        this.setMatchCode(newEntity.getMatchCode());
        this.setMatchName(newEntity.getMatchName());
        this.setMatchDate(newEntity.getMatchDate());
        this.setHomeTeam(newEntity.getHomeTeam());
        this.setAwayTeam(newEntity.getAwayTeam());
        this.setHomeTeamHalfScore(newEntity.getHomeTeamHalfScore());
        this.setAwayTeamHalfScore(newEntity.getAwayTeamHalfScore());
        this.setHomeTeamFinalScore(newEntity.getHomeTeamFinalScore());
        this.setAwayTeamFinalScore(newEntity.getAwayTeamFinalScore());
        this.setWeek(newEntity.getWeek());
        this.setRates(newEntity.getRates());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        try {
            MatchRequest req = (MatchRequest) request;
            this.matchId = req.getWeekId();
            this.matchCode = req.getMatchCode();
            this.matchName = req.getMatchName();
            this.week = req.getWeek();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.matchDate = sdf.parse(req.getMatchDate());
            this.homeTeam = req.getHomeTeam();
            this.awayTeam = req.getAwayTeam();
            this.homeTeamHalfScore = req.getHomeTeamHalfScore();
            this.awayTeamHalfScore = req.getAwayTeamHalfScore();
            this.homeTeamFinalScore = req.getHomeTeamFinalScore();
            this.awayTeamFinalScore = req.getAwayTeamFinalScore();
            this.baseInfo.setFromRequest(req);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
