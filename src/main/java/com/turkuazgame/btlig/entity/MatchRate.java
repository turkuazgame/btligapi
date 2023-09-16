package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.CompetitorWeekRequest;
import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.MatchRateRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="match_rate")
public class MatchRate implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="match_rate_id")
    private Long matchRateId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="match_id", referencedColumnName = "match_id")
    private Match match;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rate_id", referencedColumnName = "rate_id")
    private Rate rate;

    @Column(name="rate_value")
    private double rateValue;

    @Column(name="rate_real_flag")
    private boolean rateRealFlag;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        MatchRate newEntity = (MatchRate) other;
        this.setMatch(newEntity.getMatch());
        this.setRate(newEntity.getRate());
        this.setRateValue(newEntity.getRateValue());
        this.setRateRealFlag(newEntity.isRateRealFlag());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        MatchRateRequest req = (MatchRateRequest) request;
        this.matchRateId = req.getMatchRateId();
        this.match = req.getMatch();
        this.rate = req.getRate();
        this.rateValue = req.getRateValue();
        this.baseInfo.setFromRequest(req);
    }

}
