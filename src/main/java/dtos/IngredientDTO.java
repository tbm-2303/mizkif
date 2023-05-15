package dtos;

import entities.Ingredient;

public class IngredientDTO {
    private Long id;
    private String name;

    public IngredientDTO(String name) {
        this.name = name;
    }

    public IngredientDTO(Ingredient i){
        if (i.getId() != null)
            this.id = i.getId();
        this.name = i.getName();
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
}