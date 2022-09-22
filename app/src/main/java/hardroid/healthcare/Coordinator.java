package hardroid.healthcare;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.markers.KMutableList;


import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.markers.KMutableList;


public class Coordinator extends AppCompatActivity
        implements SensorEventListener {

    /*
    We have to register the listener in the onResume() method
    and unregister it in the onPause() method. It is a good practice!

    If we want to use as a background app, first the permission is required,
    second unregistering is not necessary.
     */



    private SensorManager mSensorManager;
    private  Sensor mSensor;
    private boolean isSensorPresented;
    private TextView TestSensor;
    private Button TestButton;
    private double mStepsSinceReboot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator);
        TestSensor = (TextView) findViewById(R.id.test_view);
        TestButton = findViewById(R.id.btn_test);

        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        if(mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)!=null){
            mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresented = true;
        }
        else{
            isSensorPresented = false;
        }

        /*
        1) getSystemService -- Return the handle to a system-level service by name.

        2) mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) -- returns the list (information) about the step counter sensor
        */
    }


    @Override
    public void onResume(){
        super.onResume();
        if(isSensorPresented){
            mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(isSensorPresented){
            mSensorManager.unregisterListener((SensorEventListener) this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        mStepsSinceReboot = event.values[0];
        TestSensor.setText("Steps since reboot: \n"+mStepsSinceReboot);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}



