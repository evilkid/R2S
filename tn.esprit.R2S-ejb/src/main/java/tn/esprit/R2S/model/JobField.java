package tn.esprit.R2S.model;

import tn.esprit.R2S.util.enums.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by EvilKids on 10/30/2016.
 */
@Entity
public class JobField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated
    private FieldType fieldType;

    private String fieldName;

    @OneToMany(targetEntity = JobFieldValue.class, mappedBy = "jobField", cascade = CascadeType.REMOVE)
    private List<JobFieldValue> jobFieldValues;

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

    public List<JobFieldValue> getJobFieldValues() {
        return jobFieldValues;
    }

    public void setJobFieldValues(List<JobFieldValue> jobFieldValues) {
        this.jobFieldValues = jobFieldValues;
    }
}
