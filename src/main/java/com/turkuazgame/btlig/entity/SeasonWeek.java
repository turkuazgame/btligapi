package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.response.IResponse;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name="season_week")
public class SeasonWeek implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="season_week_id")
    private Long seasonWeekId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="season_id", referencedColumnName = "season_id")
    private Season season;

    @Column(name="season_week_code", unique = true)
    private String seasonWeekCode;

    @Column(name="season_week_name")
    private String seasonWeekName;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="end_date")
    private Date endDate;

    @OneToMany
    @JoinColumn(name="match_id")
    private List<Match> matches = new ArrayList<Match>();

    @Embedded
    private BaseInfo baseInfo;

    @Override
    public void setFromOther(IEntity other) {
        SeasonWeek newEntity = (SeasonWeek) other;
        this.setSeasonWeekCode(newEntity.getSeasonWeekCode());
        this.setSeasonWeekName(newEntity.getSeasonWeekName());
        this.setSeason(newEntity.getSeason());
        this.setStartDate(newEntity.getStartDate());
        this.setEndDate(newEntity.getEndDate());
        this.setMatches(newEntity.getMatches());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {

    }

}
