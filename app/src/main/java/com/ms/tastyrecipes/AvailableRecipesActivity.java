package com.ms.tastyrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ms.tastyrecipes.database.Controller;
import com.ms.tastyrecipes.entities.Ingredient;
import com.ms.tastyrecipes.entities.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AvailableRecipesActivity extends AppCompatActivity {

    List<Ingredient> ingredients;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_recipes);
        Bundle bundle = getIntent().getExtras();
        getIngredients(bundle.getString("ingredient_list"));
        controller = Room.databaseBuilder(getApplicationContext(), Controller.class, "recipes").build();
        new Recipes().execute();
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

    void showRecipes() {
        Controller.RecipeDao recipeDao = controller.recipeDao();
        List<Recipe> recipes = recipeDao.getAllRecipe();
        ListView lvRecipe = findViewById(R.id.lvAvailableRecipes);
        List<String> names = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        runOnUiThread(() -> lvRecipe.setAdapter(adapter));


        for (Recipe helper : recipes) {
            names.add(helper.getRecipeName());
        }

        adapter.notifyDataSetChanged();
    }

    class Recipes extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            showRecipes();
            return true;
        }
    }
}