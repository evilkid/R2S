package tn.esprit.R2S.service;

import tn.esprit.R2S.interfaces.IEmployeeService;
import tn.esprit.R2S.model.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class EmployeeService extends AbstractService<Employee> implements IEmployeeService {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public EmployeeService() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public float calculReward() {
        return sommeRewards() * 15;
    }

    float sommeRewards() {
        return 0;
    }
}
