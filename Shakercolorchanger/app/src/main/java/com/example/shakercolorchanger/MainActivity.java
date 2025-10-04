package com.example.shakercolorchanger;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.RelativeLayout;
import android.view.View;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometer;
    RelativeLayout layout;
    float lastX, lastY, lastZ;
    int SHAKE_THRESHOLD = 800;
    long lastTime = 0;
    Random randomColorGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main);
        randomColorGenerator = new Random();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        }
    }

    @Override
    protected  void onResume(){
        super.onResume();
        if(accelerometer != null){
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            long curTime = System.currentTimeMillis();

            if((curTime - lastTime) > 100){
                long diffTime = curTime - lastTime;
                lastTime = curTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                float speed = Math.abs(x + y + z - lastX - lastY - lastZ)/diffTime * 10000;

                if(speed > SHAKE_THRESHOLD){
                    changeBackgroundColor();
                }

                lastX = x;
                lastY = y;
                lastZ = z;
            }
        }
    }

    void changeBackgroundColor(){
        int color = Color.rgb(randomColorGenerator.nextInt(256), randomColorGenerator.nextInt(256), randomColorGenerator.nextInt(256));
        layout.setBackgroundColor(color);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}