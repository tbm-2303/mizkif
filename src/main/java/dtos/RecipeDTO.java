package dtos;

import entities.Ingredient;
import entities.Recipe;
import entities.RecipeIngredient;
import entities.Review;

import java.util.ArrayList;
import java.util.List;

public class RecipeDTO {
    private Long id;
    private String title;
    private String description;
    private String instructions;
    private String userName;
    private List<RecipeIngredientDTO> recipeIngredientDTOS = new ArrayList<>();
    private List<ReviewDTO> reviewDTOS = new ArrayList<>();


    public RecipeDTO(Recipe re) {
        if (re.getId() != null)
            this.id = re.getId();
        this.title = re.getTitle();
        this.description = re.getDescription();
        this.instructions = re.getInstructions();
        this.userName = re.getUser().getUserName();
        if (re.getRecipeIngredients() != null) {
            for (RecipeIngredient i : re.getRecipeIngredients()) {
                this.recipeIngredientDTOS.add(new RecipeIngredientDTO(i));
            }
        }
        if (re.getReviews() != null) {
            for (Review r : re.getReviews()) {
                this.reviewDTOS.add(new ReviewDTO(r));
            }
        }
    }

    public RecipeDTO(String title, String description, String userName) {
        this.title = title;
        this.description = description;
        this.userName = userName;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public List<RecipeIngredientDTO> getRecipeIngredientDTOS() {
        return recipeIngredientDTOS;
    }
    public void setRecipeIngredientDTOS(List<RecipeIngredientDTO> recipeIngredientDTOS) {
        this.recipeIngredientDTOS = recipeIngredientDTOS;
    }
    public List<ReviewDTO> getReviewDTOS() {
        return reviewDTOS;
    }
    public void setReviewDTOS(List<ReviewDTO> reviewDTOS) {
        this.reviewDTOS = reviewDTOS;
    }

    public static List<RecipeDTO> getDtos(List<Recipe> recipes) {
        List<RecipeDTO> recipeDTOS = new ArrayList();
        recipes.forEach(re -> recipeDTOS.add(new RecipeDTO(re)));
        return recipeDTOS;
    }
}