package com.ms.tastyrecipes.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Entity(tableName = "ingredient")
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id")
    public Long ingredientID;
    @ColumnInfo(name = "ingredient_name")
    public String ingredientName;

    public Ingredient(Long ingredientID, String ingredientName) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("ingredient_id", this.ingredientID);
        object.put("ingredient_name", this.ingredientName);
        return object;
    }

    public Long getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Long ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @NonNull
    @Override
    public String toString() {
        return ingredientName;
    }
}
