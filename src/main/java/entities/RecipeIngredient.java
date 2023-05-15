package entities;

import javax.persistence.*;

@Table(name = "recipe_ingredient")
@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private String amount;

    @Column(name = "unit")
    private String unit;


    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;


    public RecipeIngredient() {
    }

    public RecipeIngredient(String amount, String unit, String name) {
        this.amount = amount;
        this.unit = unit;
        this.name = name;

    }

    public RecipeIngredient(String amount, String unit, String name, Recipe recipe, Ingredient ingredient) {
        this.amount = amount;
        this.unit = unit;
        this.name = name;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void addIngredient(Ingredient i) {// this is a helper method to add an ingredient to a recipeIngredient
        this.ingredient = i;
        i.getRecipeIngredients().add(this);
    }

    public void addRecipe(Recipe recipe) {
        this.recipe = recipe;
        if (!recipe.getRecipeIngredients().contains(this)) {
            recipe.getRecipeIngredients().add(this);
        }
    }

}