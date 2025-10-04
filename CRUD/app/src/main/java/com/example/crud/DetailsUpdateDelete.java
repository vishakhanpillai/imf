package com.example.crud;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailsUpdateDelete extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details_update_delete);

        TextView result = findViewById(R.id.textView);
        db = openOrCreateDatabase("STUDENT.DB", MODE_PRIVATE, null);

        Button refresh = findViewById(R.id.refresh);
        Button deleteBtn = findViewById(R.id.delete);
        EditText delelteName = findViewById(R.id.delete_username);

        EditText updateName = findViewById(R.id.updateName);
        EditText updateAge = findViewById(R.id.updateAge);
        EditText updateCourse = findViewById(R.id.updateCourse);
        Button updateButton = findViewById(R.id.updateButton);

        refresh.setOnClickListener( v ->{
            try{
                Cursor cur = db.rawQuery("SELECT * FROM STUDENTS", null);

                if(cur.getCount() == 0){
                    result.setText("NO records");
                }
                else{
                    StringBuilder builder = new StringBuilder();
                    while(cur.moveToNext()){
                        builder.append("\nName: ").append(cur.getString(0))
                                .append("\nAge: ").append(cur.getString(1))
                                .append("\nCourse: ").append(cur.getString(2))
                                .append("\n\n");
                    }
                    result.setText(builder);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        deleteBtn.setOnClickListener( v ->{
            String uname = delelteName.getText().toString().trim();

            try{
                db.execSQL("DELETE FROM STUDENTS WHERE name = ?", new String[]{uname});
                Toast.makeText(this, "Student Deleted", Toast.LENGTH_SHORT).show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        updateButton.setOnClickListener( v ->{
            String nameToUpdate = updateName.getText().toString().trim();
            String ageToUpdate = updateAge.getText().toString().trim();
            String courseToUpdate = updateCourse.getText().toString().trim();

            try{
                db.execSQL("UPDATE STUDENTS SET age = ?, course = ? where name = ?", new String[]{ageToUpdate, courseToUpdate, nameToUpdate});
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });



    }
}