package com.ms.tastyrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ms.tastyrecipes.database.Controller;
import com.ms.tastyrecipes.entities.Ingredient;
import com.ms.tastyrecipes.entities.User;

import java.util.ArrayList;
import java.util.List;

public class IngredientActivity extends AppCompatActivity {

    ListView lvIngredient;
    List<Ingredient> ingredients;
    Controller controller;
    enum Action {
            SHOW,
            SAVE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        Button save = findViewById(R.id.btSaveIngredient);
        lvIngredient = findViewById(R.id.lvIngredientIngredient);
        controller = Room.databaseBuilder(getApplicationContext(), Controller.class, "recipes").build();
        save.setOnClickListener(v -> new Ingredients(Action.SAVE).execute());
        new Ingredients(Action.SHOW).execute();
    }

    void showIngredients() {
        List<String> names = new ArrayList<>();
        Controller.IngredientDao ingredientDao = controller.ingredientDao();
        ingredients = ingredientDao.getAllIngredient();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        runOnUiThread(() -> lvIngredient.setAdapter(adapter));

        for (Ingredient helper : ingredients) {
            names.add(helper.getIngredientName());
        }

        adapter.notifyDataSetChanged();
    }

    void save() {
        EditText etNameIngredient = findViewById(R.id.etNameIngredient);
        Controller.IngredientDao ingredientDao = controller.ingredientDao();
        ingredientDao.insertIngredient(new Ingredient(null, etNameIngredient.getText().toString()));
        new Ingredients(Action.SHOW).execute();
        etNameIngredient.setText("");
    }

    class Ingredients extends AsyncTask<Void, Void, Boolean> {

        Action action;

        public Ingredients(Action action) {
            this.action = action;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            if(this.action == Action.SHOW) {
                showIngredients();
            } else if (this.action == Action.SAVE){
                save();
            }

            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}