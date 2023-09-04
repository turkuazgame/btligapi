package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.CompetitorMatchRequest;
import com.turkuazgame.btlig.request.IRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="competitor_match")
public class CompetitorMatch implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="competitor_match_id")
    private Long competitorMatchId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="competitor_week_id", referencedColumnName = "competitor_week_id")
    private CompetitorWeek competitorWeek;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", referencedColumnName = "match_id")
    private Match match;

    @OneToMany
    @JoinColumn(name = "competitor_rate_id")
    private List<CompetitorRate> rates = new ArrayList<CompetitorRate>();

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        CompetitorMatch newEntity = (CompetitorMatch) other;
        this.setCompetitorWeek(newEntity.getCompetitorWeek());
        this.setMatch(newEntity.getMatch());
        this.setRates(newEntity.getRates());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        CompetitorMatchRequest req = (CompetitorMatchRequest) request;
        this.competitorMatchId = req.getCompetitorMatchId();
        this.competitorWeek = req.getCompetitorWeek();
        this.match = req.getMatch();
        this.baseInfo.setFromRequest(req);
    }

}

