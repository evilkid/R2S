package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IJobFieldService;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.JobField;
import tn.esprit.R2S.model.JobFieldValue;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by EvilKids on 10/30/2016.
 */

@Stateless
public class JobFieldService extends AbstractService<JobField> implements IJobFieldService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public JobFieldService() {
        super(JobField.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public JobField findByName(String fieldName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<JobField> query = criteriaBuilder.createQuery(JobField.class);
        Root<JobField> jobFieldRoot = query.from(JobField.class);

        query
                .select(jobFieldRoot)
                .where(criteriaBuilder.equal(
                        jobFieldRoot.get("fieldName"), fieldName)
                );

        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public JobFieldValue findValue(JobField jobField, Job job) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<JobFieldValue> query = criteriaBuilder.createQuery(JobFieldValue.class);
        Root<JobFieldValue> jobFieldValueRoot = query.from(JobFieldValue.class);

        query
                .select(jobFieldValueRoot)
                .where(
                        criteriaBuilder.equal(jobFieldValueRoot.get("jobField"), jobField),
                        criteriaBuilder.equal(jobFieldValueRoot.get("job"), job)
                );

        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
