package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICandidateFieldService;
import tn.esprit.R2S.interfaces.IJobFieldService;
import tn.esprit.R2S.model.CandidateField;
import tn.esprit.R2S.model.JobField;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by evilkid on 10/30/2016.
 */
@Path("/api/settings")
//@Secured(Roles.CHIEF_HUMAN_RESOURCES_OFFICER)
public class SettingResource {


    @EJB
    private IJobFieldService jobFieldService;

    @EJB
    private ICandidateFieldService candidateFieldService;

    @Path("job")
    @POST
    public Response addJobField(JobField jobField) {
        if (jobFieldService.findByName(jobField.getFieldName()) == null) {
            jobFieldService.create(jobField);
            return Response.ok(jobField).build();
        } else {
            throw new NotAllowedException("Field with the name " + jobField.getFieldName() + " already exist");
        }
    }

    @Path("candidate")
    @POST
    public Response addCandidateField(CandidateField candidateField) {
        if (candidateFieldService.findByName(candidateField.getFieldName()) == null) {
            candidateFieldService.create(candidateField);
            return Response.ok(candidateField).build();
        } else {
            throw new NotAllowedException("Field with the name " + candidateField.getFieldName() + " already exist");
        }
    }

    @Path("job/{jobField-id}")
    @PUT
    public Response editJobField(JobField jobField, @PathParam("jobField-id") Long id) {

        return Optional.ofNullable(jobFieldService.find(id))
                .map(field -> {

                    JobField jb = jobFieldService.findByName(jobField.getFieldName());
                    if (jb != null && jb.getId() != jobField.getId() && jb.getFieldName().equals(jobField.getFieldName())) {
                        throw new NotAllowedException("Field with the name " + jobField.getFieldName() + " already exist");
                    }

                    jobFieldService.edit(jobField);
                    return Response.ok(jobField).build();
                })
                .orElseThrow(NotFoundException::new);
    }

    @Path("candidate/{candidateField-id}")
    @PUT
    public Response editCandidateField(CandidateField candidateField, @PathParam("candidateField-id") Long id) {

        return Optional.ofNullable(candidateFieldService.find(id))
                .map(field -> {

                    CandidateField cf = candidateFieldService.findByName(candidateField.getFieldName());
                    if (cf != null && cf.getId() != candidateField.getId() && cf.getFieldName().equals(candidateField.getFieldName())) {
                        throw new NotAllowedException("Field with the name " + candidateField.getFieldName() + " already exist");
                    }

                    candidateFieldService.edit(candidateField);
                    return Response.ok(candidateField).build();
                })
                .orElseThrow(NotFoundException::new);
    }

    @Path("job/{jobField-id}")
    @DELETE
    public Response deleteJobField(@PathParam("jobField-id") Long id) {

        return Optional.ofNullable(jobFieldService.find(id))
                .map(field -> {
                    jobFieldService.remove(field);
                    return Response.ok().build();
                })
                .orElseThrow(NotFoundException::new);
    }

    @Path("candidate/{candidateField-id}")
    @DELETE
    public Response deleteCandidateField(@PathParam("candidateField-id") Long id) {

        return Optional.ofNullable(candidateFieldService.find(id))
                .map(field -> {
                    candidateFieldService.remove(field);
                    return Response.ok().build();
                })
                .orElseThrow(NotFoundException::new);
    }
}
