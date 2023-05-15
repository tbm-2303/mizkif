package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RecipeDTO;
import entities.User;
import errorhandling.NotFoundException;
import facades.RecipeFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("recipe")
public class RecipeResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final RecipeFacade recipeFacade =  RecipeFacade.getRecipeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"recipe endpoint\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllRecipes() throws NotFoundException {
        List<RecipeDTO> recipes = recipeFacade.getAllRecipes();
        return Response.ok().entity(GSON.toJson(recipes)).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRecipeById(@PathParam("id") Long id) throws NotFoundException {
        RecipeDTO recipe = recipeFacade.getRecipeById(id);
        return Response.ok().entity(GSON.toJson(recipe)).build();
    }

    @GET
    @Path("/myRecipes/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllRecipesByUsername(@PathParam("username") String username) throws NotFoundException {
        List<RecipeDTO> recipes = recipeFacade.getAllRecipesByUsername(username);
        return Response.ok().entity(GSON.toJson(recipes)).build();
    }

    @POST
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createRecipe(String recipe) {
        RecipeDTO recipeDTO = GSON.fromJson(recipe, RecipeDTO.class);
        RecipeDTO created = recipeFacade.createRecipe(recipeDTO);
        return Response.ok().entity(GSON.toJson(created)).build();
    }



}