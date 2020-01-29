package com.example.proximity_notifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.lang.String;




public class MainActivity extends AppCompatActivity {
    Button log;
    EditText email, pass;
    TextView link_signup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth=FirebaseAuth.getInstance();
        log = (Button) findViewById(R.id.log);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.pass);
        link_signup = (TextView) findViewById(R.id.link_signup);

        mAuthStateListner = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (mFirebaseUser!= null){
                Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,home.class);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
            }
            }
        };

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, Sign_up.class);
                startActivity(i1);

            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=email.getText().toString();
                String s2=pass.getText().toString();
                if (s1.isEmpty()){
                    email.setError("Please Enter the email address");
                    email.requestFocus();
                }
                else if (s2.isEmpty()){
                    pass.setError("Please Enter the Password");
                    pass.requestFocus();
                }
                else if (s1.isEmpty() && s2.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(s1.isEmpty() && s2.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful()){
                               Toast.makeText(MainActivity.this, "Login Unsuccessful, please Try Again",Toast.LENGTH_SHORT).show();

                           }
                           else{
                               startActivity(new Intent(MainActivity.this,home.class));

                           }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occured!",Toast.LENGTH_SHORT).show();
                }


            }
        });






    }
}
