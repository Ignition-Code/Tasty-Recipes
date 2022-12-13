package com.ms.tastyrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ms.tastyrecipes.database.Controller;
import com.ms.tastyrecipes.entities.User;

public class LoginActivity extends AppCompatActivity {

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btLogin = findViewById(R.id.btLogin);
        Button btRegister = findViewById(R.id.btRegister);
        btLogin.setOnClickListener(v -> getLogin(etUsername.getText().toString(), etPassword.getText().toString()));
        btRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterUserActivity.class)));
        controller = Room.databaseBuilder(getApplicationContext(), Controller.class, "recipes").build();
    }

    void getLogin(String username, String password) {
        Controller.UserDao userDao = controller.userDao();
        User user = userDao.findUser(username, password);
        if (user != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}