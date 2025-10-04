package com.example.login;

import android.database.Cursor;
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

public class Login extends AppCompatActivity {
    EditText username, password;
    Button login;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        db = openOrCreateDatabase("STUDENT.DB", MODE_PRIVATE, null);

        login.setOnClickListener( v ->{

            String uname = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            try {
                Cursor cur = db.rawQuery("SELECT * FROM STUDENT WHERE username = ? and password = ?", new String[]{uname, pass});

                if(cur.getCount() > 0){
                    Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }


        });

    }
}