package com.example.leon.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText, yText, zText, xMaxText, yMaxText, zMaxText;
    private Sensor accelerometer;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create our Sensor Manager
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);


        // Accelerometer Sensor
        // TYPE_LINEAR_ACCELERATION um Gravity zu filtern
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        // Register sensor Listener
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        // Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

        xMaxText = (TextView)findViewById(R.id.xMaxText);
        yMaxText = (TextView)findViewById(R.id.yMaxText);
        zMaxText = (TextView)findViewById(R.id.zMaxText);
    }

    // Zusatz
    float now = SensorManager.GRAVITY_EARTH;
    float last = SensorManager.GRAVITY_EARTH;
    //float shake = (float) 0.00;


    @Override
    public void onSensorChanged(SensorEvent event) {

        xText.setText("X: " + event.values[0]);
        yText.setText("Y: " + event.values[1]);
        zText.setText("Z: " + event.values[2]);

        // Zusatz
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        last = now;
        now = (float) Math.sqrt(x*x + y*y + z*z);
        float grenzWert = now - last;
        //shake = shake * 0.9f + alpha;


        if (grenzWert > 12){
            Toast toast = Toast.makeText(this, "Schüttel mich nicht", Toast.LENGTH_LONG);
            toast.show();
            xMaxText.setText("X-MAX: " + event.values[0]);
            yMaxText.setText("Y-MAX: " + event.values[1]);
            zMaxText.setText("Z-MAX: " + event.values[2]);


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
