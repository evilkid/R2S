package tn.esprit.hrsmart.controller;

import tn.esprit.hrsmart.model.Certification;
import tn.esprit.hrsmart.service.CertificationFacade;
import tn.esprit.hrsmart.controller.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST controller for managing Certification.
 */
@Path("/api/certification")
public class CertificationController {

    private final Logger log = LoggerFactory.getLogger(CertificationController.class);

    @Inject
    private CertificationFacade certificationFacade;

    /**
     * POST : Create a new certification.
     *
     * @param certification the certification to create
     * @return the Response with status 201 (Created) and with body the new
     * certification, or with status 400 (Bad Request) if the certification has
     * already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createCertification(Certification certification) throws URISyntaxException {
        log.debug("REST request to save Certification : {}", certification);
        certificationFacade.create(certification);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/certification/" + certification.getId())),
                "certification", certification.getId().toString())
                .entity(certification).build();
    }

    /**
     * PUT : Updates an existing certification.
     *
     * @param certification the certification to update
     * @return the Response with status 200 (OK) and with body the updated
     * certification, or with status 400 (Bad Request) if the certification is
     * not valid, or with status 500 (Internal Server Error) if the
     * certification couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateCertification(Certification certification) throws URISyntaxException {
        log.debug("REST request to update Certification : {}", certification);
        certificationFacade.edit(certification);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "certification", certification.getId().toString())
                .entity(certification).build();
    }

    /**
     * GET : get all the certifications. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of certifications
     * in body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Certification> getAllCertifications() {
        log.debug("REST request to get all Certifications");
        List<Certification> certifications = certificationFacade.findAll();
        return certifications;
    }

    /**
     * GET /:id : get the "id" certification.
     *
     * @param id the id of the certification to retrieve
     * @return the Response with status 200 (OK) and with body the
     * certification, or with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getCertification(@PathParam("id") Long id) {
        log.debug("REST request to get Certification : {}", id);
        Certification certification = certificationFacade.find(id);
        return Optional.ofNullable(certification)
                .map(result -> Response.status(Response.Status.OK).entity(certification).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" certification.
     *
     * @param id the id of the certification to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeCertification(@PathParam("id") Long id) {
        log.debug("REST request to delete Certification : {}", id);
        certificationFacade.remove(certificationFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "certification", id.toString()).build();
    }

}
