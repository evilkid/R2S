package tn.esprit.R2S.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by evilkid on 10/30/2016.
 */

@Entity
public class ReferHash {

    @Id
    private String hash;

    private Date generateAt;

    @ManyToOne(targetEntity = Job.class)
    private Job job;

    @ManyToOne(targetEntity = Employee.class)
    private Employee employee;

    public ReferHash() {
    }

    public ReferHash(String hash, Date generateAt, Job job, Employee employee) {
        this.hash = hash;
        this.generateAt = generateAt;
        this.job = job;
        this.employee = employee;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getGenerateAt() {
        return generateAt;
    }

    public void setGenerateAt(Date generateAt) {
        this.generateAt = generateAt;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "ReferHash{" +
                "hash='" + hash + '\'' +
                ", generateAt=" + generateAt +
                ", job=" + job +
                ", employee=" + employee +
                '}';
    }
}
