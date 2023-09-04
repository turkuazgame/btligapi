package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.CompetitorWeekRequest;
import com.turkuazgame.btlig.request.IRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="competitor_week")
public class CompetitorWeek implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="competitor_week_id")
    private Long competitorWeekId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="competitor_season_id", referencedColumnName = "competitor_season_id")
    private CompetitorSeason competitorSeason;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "week_id", referencedColumnName = "week_id")
    private Week week;

    @Column(name="total_score")
    private double totalScore;

    @OneToMany
    @JoinColumn(name = "competitor_week_id")
    private List<CompetitorMatch> matches = new ArrayList<CompetitorMatch>();

    @Embedded
    private BaseInfo baseInfo;

    public boolean updateTotalScore() {
        return true;
    }

    @Override
    public void setFromOther(IEntity other) {
        CompetitorWeek newEntity = (CompetitorWeek) other;
        this.setCompetitorSeason(newEntity.getCompetitorSeason());
        this.setWeek(newEntity.getWeek());
        this.setTotalScore(newEntity.getTotalScore());
        this.setMatches(newEntity.getMatches());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        CompetitorWeekRequest req = (CompetitorWeekRequest) request;
        this.competitorWeekId = req.getCompetitorWeekId();
        this.competitorSeason = req.getCompetitorSeason();
        this.week = req.getWeek();
        this.baseInfo.setFromRequest(req);
    }

}