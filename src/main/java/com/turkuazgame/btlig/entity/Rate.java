package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.RateRequest;
import com.turkuazgame.btlig.request.RateTypeRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="rate")
public class Rate implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rate_id")
    private Long rateId;

    @Column(name="rate_code", unique = true)
    private String rateCode;

    @Column(name="rate_name")
    private String rateName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rate_type_id", referencedColumnName = "rate_type_id")
    private RateType rateType;

    @Column(name="first_result_value")
    private String firstValue;

    @Column(name="second_result_value")
    private String secondValue;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        Rate newEntity = (Rate) other;
        this.setRateCode(newEntity.getRateCode());
        this.setRateName(newEntity.getRateName());
        this.setRateType(newEntity.getRateType());
        this.setFirstValue(newEntity.getFirstValue());
        this.setSecondValue(newEntity.getSecondValue());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        RateRequest req = (RateRequest) request;
        this.rateId = req.getRateTypeId();
        this.rateCode = req.getRateCode();
        this.rateName = req.getRateName();
        this.rateType = req.getRateType();
        this.firstValue = req.getFirstValue();
        this.secondValue = req.getSecondValue();
        this.baseInfo.setFromRequest(req);
    }

}