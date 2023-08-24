package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_type_id", referencedColumnName = "rate_type_id")
    private RateType rateType;

    @Column(name="rate_code", unique = true)
    private String rateCode;

    @Column(name="rate_name")
    private String rateName;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        Rate newEntity = (Rate) other;
        this.setRateCode(newEntity.getRateCode());
        this.setRateName(newEntity.getRateName());
        this.setRateType(newEntity.getRateType());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {

    }

}