package tn.esprit.R2S.controller;

import tn.esprit.R2S.model.Education;
import tn.esprit.R2S.service.EducationFacade;
import tn.esprit.R2S.controller.util.HeaderUtil;
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
 * REST controller for managing Education.
 */
@Path("/api/education")
public class EducationController {

    private final Logger log = LoggerFactory.getLogger(EducationController.class);

    @Inject
    private EducationFacade educationFacade;

    /**
     * POST : Create a new education.
     *
     * @param education the education to create
     * @return the Response with status 201 (Created) and with body the new
     * education, or with status 400 (Bad Request) if the education has already
     * an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @POST
    public Response createEducation(Education education) throws URISyntaxException {
        log.debug("REST request to save Education : {}", education);
        educationFacade.create(education);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/education/" + education.getId())),
                "education", education.getId().toString())
                .entity(education).build();
    }

    /**
     * PUT : Updates an existing education.
     *
     * @param education the education to update
     * @return the Response with status 200 (OK) and with body the updated
     * education, or with status 400 (Bad Request) if the education is not
     * valid, or with status 500 (Internal Server Error) if the education
     * couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PUT
    public Response updateEducation(Education education) throws URISyntaxException {
        log.debug("REST request to update Education : {}", education);
        educationFacade.edit(education);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "education", education.getId().toString())
                .entity(education).build();
    }

    /**
     * GET : get all the educations. <% if (pagination != 'no') {} @param
     * pageable the p
     *
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     * @return the Response with status 200 (OK) and the list of educations in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Education> getAllEducations() {
        log.debug("REST request to get all Educations");
        List<Education> educations = educationFacade.findAll();
        return educations;
    }

    /**
     * GET /:id : get the "id" education.
     *
     * @param id the id of the education to retrieve
     * @return the Response with status 200 (OK) and with body the education, or
     * with status 404 (Not Found)
     */
    @Path("/{id}")
    @GET
    public Response getEducation(@PathParam("id") Long id) {
        log.debug("REST request to get Education : {}", id);
        Education education = educationFacade.find(id);
        return Optional.ofNullable(education)
                .map(result -> Response.status(Response.Status.OK).entity(education).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * DELETE /:id : remove the "id" education.
     *
     * @param id the id of the education to delete
     * @return the Response with status 200 (OK)
     */
    @Path("/{id}")
    @DELETE
    public Response removeEducation(@PathParam("id") Long id) {
        log.debug("REST request to delete Education : {}", id);
        educationFacade.remove(educationFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "education", id.toString()).build();
    }

}
