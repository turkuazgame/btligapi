package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.CompetitorSeasonRequest;
import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="competitor_season")
public class CompetitorSeason implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="competitor_season_id")
    private Long competitorSeasonId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="competitor_id", referencedColumnName = "competitor_id")
    private Competitor competitor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "season_id", referencedColumnName = "season_id")
    private Season season;

    @Column(name="total_score")
    private double totalScore;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "competitor_week_id")
    private List<CompetitorWeek> weeks = new ArrayList<CompetitorWeek>();

    @Embedded
    private BaseInfo baseInfo;

    public boolean updateTotalScore() {
        return true;
    }

    public Long getCompetitorId() {
        if(this.competitor!=null)
            return competitor.getCompetitorId();
        else
            return 0L;
    }

    public Long getSeasonId() {
        if(this.season!=null)
            return season.getSeasonId();
        else
            return 0L;
    }

    public int getWeekListSize() {
        if(this.weeks!=null)
            return this.weeks.size();
        else
            return 0;
    }

    @Override
    public void setFromOther(IEntity other) {
        CompetitorSeason newEntity = (CompetitorSeason) other;
        this.setCompetitor(newEntity.getCompetitor());
        this.setSeason(newEntity.getSeason());
        this.setTotalScore(newEntity.getTotalScore());
        this.setWeeks(newEntity.getWeeks());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        CompetitorSeasonRequest req = (CompetitorSeasonRequest) request;
        this.competitorSeasonId = req.getCompetitorSeasonId();
        this.competitor = req.getCompetitor();
        this.season = req.getSeason();
        this.baseInfo.setFromRequest(req);
    }

}