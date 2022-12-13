package com.ms.tastyrecipes.database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;

import com.ms.tastyrecipes.entities.Ingredient;
import com.ms.tastyrecipes.entities.Recipe;
import com.ms.tastyrecipes.entities.RecipeDetail;
import com.ms.tastyrecipes.entities.User;

import java.util.List;

@Database(entities = {Ingredient.class, Recipe.class, RecipeDetail.class, User.class}, version = 1)
public abstract class Controller extends RoomDatabase {



    @Dao
    public interface IngredientDao {
        @Insert
        void insertIngredient(Ingredient ingredient);
        @Query("SELECT * FROM ingredient")
        List<Ingredient> getAllIngredient();
    }

    @Dao
    public interface RecipeDao {
        @Insert
        void insertRecipe(Recipe recipe);
        @Query("SELECT * FROM recipe")
        List<Recipe> getAllRecipe();
        @Query("SELECT MAX(recipe_id) FROM recipe")
        Long getLastRecipeID();
    }

    @Dao
    public interface RecipeDetailDao {
        @Insert
        void insertRecipeDetail();
        @Query("SELECT rd.recipe_detail_id, rd.recipe_detail_number, rd.recipe_id, rd.ingredient_id, i.ingredient_name FROM recipe_detail AS rd INNER JOIN ingredient AS i ON rd.ingredient_id = i.ingredient_id WHERE rd.recipe_id = :recipeID")
        List<RecipeDetail> getRecipeDetail(Long recipeID);
    }

    @Dao
    public interface UserDao {
        @Insert
        void insertUser(User user);
        @Query("SELECT * FROM user WHERE user_name = :username AND user_password = :password")
        User findUser(String username, String password);
    }
}
