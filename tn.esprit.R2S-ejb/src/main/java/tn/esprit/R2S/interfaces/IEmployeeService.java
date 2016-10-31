package tn.esprit.R2S.interfaces;

import tn.esprit.R2S.model.Employee;

import java.util.List;

/**
 * Created by evilkid on 10/18/2016.
 */
public interface IEmployeeService {
    void create(Employee employee);

    Employee edit(Employee employee);

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    float calculReward();
}
