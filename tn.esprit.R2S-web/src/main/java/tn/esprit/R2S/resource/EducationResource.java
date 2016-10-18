package tn.esprit.R2S.resource;

import tn.esprit.R2S.model.Education;
import tn.esprit.R2S.resource.util.HeaderUtil;
import tn.esprit.R2S.service.EducationService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Education.
 */
@Path("/api/education")

public class EducationResource {


    @EJB
    private EducationService educationService;

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

        educationService.create(education);
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

        educationService.edit(education);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "education", education.getId().toString())
                .entity(education).build();
    }

    /**
     * GET : get all the educations. <% if (pagination != 'no') {} @param
     * pageable the p
     * <p>
     * agination information<% } if (fieldsContainNoOwnerOneToOne) {} @param
     * filter the filter of the r
     * equest<% }}
     *
     * @return the Response with status 200 (OK) and the list of educations in
     * body<% if (pagination != 'no') {} @throws URISyntaxExce
     * ption if there is an error to generate the pagination HTTP headers<% }}
     */
    @GET
    public List<Education> getAllEducations() {

        List<Education> educations = educationService.findAll();
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

        Education education = educationService.find(id);
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

        educationService.remove(educationService.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "education", id.toString()).build();
    }

}
