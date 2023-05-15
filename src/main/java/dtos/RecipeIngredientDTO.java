package dtos;
import entities.RecipeIngredient;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientDTO {
    private Long id;
    private String name;
    private String amount;
    private String unit;
    private Long ingredientId;

    private IngredientDTO ingredientDTO; // not in use

    public RecipeIngredientDTO(RecipeIngredient re) {
        if (re.getId() != null)
            this.id = re.getId();
        this.name = re.getName();
        this.amount = re.getAmount();
        this.unit = re.getUnit();
        //this.ingredientDTO = new IngredientDTO(re.getIngredient());
    }

    public RecipeIngredientDTO( String name, String amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public RecipeIngredientDTO(String name, String amount, String unit, IngredientDTO ingredientDTO) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.ingredientDTO = ingredientDTO;
    }

    public Long getIngredientId() { return ingredientId;
    }
    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public IngredientDTO getIngredientDTO() {
        return ingredientDTO;
    }
    public void setIngredientDTO(IngredientDTO ingredientDTO) {
        this.ingredientDTO = ingredientDTO;
    }

    public static List<RecipeIngredientDTO> getDtos(List<RecipeIngredient> recipeIngredients) {
        List<RecipeIngredientDTO> recipeDTOS = new ArrayList();
        recipeIngredients.forEach(re -> recipeDTOS.add(new RecipeIngredientDTO(re)));
        return recipeDTOS;
    }
}