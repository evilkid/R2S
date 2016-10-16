package tn.esprit.R2S.controller;

import tn.esprit.R2S.controller.util.HeaderUtil;
import tn.esprit.R2S.model.Category;
import tn.esprit.R2S.service.CategoryFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/api/category")
public class CategoryController {

    @Inject
    private CategoryFacade categoryFacade;
    @POST
    public Response createCategory(Category category) throws URISyntaxException {
        categoryFacade.create(category);
        return HeaderUtil.createEntityCreationAlert(Response.created(new URI("/resources/api/category/" + category.getId())),
                "category", category.getId().toString())
                .entity(category).build();
    }

    @PUT
    public Response updateCategory(Category category) throws URISyntaxException {
        categoryFacade.edit(category);
        return HeaderUtil.createEntityUpdateAlert(Response.ok(), "category", category.getId().toString())
                .entity(category).build();
    }

    @GET
    public List<Category> getAllCategories() {
        List<Category> categories = categoryFacade.findAll();
        return categories;
    }

    @Path("/{id}")
    @GET
    public Response getCategory(@PathParam("id") Long id) {
        Category category = categoryFacade.find(id);
        return Optional.ofNullable(category)
                .map(result -> Response.status(Response.Status.OK).entity(category).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @Path("/{id}")
    @DELETE
    public Response removeCategory(@PathParam("id") Long id) {
        categoryFacade.remove(categoryFacade.find(id));
        return HeaderUtil.createEntityDeletionAlert(Response.ok(), "category", id.toString()).build();
    }

}
