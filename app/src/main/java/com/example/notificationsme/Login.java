package com.example.notificationsme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emaill, pass_log;
    private Button loginbtn;
    private TextView noac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        emaill = findViewById(R.id.emaill);
        pass_log = findViewById(R.id.pass_log);
        loginbtn = findViewById(R.id.loginbtn);
        noac = findViewById(R.id.noac);

        loginbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String ema = emaill.getText().toString().trim();
                String psd = pass_log.getText().toString().trim();

                if (!ema.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(ema).matches()) {
                    if (!psd.isEmpty()){

                        auth.signInWithEmailAndPassword(ema,psd)

                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        Toast.makeText(Login.this, "Login Successful, Welcome!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this, MainScreen.class));
                                        finish();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this, "Failed Login, try again", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else{
                        pass_log.setError("Password Field Cannot Be Blank");
                    }
                }
                else if(ema.isEmpty()){
                    emaill.setError("Email Field Cannot Be Empty");
                }
                else{
                    emaill.setError("Enter Valid Email Address");
                }
            }
        });

        noac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }
}