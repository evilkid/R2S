package tn.esprit.R2S.resource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import tn.esprit.R2S.interfaces.IEmployeeService;
import tn.esprit.R2S.interfaces.IReferHashService;
import tn.esprit.R2S.interfaces.ITokenService;
import tn.esprit.R2S.interfaces.IUsersService;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;
import tn.esprit.R2S.resource.util.TokenUtil;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
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

    @EJB
    private IUsersService usersService;
    @EJB
    private ITokenService tokenService;

    @Resource(name = "emailServiceEJB", lookup = "java:/jms/queue/R2S")
    private Queue emailServiceEJB;

    @Path("/referred")
    @GET
    @Secured(Roles.EMPLOYEE)
    public Response getReferred(@CookieParam("access_token") Cookie cookie) {
        Jws<Claims> claims = TokenUtil.getClaims(cookie, tokenService.getKey());

        Long cin = Long.parseLong((String) claims.getBody().get("cin"));

        return Response.ok(usersService.getReferred(cin)).build();
    }

    @Path("/rewards")
    @GET
    @Secured(Roles.EMPLOYEE)
    public Response getRewardPoints(@CookieParam("access_token") Cookie cookie) {
        Jws<Claims> claims = TokenUtil.getClaims(cookie, tokenService.getKey());

        Long cin = Long.parseLong((String) claims.getBody().get("cin"));

        return Response.ok(usersService.getRewardPoints(cin)).build();
    }

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
