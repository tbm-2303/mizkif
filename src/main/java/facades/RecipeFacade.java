package facades;

import dtos.RecipeDTO;
import dtos.RecipeIngredientDTO;
import entities.Ingredient;
import entities.Recipe;
import entities.RecipeIngredient;
import entities.User;
import errorhandling.NotFoundException;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class RecipeFacade {

    private static EntityManagerFactory emf;
    private static RecipeFacade instance;

    private RecipeFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static RecipeFacade getRecipeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }

    public List<RecipeDTO> getAllRecipes() throws NotFoundException {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<RecipeDTO> query = em.createQuery("SELECT new dtos.RecipeDTO(r) FROM Recipe r", RecipeDTO.class);
            List<RecipeDTO> recipes = query.getResultList();
            if (recipes.isEmpty()) {
                throw new NotFoundException("No recipes found");
            }
            return recipes;
        } finally {
            em.close();
        }
    }


    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, recipeDTO.getUserName());
        Recipe recipe = new Recipe(recipeDTO.getTitle(), recipeDTO.getDescription(), recipeDTO.getInstructions(), user);

        List<RecipeIngredient> list = new ArrayList<>();
        for (RecipeIngredientDTO ridto : recipeDTO.getRecipeIngredientDTOS()) {
            RecipeIngredient recipeIngredient = new RecipeIngredient(ridto.getAmount(),ridto.getUnit(),ridto.getName());
            Ingredient i = em.find(Ingredient.class, ridto.getIngredientId());
            recipeIngredient.addIngredient(i);
            //recipeIngredient.addRecipe(recipe);
            list.add(recipeIngredient);
        }
        try {
            em.getTransaction().begin();
            em.persist(recipe);
            for (RecipeIngredient recipeIngredient : list) {
                recipeIngredient.addRecipe(recipe);
                em.persist(recipeIngredient);
            }


            em.getTransaction().commit();
            return new RecipeDTO(recipe);
        } finally {
            em.close();
        }
    }

    public RecipeDTO getRecipeById(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Recipe recipe = em.find(Recipe.class, id);
            if (recipe == null) {
                throw new NotFoundException("Recipe not found");
            }
            return new RecipeDTO(recipe);
        } finally {
            em.close();
        }
    }

    public List<RecipeDTO> getAllRecipesByUsername(String username) throws NotFoundException {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<RecipeDTO> query = em.createQuery("SELECT new dtos.RecipeDTO(r) FROM Recipe r WHERE r.user.userName = :username", RecipeDTO.class);
            query.setParameter("username", username);
            List<RecipeDTO> recipes = query.getResultList();
            if (recipes.isEmpty()) {
                throw new NotFoundException("No recipes found");
            }
            return recipes;
        } finally {
            em.close();
        }
    }
}
