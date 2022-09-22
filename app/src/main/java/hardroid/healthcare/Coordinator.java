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


public class Coordinator extends AppCompatActivity {

    private SensorManager sManager;
    private TextView TestSensor;
    private Button TestButton;
    private   int foot  =0 ;
    private boolean running = false;
    private  Sensor sensor;
    private  int TotalSteps = 0;
    private  int InitialTotalSteps = 0;
    private SensorEventListener sListener;
    private boolean initialization = true;

    private boolean HasFitPermission(){
        return ActivityCompat.checkSelfPermission(this,Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_GRANTED;
    }

    private void RequestFitPermissions(){
        List <String> permissionsToRequest = new  <String> ArrayList();
        if(!HasFitPermission()){
            permissionsToRequest.add(Manifest.permission.ACTIVITY_RECOGNITION);
        }
        if(!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this,    permissionsToRequest.toArray(new String[0]), 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0 && grantResults.length>0){
            for(int i = 0 ; i < grantResults.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.d("PermissionRequest ", permissions[i] + " granted");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator);

        //Permissions
        RequestFitPermissions();


        TestSensor = (TextView) findViewById(R.id.test_view);
        TestButton = findViewById(R.id.btn_test);


        sManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //SensorManager lets you access the device's sensors
        //getSystemService -- receive system services. System Services like
        //Gods for various apps. Most functions of apps must be rely for System Services.

        sensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);//TYPE_STEP_COUNTER
        //This method is used to get the default sensor for a given type //1234

        if(sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) == null){
            Toast.makeText(this, "NO STEP SENSOR",Toast.LENGTH_SHORT);
        }
        else{
//            System.out.println("INDEX: "+ sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0));//.getClass().getName()
            System.out.println("You have the following step counter: "
                    +"\nname: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getName()
                    +"\nvendor: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getVendor()
                    +"\ntype: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getType()
                    +"\nversion: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getVersion()
                    +"\npower: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getPower());
            for(int i = 0 ; i<5;i++){
                Toast.makeText(this, "You have the following step counter: "
                                +"\nname: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getName()
                                +"\nvendor: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getVendor()
                                +"\ntype: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getType()
                                +"\nversion: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getVersion()
                                +"\npower: " + sManager.getSensorList(Sensor.TYPE_STEP_COUNTER).get(0).getPower()
                        ,Toast.LENGTH_LONG).show();
            }
        }




//        if(sensor == null ){
//            Toast.makeText(this,"No Step Sensor Detected!",Toast.LENGTH_SHORT).show() ;
//        }else{
//            sManager.registerListener(sListener,sensor,SensorManager.SENSOR_DELAY_UI);
//        }

        float[] abc = new float[3];

//        sManager.registerListener(sListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        sManager.registerListener(sListener,sensor,SensorManager.SENSOR_DELAY_UI);


        TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestSensor.setText(abc[0]+" "+ abc[1]+ " "+abc[2]+" ");
                TestSensor.setText(String.valueOf(foot));
            }
        });


    }


    @Override
    protected void onResume(){
        super.onResume();
        running = true;
        sensor = sManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if(sensor == null ){
            Toast.makeText(this,"No Step Sensor Detected!",Toast.LENGTH_SHORT).show() ;
        }else{
            sManager.registerListener(sListener,sensor,SensorManager.SENSOR_DELAY_UI);
        }


    }




}

