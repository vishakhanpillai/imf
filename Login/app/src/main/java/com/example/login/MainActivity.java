package com.example.login;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button register, login;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.GoToLogin);

        db = openOrCreateDatabase("STUDENT.DB", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS STUDENT( username text, password text)");

        register.setOnClickListener( v ->{

            String uname = username.getText().toString();
            String pass = password.getText().toString();

            if(uname.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            try{
                db.execSQL("INSERT INTO STUDENT VALUES (?, ?)", new String[]{uname, pass});
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            login.setOnClickListener( view ->{
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            });
        });



    }
}