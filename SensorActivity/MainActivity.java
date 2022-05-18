package com.example.sensoractivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView parlaklik, hareket;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mProximity;
    private double accelerationCurrent;
    private double accelerationPrevious;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        float proximity;
        @Override
        public void onSensorChanged(SensorEvent event) {
            Intent intent = new Intent();
            intent.setAction("com.example.sensoractivity2");
            if(event.sensor.getType() == Sensor.TYPE_PROXIMITY){
                proximity = event.values[0];
            }
            else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                accelerationPrevious = accelerationCurrent;
                accelerationCurrent = Math.sqrt((x*x + y*y + z*z));
            }

            if(proximity < mProximity.getMaximumRange() && accelerationCurrent-accelerationPrevious!=0){
                // The mobile phone is moving and it is in the pocket
                intent.putExtra("Status", 0);
                //parlaklik.setText("Telefon cepte ve hareketli");
                Toast.makeText(getApplicationContext(),"Telefon cepte ve hareketli",Toast.LENGTH_SHORT).show();
            }
            else if(accelerationCurrent-accelerationPrevious == 0){
                // The mobile phone is not moving and it is on the deck
                intent.putExtra("Status", 1);
                //hareket.setText("Telefon masada ve hareketsiz");
                Toast.makeText(getApplicationContext(),"Telefon masada ve hareketsiz",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parlaklik = (TextView) findViewById(R.id.parlaklik);
        hareket = (TextView) findViewById(R.id.hareket);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(sensorEventListener, mProximity, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();
    }

}