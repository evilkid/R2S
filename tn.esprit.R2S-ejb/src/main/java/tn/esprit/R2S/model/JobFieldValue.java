package tn.esprit.R2S.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by EvilKids on 10/30/2016.
 */
@Entity
@JsonIgnoreProperties("job")
public class JobFieldValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne(targetEntity = Job.class)
    private Job job;

    @ManyToOne(targetEntity = JobField.class)
    private JobField jobField;

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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobField getJobField() {
        return jobField;
    }

    public void setJobField(JobField jobField) {
        this.jobField = jobField;
    }
}
