package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Ouerghi Yassine
 */
@Entity
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;

    @OneToOne(targetEntity = CandidateSkill.class, mappedBy = "skill")
    private CandidateSkill candidateSkill;

    @ManyToMany(targetEntity = Job.class)
    private List<Job> jobs;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CandidateSkill getCandidateSkill() {
        return this.candidateSkill;
    }

    public void setCandidateSkill(CandidateSkill candidateSkill) {
        this.candidateSkill = candidateSkill;
    }

    public List<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}
