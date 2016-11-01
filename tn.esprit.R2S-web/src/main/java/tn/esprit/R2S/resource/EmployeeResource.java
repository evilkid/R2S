package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.IEmployeeService;
import tn.esprit.R2S.interfaces.IReferHashService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Optional;

@Path("/api/employee")
public class EmployeeResource {

    @EJB
    private IEmployeeService employeeService;

    @EJB
    private IReferHashService referHashService;

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "emailServiceEJB", lookup = "java:/jms/queue/R2S")
    private Queue emailServiceEJB;

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

    @GET
    @Path("/refer/{hash}/{candidate-email}")
    public Response referCandidateByEmail(@PathParam("hash") String hash, @PathParam("candidate-email") String candidateEmail) {

        return Optional.ofNullable(referHashService.findByHash(hash))
                .map(referHash -> {
                    StringBuilder contentBuilder = new StringBuilder();
                    contentBuilder
                            .append("Hello, there is an opening in our company. A job titled: ")
                            .append(referHash.getJob().getName())
                            .append(", with a salary of ")
                            .append(referHash.getJob().getSalary())
                            .append(", we would love to have you with us, if you are interested you can register at this link: ")
                            .append("www.r2s.com/register/")
                            .append(referHash.getHash());

                    notifyCandidate(candidateEmail, "Job Offer", contentBuilder.toString());

                    return Response.ok().build();
                })
                .orElseThrow(NotFoundException::new);
    }

    private void notifyCandidate(String email, String subject, String content) {
        try {
            final Connection connection = connectionFactory.createConnection();

            connection.start();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            final MessageProducer emails = session.createProducer(emailServiceEJB);


            HashMap<String, String> messages = new HashMap<>();

            messages.put("recipient", email);
            messages.put("subject", subject);
            messages.put("content", content);

            emails.send(session.createObjectMessage(messages));

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
