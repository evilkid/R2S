package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.JobField;

import java.util.List;

/**
 * Created by evilkid on 10/30/2016.
 */
public interface IJobFieldService {

    void create(JobField jobField);

    JobField edit(JobField jobField);

    void remove(JobField jobField);

    JobField find(Object id);

    JobField findByName(String fieldName);

    List<JobField> findAll();

}
