package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Employee;
import tn.esprit.R2S.service.EmployeeFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/employee")
public class EmployeeController {

    @Inject
    private EmployeeFacade employeeFacade;

    @POST
    public Response createEmployee(Employee employee) throws URISyntaxException {
        employeeFacade.create(employee);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/employee/" + employee.getCin())),
                "employee", employee.getCin().toString())
                .entity(employee).build();
    }

    @PUT
    public Response updateEmployee(Employee employee) throws URISyntaxException {
        employeeFacade.edit(employee);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "employee", employee.getCin().toString())
                .entity(employee).build();
    }

    @GET
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeFacade.findAll();
        return employees;
    }

    @Path("/{cin}")
    @GET
    public Response getEmployee(@PathParam("cin") Long cin) {
        Employee employee = employeeFacade.find(cin);
        return Optional.ofNullable(employee)
                .map(result -> Response.status(Response.Status.OK).entity(employee).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    public Response removeEmployee(@PathParam("cin") Long cin) {
        employeeFacade.remove(employeeFacade.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "employee", cin.toString()).build();
    }

}
