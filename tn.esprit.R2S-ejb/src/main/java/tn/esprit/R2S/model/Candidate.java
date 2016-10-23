/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author EvilKids
 */
@Entity
@JsonIgnoreProperties({"certifications", "educations", "experiences", "jobs", "interviews", "candidateQuizModels", "candidateSkills"})
public class Candidate extends Users implements Serializable {

    @ManyToOne(targetEntity = Employee.class)
    private Employee referee;

    @OneToMany(targetEntity = Certification.class, mappedBy = "candidate")
    private List<Certification> certifications;

    @OneToMany(targetEntity = Education.class, mappedBy = "candidate")
    private List<Education> educations;

    @OneToMany(targetEntity = Experience.class, mappedBy = "candidate")
    private List<Experience> experiences;

    @OneToMany(targetEntity = CandidateJob.class, mappedBy = "candidate")
    private List<CandidateJob> jobs;

    @OneToMany(targetEntity = Interview.class, mappedBy = "candidate")
    private List<Interview> interviews;

    @OneToMany(targetEntity = CandidateQuizModel.class, mappedBy = "candidate")
    private List<CandidateQuizModel> candidateQuizModels;

    @OneToMany(targetEntity = CandidateSkill.class, mappedBy = "candidate")
    private List<CandidateSkill> candidateSkills;

    public Employee getReferee() {
        return this.referee;
    }

    public void setReferee(Employee referee) {
        this.referee = referee;
    }

    public List<Certification> getCertifications() {
        return this.certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Education> getEducations() {
        return this.educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Experience> getExperiences() {
        return this.experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<CandidateJob> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<CandidateJob> jobs) {
        this.jobs = jobs;
    }

    public List<Interview> getInterviews() {
        return this.interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }

    public List<CandidateQuizModel> getCandidateQuizModels() {
        return this.candidateQuizModels;
    }

    public void setCandidateQuizModels(List<CandidateQuizModel> candidateQuizModels) {
        this.candidateQuizModels = candidateQuizModels;
    }

    public List<CandidateSkill> getCandidateSkills() {
        return this.candidateSkills;
    }

    public void setCandidateSkills(List<CandidateSkill> candidateSkills) {
        this.candidateSkills = candidateSkills;
    }

}