package com.ms.tastyrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ms.tastyrecipes.entities.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableRecipesActivity extends AppCompatActivity {

    List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_recipes);
        Bundle bundle = getIntent().getExtras();
        getIngredients(bundle.getString("ingredient_list"));
    }

    void getIngredients(String data) {
        ingredients = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(data);
            for (int i = 0; i < array.length(); i++){
                JSONObject object = array.getJSONObject(i);
                ingredients.add(new Ingredient(object.getLong("ingredient_id"), object.getString("ingredient_name")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}