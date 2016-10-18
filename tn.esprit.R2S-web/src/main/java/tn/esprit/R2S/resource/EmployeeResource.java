package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmployeeService;
import tn.esprit.R2S.model.Employee;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/employee")
public class EmployeeResource {

    @EJB
    private IEmployeeService employeeService;

    @POST
    public Response createEmployee(Employee employee) throws URISyntaxException {

        employeeService.create(employee);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/employee/" + employee.getCin())),
                "employee", employee.getCin().toString())
                .entity(employee).build();
    }
    @PUT
    public Response updateEmployee(Employee employee) throws URISyntaxException {

        employeeService.edit(employee);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "employee", employee.getCin().toString())
                .entity(employee).build();
    }
    @GET
    public List<Employee> getAllEmployees() {

        List<Employee> employees = employeeService.findAll();
        return employees;
    }
    @Path("/{cin}")
    @GET
    public Response getEmployee(@PathParam("cin") Long cin) {

        Employee employee = employeeService.find(cin);
        return Optional.ofNullable(employee)
                .map(result -> Response.status(Response.Status.OK).entity(employee).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{cin}")
    @DELETE
    public Response removeEmployee(@PathParam("cin") Long cin) {

        employeeService.remove(employeeService.find(cin));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "employee", cin.toString()).build();
    }

}
