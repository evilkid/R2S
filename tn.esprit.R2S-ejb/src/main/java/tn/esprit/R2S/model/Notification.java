package tn.esprit.R2S.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    private String message;

    @ManyToOne(targetEntity = RecruitmentManager.class)
    private RecruitmentManager recruitmentManager;

    @ManyToOne(targetEntity = Job.class)
    private Job job;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Optional<Date> getDate() {
        return Optional.ofNullable(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RecruitmentManager getRecruitmentManager() {
        return this.recruitmentManager;
    }

    public void setRecruitmentManager(RecruitmentManager recruitmentManager) {
        this.recruitmentManager = recruitmentManager;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}
