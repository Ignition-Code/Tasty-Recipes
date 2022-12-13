package com.ms.tastyrecipes.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_detail", foreignKeys = {
        @ForeignKey(
                entity = Recipe.class,
                parentColumns = "recipe_id",
                childColumns = "recipe_id",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = Recipe.class,
                parentColumns = "ingredient_id",
                childColumns = "ingrediet_id",
                onDelete = ForeignKey.CASCADE
        )
})
public class RecipeDetail {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "recipe_detail_id")
    public Long recipeDetailID;
    @ColumnInfo(name = "recipe_detail_number")
    public String recipeDetailNumber;
    @ColumnInfo(name = "recipe_id", index = true)
    public Long recipeID;
    @ColumnInfo(name = "ingredient_id", index = true)
    public Long ingredientID;
    @ColumnInfo(name = "ingredient_name")
    public String recipeDetailIngredientName;

    public RecipeDetail(Long recipeDetailID, String recipeDetailNumber, Long recipeID, Long ingredientID) {
        this.recipeDetailID = recipeDetailID;
        this.recipeDetailNumber = recipeDetailNumber;
        this.recipeID = recipeID;
        this.ingredientID = ingredientID;
    }

    public Long getRecipeDetailID() {
        return recipeDetailID;
    }

    public void setRecipeDetailID(Long recipeDetailID) {
        this.recipeDetailID = recipeDetailID;
    }

    public String getRecipeDetailNumber() {
        return recipeDetailNumber;
    }

    public void setRecipeDetailNumber(String recipeDetailNumber) {
        this.recipeDetailNumber = recipeDetailNumber;
    }

    public Long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Long recipeID) {
        this.recipeID = recipeID;
    }

    public Long getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Long ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getRecipeDetailIngredientName() {
        return recipeDetailIngredientName;
    }

    public void setRecipeDetailIngredientName(String recipeDetailIngredientName) {
        this.recipeDetailIngredientName = recipeDetailIngredientName;
    }
}
