package com.ms.tastyrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.ms.tastyrecipes.database.Controller;
import com.ms.tastyrecipes.entities.Ingredient;
import com.ms.tastyrecipes.entities.Recipe;
import com.ms.tastyrecipes.entities.RecipeDetail;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    ListView lvIngredientRecipe;
    Spinner spIngredientRecipe;
    Controller controller;
    List<Ingredient> mainIngredient;
    Ingredient test;
    List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        lvIngredientRecipe = findViewById(R.id.lvIngredientRecipe);
        spIngredientRecipe = findViewById(R.id.spIngredientRecipe);
        Button btIngredientAddRecipe = findViewById(R.id.btIngredientAddRecipe);
        Button btSaveRecipe = findViewById(R.id.btSaveRecipe);

        controller = Room.databaseBuilder(getApplicationContext(), Controller.class, "recipes").build();
        mainIngredient = null;
        new RecipeRegister(3).execute();
        spIngredientRecipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                test = ingredients.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btIngredientAddRecipe.setOnClickListener(v -> {
            mainIngredient.add(test);
            new RecipeRegister(2).execute();
        });

        btSaveRecipe.setOnClickListener(v -> new RecipeRegister(1).execute());

        mainIngredient = new ArrayList<>();
    }

    void addIngredients() {
        List<String> names = new ArrayList<>();
        Controller.IngredientDao ingredientDao = controller.ingredientDao();
        ingredients = ingredientDao.getAllIngredient();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        runOnUiThread(() -> spIngredientRecipe.setAdapter(adapter));


        for (Ingredient helper : ingredients) {
            names.add(helper.getIngredientName());
        }

        adapter.notifyDataSetChanged();
    }

    void showIngredient() {
        List<String> names = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        runOnUiThread(() -> lvIngredientRecipe.setAdapter(adapter));


        for (Ingredient helper : mainIngredient) {
            names.add(helper.getIngredientName());
        }

        adapter.notifyDataSetChanged();
    }

    void save() {
        EditText etNameRecipe = findViewById(R.id.etNameRecipe);
        Controller.RecipeDao recipeDao = controller.recipeDao();
        Controller.RecipeDetailDao recipeDetailDao = controller.recipeDetailDao();
        recipeDao.insertRecipe(new Recipe(null, etNameRecipe.getText().toString()));
        Long id = recipeDao.getLastRecipeID();
        for (Ingredient ingredient : mainIngredient) {
            recipeDetailDao.insertRecipeDetail(new RecipeDetail(null, null, id, ingredient.ingredientID));
        }
        finish();
    }

    class RecipeRegister extends AsyncTask<Void, Void, Boolean> {

        int option;

        public RecipeRegister(int option) {
            this.option = option;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (option == 1) {
                save();
            } else if (option == 2){
                showIngredient();
            } else if (option == 3) {
                addIngredients();
            }
            return true;
        }
    }
}