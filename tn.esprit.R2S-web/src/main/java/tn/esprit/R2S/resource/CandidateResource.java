package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.model.Candidate;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/api/candidate")
public class CandidateResource {


    @EJB
    private ICandidateService candidateService;

    @GET
    public List<Candidate> getAllCandidates(@QueryParam("skillId") int skillId) {
        System.out.println(skillId);
        return candidateService.findBySkillId(skillId);
    }

}
