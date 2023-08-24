package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Data
@Entity
@Table(name="rate_type")
public class RateType implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rate_type_id")
    private Long rateTypeId;

    @Column(name="rate_type_code", unique = true)
    private String rateTypeCode;

    @Column(name="rate_type_name")
    private String rateTypeName;

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        RateType newEntity = (RateType) other;
        this.setRateTypeCode(newEntity.getRateTypeCode());
        this.setRateTypeName(newEntity.getRateTypeName());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {

    }

}
