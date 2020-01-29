package com.example.proximity_notifier;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proximity_notifier.models.placeInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class home<savePlaceInfo> extends AppCompatActivity  {
    private static final String TAG = "MapsActivity";
    private static final int ERROR_DIAGLOG_REQUEST = 9001;
    private DatabaseReference mDatabaseResrence;
    private EditText task_name, task_date, taskLatitude, taskLongitude, taskAddress;
    private FloatingActionButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseResrence = FirebaseDatabase.getInstance().getReference().child("place");
         task_name = (EditText)findViewById(R.id.task_name);
         task_date = (EditText)findViewById(R.id.task_date);
        taskLatitude = (EditText)findViewById(R.id.taskLatitude);
        taskLongitude = (EditText)findViewById(R.id.taskLongitude);
        taskAddress = (EditText)findViewById(R.id.taskAddress);
        if (isServiceOK()){
            init();
        }
        taskAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this, MapsActivity.class);
                startActivity(i);
            }
        });
        saveButton=(FloatingActionButton)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    private void init() {
        taskAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void saveInfo(){
        String name= task_name.getText().toString().trim();
        String latLng=taskLatitude.getText().toString().trim();
        String address=taskAddress.getText().toString().trim();
        placeInfo placeInfo = new placeInfo(name, latLng, address);
        mDatabaseResrence.child("place").setValue(placeInfo);
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();


    }





    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: checking google service version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(home.this);
        if (available == ConnectionResult.SUCCESS){
            //everyting is fine
            Log.d(TAG, "isServiceOK: Google play services is working");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServiceOK: an error occured but we can resolve it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(home.this, available, ERROR_DIAGLOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "you can't make map request", Toast.LENGTH_SHORT).show();

        }
        return false;
    }



}

