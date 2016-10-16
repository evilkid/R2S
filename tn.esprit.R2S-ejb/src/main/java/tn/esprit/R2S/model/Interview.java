package tn.esprit.R2S.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(targetEntity = Job.class)
    private Job job;

    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    @ManyToOne(targetEntity = RecruitmentManager.class)
    private RecruitmentManager recruitmentManager;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public RecruitmentManager getRecruitmentManager() {
        return this.recruitmentManager;
    }

    public void setRecruitmentManager(RecruitmentManager recruitmentManager) {
        this.recruitmentManager = recruitmentManager;
    }

}
