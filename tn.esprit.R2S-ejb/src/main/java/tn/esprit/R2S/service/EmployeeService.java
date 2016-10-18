package tn.esprit.R2S.service;

import tn.esprit.R2S.model.Employee;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("employee")
public class EmployeeService extends AbstractService<Employee> {

    @PersistenceContext(unitName = "R2S_PU")
    private EntityManager em;

    public EmployeeService() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
