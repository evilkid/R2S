package tn.esprit.R2S.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Employee extends Users implements Serializable {


    private Integer credibility;

    @OneToMany(targetEntity = Candidate.class, mappedBy = "referee")
    private List<Candidate> referredCandidates;

    public Integer getCredibility() {
        return this.credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public List<Candidate> getReferredCandidates() {
        return this.referredCandidates;
    }

    public void setReferredCandidates(List<Candidate> referredCandidates) {
        this.referredCandidates = referredCandidates;
    }

}
