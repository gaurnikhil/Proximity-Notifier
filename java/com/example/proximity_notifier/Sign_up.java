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

public class Sign_up extends AppCompatActivity {
    TextView details;
    EditText pwd, em;
    Button SignUp;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        details=(TextView)findViewById(R.id.details);
        pwd=(EditText)findViewById(R.id.pwd);
        em=(EditText)findViewById(R.id.em);
        SignUp=(Button)findViewById(R.id.SignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=em.getText().toString();
                String s2=pwd.getText().toString();
                if (s1.isEmpty()){
                    em.setError("Please Enter the email address");
                    em.requestFocus();
                }
                else if (s2.isEmpty()){
                    pwd.setError("Please Enter the Password");
                    pwd.requestFocus();
                }
                else if (s1.isEmpty() && s2.isEmpty()){
                    Toast.makeText(Sign_up.this, "Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if (!(s1.isEmpty() && s2.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(s1,s2).addOnCompleteListener(Sign_up.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Sign_up.this, "SignUp Unsuccessful, please Try Again",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                startActivity(new Intent(Sign_up.this,MainActivity.class));

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Sign_up.this,"Error Occured!",Toast.LENGTH_SHORT).show();
                }


            }

        });


    }
}
