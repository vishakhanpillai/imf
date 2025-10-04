package com.example.crud;

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

    EditText name, age, course;
    Button add;

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        course = findViewById(R.id.course);
        add = findViewById(R.id.add);

        Button goto_ = findViewById(R.id.details);

        db = openOrCreateDatabase("STUDENT.DB", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS STUDENTS (name TEXT, age TEXT, course TEXT)");

        add.setOnClickListener( v -> {
            String name_ = name.getText().toString().trim();
            String age_ = age.getText().toString().trim();
            String course_ = course.getText().toString().trim();

            try{
                db.execSQL("INSERT INTO STUDENTS VALUES(?, ?, ?)", new String[]{name_, age_, course_});
                Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        });

        goto_.setOnClickListener( e ->{
            Intent intent = new Intent(this, DetailsUpdateDelete.class);
            startActivity(intent);
        });
    }
}