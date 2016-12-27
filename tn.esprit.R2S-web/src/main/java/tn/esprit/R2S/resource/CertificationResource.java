package tn.esprit.R2S.resource;

import tn.esprit.R2S.interfaces.ICertificationService;
import tn.esprit.R2S.model.Certification;
import tn.esprit.R2S.resource.util.HeaderUtil;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/certification")
public class CertificationResource {

    @EJB
    private ICertificationService CertificationService;

    @POST
    public Response createCertification(Certification Certification) throws URISyntaxException {

        CertificationService.create(Certification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/Certification/" + Certification.getId())),
                "Certification", Certification.getId().toString())
                .entity(Certification).build();
    }

    @PUT
    public Response updateCertification(Certification Certification) throws URISyntaxException {

        CertificationService.edit(Certification);
        return Response.ok(Certification).build();
    }

    @GET
    public List<Certification> getAllCertifications() {

        List<Certification> Certifications = CertificationService.findAll();
        return Certifications;
    }

    @Path("/{id}")
    @GET

    public Response getCertification(@PathParam("id") Long id) {

        Certification Certification = CertificationService.find(id);
        return Optional.ofNullable(Certification)
                .map(result -> Response.status(Response.Status.OK).entity(Certification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCertification(@PathParam("id") Long id) {

        CertificationService.remove(CertificationService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "Certification", id.toString()).build();
    }

}
