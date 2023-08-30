package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.SeasonRequest;
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
@Table(name="season")
public class Season implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="season_id")
    private Long seasonId;

    @Column(name="season_code", unique = true)
    private String seasonCode;

    @Column(name="season_name")
    private String seasonName;

    @Column(name="season_type")
    private SeasonType seasonType;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @OneToMany
    @JoinColumn(name = "season_id")
    private List<Week> weeks = new ArrayList<Week>();

    @Embedded
    private BaseInfo baseInfo;

    public int getWeekListSize() {
        if(this.weeks!=null)
            return this.weeks.size();
        else
            return 0;
    }

    public Season(SeasonRequest request) {
        try {
            this.seasonId = request.getSeasonId();
            this.seasonCode = request.getSeasonCode();
            this.seasonName = request.getSeasonName();
            this.seasonType = SeasonType.valueOf(request.getSeasonType());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.startDate = sdf.parse(request.getStartDate());
            this.endDate = sdf.parse(request.getEndDate());
            this.baseInfo = new BaseInfo();
            this.baseInfo.setFromRequest(request);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFromOther(IEntity other) {
        Season newEntity = (Season) other;
        this.setSeasonCode(newEntity.getSeasonCode());
        this.setSeasonName(newEntity.getSeasonName());
        this.setSeasonType(newEntity.getSeasonType());
        this.setStartDate(newEntity.getStartDate());
        this.setEndDate(newEntity.getEndDate());
        this.setWeeks(newEntity.getWeeks());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        try {
            SeasonRequest req = (SeasonRequest) request;
            this.seasonId = req.getSeasonId();
            this.seasonCode = req.getSeasonCode();
            this.seasonName = req.getSeasonName();
            this.seasonType = SeasonType.valueOf(req.getSeasonType());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.startDate = sdf.parse(req.getStartDate());
            this.endDate = sdf.parse(req.getEndDate());
            this.baseInfo.setFromRequest(req);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}

