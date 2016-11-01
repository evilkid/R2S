package tn.esprit.R2S.resource;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import tn.esprit.R2S.interfaces.*;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.Interview;
import tn.esprit.R2S.model.Job;
import tn.esprit.R2S.model.RecruitmentManager;
import tn.esprit.R2S.resource.util.Roles;
import tn.esprit.R2S.resource.util.Secured;
import tn.esprit.R2S.resource.util.TokenUtil;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Optional;

@Path("/api/interview")
@Secured(Roles.RECRUITMENT_MANAGER)
public class InterviewResource {


    @EJB
    private IInterviewService interviewService;

    @EJB
    private ICandidateService candidateService;

    @EJB
    private IRecruitmentManagerService recruitmentManagerService;
    @EJB
    private IJobService jobService;

    @EJB
    private ITokenService tokenService;

    @Resource
    private ConnectionFactory connectionFactory;

    @Resource(name = "emailServiceEJB", lookup = "java:/jms/queue/R2S")
    private Queue emailServiceEJB;


    @POST
    public Response createInterview(Interview interview) throws URISyntaxException {

        interviewService.create(interview);

        notifyNewInterview(interview);

        return Response.created(new URI("/resources/api/interview/" + interview.getId())).entity(interview).build();
    }

    @PUT
    @Path("{interview-id}")
    public Response updateInterview(@PathParam("interview-id") Long interviewId,
                                    Interview interview) throws URISyntaxException {

        return Optional.ofNullable(interviewService.find(interviewId))
                .map(interv -> {
                    if (!interview.equals(interv)) {
                        throw new NotAllowedException("Different Ids passed");
                    }

                    interv.setDate(interview.getDate());

                    interviewService.edit(interv);

                    notifyUpdatedInterview(interv);

                    return Response.ok(interv).build();
                })
                .orElseThrow(NotFoundException::new);
    }

    @GET
    public Response getAllInterviews(@CookieParam("access_token") Cookie token) {
        System.out.println(token);

        Jws<Claims> claims = TokenUtil.getClaims(token, tokenService.getKey());

        Long cin = Long.parseLong((String) claims.getBody().get("cin"));

        RecruitmentManager rm = recruitmentManagerService.findInitializeInterviews(cin);

        return Response.ok(rm.getInterviews()).build();
    }

    @Path("/{id}")
    @GET
    public Response getInterview(@PathParam("id") Long id) {

        return Optional.ofNullable(interviewService.find(id))
                .map(interview -> Response.status(Response.Status.OK).entity(interview).build())
                .orElseThrow(NotFoundException::new);
    }

    @Path("/{id}")
    @DELETE
    public Response removeInterview(@PathParam("id") Long id) {

        interviewService.remove(interviewService.find(id));
        return Response.ok().build();
    }


    public void notifyNewInterview(Interview interview) {
        Candidate candidate = candidateService.find(interview.getCandidate().getCin());
        Job job = jobService.find(interview.getJob().getId());

        notifyCandidate(candidate, "You have an interview",
                "Dear " + candidate.getFirstname() + " " + candidate.getLastname()
                        + ", You have an interview for the job " + job.getName() + ", "
                        + "At " + interview.getDate());
    }

    public void notifyUpdatedInterview(Interview interview) {

        Candidate candidate = candidateService.find(interview.getCandidate().getCin());
        Job job = jobService.find(interview.getJob().getId());

        notifyCandidate(candidate, "Your interview is updated",
                "Dear " + candidate.getFirstname() + " " + candidate.getLastname()
                        + ", Your have an interview for the job " + job.getName() + ", "
                        + "is changed to: " + interview.getDate());
    }

    private void notifyCandidate(Candidate candidate, String subject, String content) {
        try {
            final Connection connection = connectionFactory.createConnection();

            connection.start();

            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            final MessageProducer emails = session.createProducer(emailServiceEJB);


            HashMap<String, String> messages = new HashMap<>();

            messages.put("recipient", candidate.getEmail());
            messages.put("subject", subject);
            messages.put("content", content);

            emails.send(session.createObjectMessage(messages));

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
