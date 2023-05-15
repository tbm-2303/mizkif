package entities;
import javax.persistence.*;
import java.util.List;

@Table(name = "recipe")
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "instructions")
    private String instructions;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Review> reviews;


    public Recipe() {
    }

    public Recipe(String title, String description, String instructions, User user){
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.user = user;
    }

    public Recipe(String title, String description,String instructions, User user, List<RecipeIngredient> recipeIngredients) {
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.user = user;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(String title, String description,String instructions, User user, List<RecipeIngredient> recipeIngredients,List<Review> reviews) {
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.user = user;
        this.recipeIngredients = recipeIngredients;
        this.reviews = reviews;
    }

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public List<Review> getReviews() {
        return reviews;
    }
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
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
    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) { this.recipeIngredients = recipeIngredients; }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


}