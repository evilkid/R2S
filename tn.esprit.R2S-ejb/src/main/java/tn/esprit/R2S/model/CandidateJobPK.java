//
// This file was generated by the JPA Modeler
//
package tn.esprit.R2S.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author EvilKids
 */
@Embeddable
public class CandidateJobPK implements Serializable {

    private Long job;

    private Long candidate;

    public CandidateJobPK() {

    }

    public Long getJob() {
        return this.job;
    }

    public void setJob(Long job) {
        this.job = job;
    }

    public Long getCandidate() {
        return this.candidate;
    }

    public void setCandidate(Long candidate) {
        this.candidate = candidate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.job);
        hash = 59 * hash + Objects.hashCode(this.candidate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateJobPK other = (CandidateJobPK) obj;
        if (!Objects.equals(this.job, other.job)) {
            return false;
        }
        if (!Objects.equals(this.candidate, other.candidate)) {
            return false;
        }
        return true;
    }

}
