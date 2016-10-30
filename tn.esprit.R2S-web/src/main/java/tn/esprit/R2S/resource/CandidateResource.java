package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateFieldService;
import tn.esprit.R2S.interfaces.ICandidateService;
import tn.esprit.R2S.model.Candidate;
import tn.esprit.R2S.model.CandidateField;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@Path("/api/candidate")
public class CandidateResource {


    @EJB
    private ICandidateService candidateService;

    @EJB
    private ICandidateFieldService candidateFieldService;

    @GET
    public List<Candidate> getAllCandidates(@QueryParam("skillId") int skillId) {
        return candidateService.findBySkillId(skillId);
    }

    @GET
    @Path("form")
    public List<CandidateField> getCandidateField() {
        return candidateFieldService.findAll();
    }

}
