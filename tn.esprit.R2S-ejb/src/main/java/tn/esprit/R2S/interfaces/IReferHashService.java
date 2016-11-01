package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Employee;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.ReferHash;

/**
 * Created by EvilKids on 10/30/2016.
 */
public interface IReferHashService {
    ReferHash generateHash(Job job, Employee employee);

    ReferHash findByHash(String hash);
}
