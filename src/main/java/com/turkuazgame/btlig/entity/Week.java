package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.IRequest;
import com.turkuazgame.btlig.request.SeasonRequest;
import com.turkuazgame.btlig.request.WeekRequest;
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
@Table(name="week")
public class Week implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="week_id")
    private Long weekId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="season_id", referencedColumnName = "season_id")
    private Season season;

    @Column(name="week_code", unique = true)
    private String weekCode;

    @Column(name="week_name")
    private String weekName;

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
        Week newEntity = (Week) other;
        this.setWeekCode(newEntity.getWeekCode());
        this.setWeekName(newEntity.getWeekName());
        this.setSeason(newEntity.getSeason());
        this.setStartDate(newEntity.getStartDate());
        this.setEndDate(newEntity.getEndDate());
        this.setMatches(newEntity.getMatches());
        newEntity.getBaseInfo().setCreatedBy(this.getBaseInfo().getCreatedBy());
        this.setBaseInfo(newEntity.getBaseInfo());
    }

    @Override
    public void setFromRequest(IRequest request) {
        try {
            WeekRequest req = (WeekRequest) request;
            this.weekId = req.getWeekId();
            this.weekCode = req.getWeekCode();
            this.weekName = req.getWeekName();
            this.season = req.getSeason();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            this.startDate = sdf.parse(req.getStartDate());
            this.endDate = sdf.parse(req.getEndDate());
            this.baseInfo.setFromRequest(req);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
