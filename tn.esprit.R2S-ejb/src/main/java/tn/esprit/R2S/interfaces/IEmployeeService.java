package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Employee;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IEmployeeService {
    void create(Employee entity);

    Employee edit(Employee entity);

    void remove(Employee entity);

    Employee find(Object id);

    List<Employee> findAll();
}
