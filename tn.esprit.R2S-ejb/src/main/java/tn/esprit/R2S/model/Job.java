package tn.esprit.R2S.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.R2S.util.enums.JobStatus;

@Entity
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String name;


    private String description;


    private Double salary;


    @Enumerated
    private JobStatus status;

    @ManyToOne(targetEntity = QuizModel.class)
    private QuizModel quizModel;

    @OneToMany(targetEntity = Reward.class, mappedBy = "job")
    private List<Reward> rewards;

    @OneToMany(targetEntity = CandidateJob.class, mappedBy = "job")
    private List<CandidateJob> candidates;

    @ManyToMany(targetEntity = Skill.class, mappedBy = "jobs")
    private List<Skill> skills;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public JobStatus getStatus() {
        return this.status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public QuizModel getQuizModel() {
        return this.quizModel;
    }

    public void setQuizModel(QuizModel quizModel) {
        this.quizModel = quizModel;
    }

    public List<Reward> getRewards() {
        return this.rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public List<CandidateJob> getCandidates() {
        return this.candidates;
    }

    public void setCandidates(List<CandidateJob> candidates) {
        this.candidates = candidates;
    }

    public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
