package tn.esprit.R2S.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EvilKids on 10/30/2016.
 */
@Entity
public class CandidateFieldValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne(targetEntity = CandidateField.class)
    private CandidateField candidateField;

    @ManyToOne(targetEntity = Candidate.class)
    private Candidate candidate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CandidateField getCandidateField() {
        return candidateField;
    }

    public void setCandidateField(CandidateField candidateField) {
        this.candidateField = candidateField;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
