package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.Employee;
import tn.esprit.R2S.service.EmployeeFacade;
import tn.esprit.R2S.controller.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST controller for managing Employee.
 */
@Path("/api/employee")
public class EmployeeController {

    private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Inject
    private EmployeeFacade employeeFacade;

    /**
     * POST : Create a new employee.
     *
     * @param employee the employee to create
     * @return the Response with status 201 (Created) and with body the new
     * employee, or with status 400 (Bad Request) if the employee has already an
     * ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createEmployee(Employee employee) throws URISyntaxException {
        log.debug("REST request to save Employee : {}", employee);
        employeeFacade.create(employee);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/employee/" + employee.getCin())),
                "employee", employee.getCin().toString())
                .entity(employee).build();
    }

    /**
     * PUT : Updates an existing employee.
     *
     * @param employee the employee to update
     * @return the Response with status 200 (OK) and with body the updated
     * employee, or with status 400 (Bad Request) if the employee is not valid,
     * or with status 500 (Internal Server Error) if the employee couldn't be
     * updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateEmployee(Employee employee) throws URISyntaxException {
        log.debug("REST request to update Employee : {}", employee);
        employeeFacade.edit(employee);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "employee", employee.getCin().toString())
                .entity(employee).build();
    }

    /**
     * GET : get all the employees. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of employees in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Employee> getAllEmployees() {
        log.debug("REST request to get all Employees");
        List<Employee> employees = employeeFacade.findAll();
        return employees;
    }

    /**
     * GET /:cin : get the "cin" employee.
     *
     * @param cin the cin of the employee to retrieve
     * @return the Response with status 200 (OK) and with body the employee, or
     * with status 404 (Not Found)
     */
    @Path("/{cin}")
    @GET
    public Response getEmployee(@PathParam("cin") Long cin) {
        log.debug("REST request to get Employee : {}", cin);
        Employee employee = employeeFacade.find(cin);
        return Optional.ofNullable(employee)
                .map(result -> Response.status(Response.Status.OK).entity(employee).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:cin : remove the "cin" employee.
     *
     * @param cin the cin of the employee to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{cin}")
    @DELETE
    public Response removeEmployee(@PathParam("cin") Long cin) {
        log.debug("REST request to delete Employee : {}", cin);
        employeeFacade.remove(employeeFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "employee", cin.toString()).build();
    }

}
