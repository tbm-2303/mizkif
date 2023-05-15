package facades;

import dtos.IngredientDTO;
import dtos.RecipeDTO;
import entities.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
public class IngredientFacade {

    private static EntityManagerFactory emf;
    private static IngredientFacade instance;

    private IngredientFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static IngredientFacade getIngredientFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new IngredientFacade();
        }
        return instance;
    }

    public List<IngredientDTO> getAllIngredients() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<IngredientDTO> query = em.createQuery("SELECT new dtos.IngredientDTO(i) FROM Ingredient i", IngredientDTO.class);
            List<IngredientDTO> ingredients = query.getResultList();
            return ingredients;
        } finally {
            em.close();
        }
    }

    public IngredientDTO createIngredient(IngredientDTO ingredientDTO) {
        EntityManager em = emf.createEntityManager();
        Ingredient ingredient = new Ingredient(ingredientDTO.getName());
        try{
            em.getTransaction().begin();
            em.persist(ingredient);
            em.getTransaction().commit();
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }


    public IngredientDTO getIngredientById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Ingredient ingredient = em.find(Ingredient.class, id);
            return new IngredientDTO(ingredient);
        } finally {
            em.close();
        }
    }
}
