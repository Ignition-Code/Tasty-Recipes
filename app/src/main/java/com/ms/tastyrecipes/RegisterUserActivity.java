package com.ms.tastyrecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.ms.tastyrecipes.database.Controller;
import com.ms.tastyrecipes.entities.User;

public class RegisterUserActivity extends AppCompatActivity {

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        EditText etMail = findViewById(R.id.etMail);
        EditText etNaturalName = findViewById(R.id.etNaturalName);
        EditText etUsernameRegister = findViewById(R.id.etUsernameRegister);
        EditText etPasswordRegister = findViewById(R.id.etPasswordRegister);
        Button btSaveRegister = findViewById(R.id.btSaveRegister);
        btSaveRegister.setOnClickListener(v -> save(etUsernameRegister.getText().toString(), etPasswordRegister.getText().toString(), etNaturalName.getText().toString(), etMail.getText().toString()));
        controller = Room.databaseBuilder(getApplicationContext(), Controller.class, "recipes").build();
    }

    void save(String username, String password, String naturalName, String mail) {
        Controller.UserDao userDao = controller.userDao();
        userDao.insertUser(new User(null, username, password, naturalName, mail));
        finish();
    }
}