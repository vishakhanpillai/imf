package com.example.tts;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.input);
        Button btn = findViewById(R.id.btn);


        tts = new TextToSpeech(this, status -> {

            tts.setLanguage(Locale.ENGLISH);
        });

        btn.setOnClickListener( v ->{

            tts.speak(input.getText().toString(), TextToSpeech.QUEUE_FLUSH, null, null);
        });

    }
}