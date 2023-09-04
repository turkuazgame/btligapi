package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.RateTypeRequest;
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
        RateTypeRequest req = (RateTypeRequest) request;
        this.rateTypeId = req.getRateTypeId();
        this.rateTypeCode = req.getRateTypeCode();
        this.rateTypeName = req.getRateTypeName();
        this.baseInfo.setFromRequest(req);
    }

/*
MATCH_RESULT :
FINAL RESULT			RESULT_FINAL_1
						RESULT_FINAL_2
						RESULT_FINAL_0

HALF RESULT 			RESULT_HALF_1
						RESULT_HALF_2
						RESULT_HALF_0

HALF/FINAL RESULT		RESULT_HALF_FINAL_11
						RESULT_HALF_FINAL_10
						RESULT_HALF_FINAL_12
						RESULT_HALF_FINAL_01
						RESULT_HALF_FINAL_00
						RESULT_HALF_FINAL_02
						RESULT_HALF_FINAL_21
						RESULT_HALF_FINAL_20
						RESULT_HALF_FINAL_22

UP_DOWN :
HALF UP/DOWN  			UPDOWN_HALF_15_UP
						UPDOWN_HALF_15_DOWN

FINAL UP/DOWN	 		UPDOWN_FINAL_25_UP
						UPDOWN_FINAL_25_DOWN

TOTAL_SCORE :
TOTAL SCORE 			TOTAL_SCORE_01
						TOTAL_SCORE_23
						TOTAL_SCORE_45
						TOTAL_SCORE_6P

RECIPROCAL_SCORE :
RECIPROCAL_SCORE		RECIPROCAL_SCORE_YES
						RECIPROCAL_SCORE_NO
*/
}
