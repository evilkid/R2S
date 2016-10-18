/**
 * This file was generated by the JPA Modeler
 */
package tn.esprit.R2S.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

/**
 * @author EvilKids
 */
@Entity
public class CandidateSkill implements Serializable {

    @EmbeddedId
    private CandidateSkillPK candidateSkillPK;


    private Integer level;

    @MapsId("candidate")
    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    @MapsId("skill")
    @ManyToOne(targetEntity = Skill.class)
    private Skill skill;

    public CandidateSkillPK getCandidateSkillPK() {
        return this.candidateSkillPK;
    }

    public void setCandidateSkillPK(CandidateSkillPK candidateSkillPK) {
        this.candidateSkillPK = candidateSkillPK;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Candidate getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}
