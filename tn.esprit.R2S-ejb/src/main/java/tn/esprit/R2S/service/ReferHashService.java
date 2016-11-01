package tn.esprit.R2S.service;

import org.apache.commons.codec.digest.DigestUtils;
import tn.esprit.R2S.interfaces.IReferHashService;
import tn.esprit.R2S.model.Employee;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.ReferHash;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by EvilKids on 10/30/2016.
 */
@Stateless
public class ReferHashService implements IReferHashService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    @Override
    public ReferHash generateHash(Job job, Employee employee) {

        Date date = new Date();

        String hash = job.getId() + employee.getCin() + date.getTime() + "";

        hash = DigestUtils.md5Hex(hash);

        ReferHash referHash = new ReferHash(hash, date, job, employee);

        em.persist(referHash);

        return referHash;
    }

    @Override
    public ReferHash findByHash(String hash) {
        try {
            return em.find(ReferHash.class, hash);
        } catch (Exception e) {
            return null;
        }
    }
}
