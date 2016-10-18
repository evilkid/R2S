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
    private ICertificationService certificationService;


    @POST
    public Response createCertification(Certification certification) throws URISyntaxException {

        certificationService.create(certification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/certification/" + certification.getId())),
                "certification", certification.getId().toString())
                .entity(certification).build();
    }


    @PUT
    public Response updateCertification(Certification certification) throws URISyntaxException {

        certificationService.edit(certification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "certification", certification.getId().toString())
                .entity(certification).build();
    }


    @GET
    public List<Certification> getAllCertifications() {

        List<Certification> certifications = certificationService.findAll();
        return certifications;
    }


    @Path("/{id}")
    @GET
    public Response getCertification(@PathParam("id") Long id) {

        Certification certification = certificationService.find(id);
        return Optional.ofNullable(certification)
                .map(result -> Response.status(Response.Status.OK).entity(certification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCertification(@PathParam("id") Long id) {

        certificationService.remove(certificationService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "certification", id.toString()).build();
    }

}
