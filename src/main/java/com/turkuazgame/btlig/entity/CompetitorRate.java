package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="competitor_rate")
public class CompetitorRate implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="competitor_rate_id")
    private Long competitorRateId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="competitor_match_id", referencedColumnName = "competitor_match_id")
    private CompetitorMatch competitorMatch;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_rate_id", referencedColumnName = "match_rate_id")
    private MatchRate matchRate;

    @Column(name="rate_value")
    private double rateValue;

    @Column(name="rateRealFlag")
    private boolean rateRealFlag;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        CompetitorRate newEntity = (CompetitorRate) other;
        this.setCompetitorMatch(newEntity.getCompetitorMatch());
        this.setMatchRate(newEntity.getMatchRate());
        this.setRateValue(newEntity.getRateValue());
        this.setRateRealFlag(newEntity.isRateRealFlag());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {

    }

}
