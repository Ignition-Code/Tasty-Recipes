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
import android.widget.ListView;
import android.widget.Spinner;

import com.ms.tastyrecipes.database.Controller;
import com.ms.tastyrecipes.entities.Ingredient;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvIngredient;
    Spinner spIngredient;
    Controller controller;
    List<Ingredient> mainIngredient;
    Ingredient test;
    List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btIngredientRegister = findViewById(R.id.btIngredientRegister);
        Button btRecipeRegister = findViewById(R.id.btRecipeRegister);
        Button btAddIngredient = findViewById(R.id.btAddIngredient);
        Button btShowRecipe = findViewById(R.id.btShowRecipe);
        lvIngredient = findViewById(R.id.lvIngredient);
        spIngredient = findViewById(R.id.spIngredient);
        controller = Room.databaseBuilder(getApplicationContext(), Controller.class, "recipes").build();
        mainIngredient = null;
        new Kitchen(true).execute();
        btIngredientRegister.setOnClickListener(v ->
        {
            startActivity(new Intent(this, IngredientActivity.class));
            finish();
        });
        spIngredient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                test = ingredients.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btAddIngredient.setOnClickListener(v -> {
            mainIngredient.add(test);
            new Kitchen(false).execute();
        });

        btRecipeRegister.setOnClickListener(v -> startActivity(new Intent(this, RecipeActivity.class)));
        btShowRecipe.setOnClickListener(v -> {
            JSONArray array = new JSONArray();
            for (Ingredient ingredient : mainIngredient) {
                try {
                    array.put(ingredient.toJson());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            Intent intent = new Intent(this, AvailableRecipesActivity.class);
            intent.putExtra("ingredient_list", array.toString());
            startActivity(intent);
        });
        mainIngredient = new ArrayList<>();
    }

    void addIngredients() {
        List<String> names = new ArrayList<>();
        Controller.IngredientDao ingredientDao = controller.ingredientDao();
        ingredients = ingredientDao.getAllIngredient();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        runOnUiThread(() -> spIngredient.setAdapter(adapter));


        for (Ingredient helper : ingredients) {
            names.add(helper.getIngredientName());
        }

        adapter.notifyDataSetChanged();
    }

    void showIngredient() {
        List<String> names = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        runOnUiThread(() -> lvIngredient.setAdapter(adapter));


        for (Ingredient helper : mainIngredient) {
            names.add(helper.getIngredientName());
        }

        adapter.notifyDataSetChanged();
    }

    class Kitchen extends AsyncTask<Void, Void, Boolean> {

        boolean option;

        public Kitchen(boolean option) {
            this.option = option;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if (option) {
                addIngredients();
            } else {
                showIngredient();
            }
            return true;
        }
    }
}