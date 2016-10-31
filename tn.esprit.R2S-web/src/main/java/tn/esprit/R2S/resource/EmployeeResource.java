package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmployeeService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/api/employee")
public class EmployeeResource {

    @EJB
    private IEmployeeService employeeService;

    @GET
    @Path("{employee-cin}/{credibility}")
    public Response evaluate(@PathParam("employee-cin") Long employeeCin,
                             @PathParam("credibility") Integer credibility) {

        return Optional.ofNullable(employeeService.find(employeeCin))
                .map(employee -> {
                    employee.setCredibility(employee.getCredibility() + credibility);
                    employeeService.edit(employee);
                    return Response.ok(employee).build();
                }).orElseThrow(NotFoundException::new);

    }

}
