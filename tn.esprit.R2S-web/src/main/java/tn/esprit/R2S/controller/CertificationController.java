package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Certification;
import tn.esprit.R2S.service.CertificationFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Path("/api/certification")

public class CertificationController {

    @Inject
    private CertificationFacade certificationFacade;

    @POST
    public Response createCertification(Certification certification) throws URISyntaxException {
        certificationFacade.create(certification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/certification/" + certification.getId())),
                "certification", certification.getId().toString())
                .entity(certification).build();
    }

    @PUT
    public Response updateCertification(Certification certification) throws URISyntaxException {
        certificationFacade.edit(certification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "certification", certification.getId().toString())
                .entity(certification).build();
    }

    @GET
    public List<Certification> getAllCertifications() {
        List<Certification> certifications = certificationFacade.findAll();
        return certifications;
    }

    @Path("/{id}")
    @GET
    public Response getCertification(@PathParam("id") Long id) {
        Certification certification = certificationFacade.find(id);
        return Optional.ofNullable(certification)
                .map(result -> Response.status(Response.Status.OK).entity(certification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCertification(@PathParam("id") Long id) {
        certificationFacade.remove(certificationFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "certification", id.toString()).build();
    }

}
