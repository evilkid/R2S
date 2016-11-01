package tn.esprit.R2S.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import tn.esprit.R2S.util.enums.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by EvilKids on 10/30/2016.
 */
@Entity
@JsonIgnoreProperties({"candidateFieldValues"})
public class CandidateField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private FieldType fieldType;

    private String fieldName;

    @OneToMany(targetEntity = CandidateFieldValue.class, mappedBy = "candidateField", cascade = CascadeType.REMOVE)
    private List<CandidateFieldValue> candidateFieldValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<CandidateFieldValue> getCandidateFieldValues() {
        return candidateFieldValues;
    }

    public void setCandidateFieldValues(List<CandidateFieldValue> candidateFieldValues) {
        this.candidateFieldValues = candidateFieldValues;
    }
}
