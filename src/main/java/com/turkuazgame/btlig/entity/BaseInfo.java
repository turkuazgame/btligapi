package com.turkuazgame.btlig.entity;

import com.turkuazgame.btlig.request.BaseRequest;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Embeddable
public class BaseInfo {

    @Column(name="status")
    private String status;

    //@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Timestamp createDate;

    @Column(name="created_by")
    @CreatedBy
    private String createdBy;

    //@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Timestamp updateDate;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="updatedFrom")
    private String updatedFrom;

    public void BaseInfo(BaseRequest request) {
        this.setStatus(request.getStatus());
        this.setUpdatedFrom(request.getUpdatedFrom());
        this.setUpdatedBy(request.getUpdatedBy());
//        this.setCreatedBy(request.getUpdatedBy());
    }

    public void setFromRequest(BaseRequest request) {
        this.setStatus(request.getStatus());
        this.setUpdatedFrom(request.getUpdatedFrom());
        this.setUpdatedBy(request.getUpdatedBy());
    }

    @PrePersist
    protected void onCreate() {
        long tm = (new Date()).getTime();
        this.createDate = new Timestamp(tm);
        this.updateDate = new Timestamp(tm);
        this.createdBy = this.updatedBy;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Timestamp((new Date()).getTime());
    }
}
