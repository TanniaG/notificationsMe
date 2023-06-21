package com.example.notificationsme;

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
import com.google.firebase.auth.SignInMethodQueryResult;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signUp, pass;
    private Button signbtn;
    private TextView already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signUp = findViewById(R.id.signUp);
        pass = findViewById(R.id.pass);
        signbtn = findViewById(R.id.signbtn);
        already = findViewById(R.id.already);

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = signUp.getText().toString().trim();
                String psw = pass.getText().toString().trim();

                if(user.isEmpty()){
                    signUp.setError("Email Cannot be Empty");
                }

                if(psw.isEmpty()){
                    pass.setError("Password Cannot be Empty");
                }
                else{
                    auth.createUserWithEmailAndPassword(user, psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(SignUp.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Login.class));

                            }
                            else{
                                Toast.makeText(SignUp.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }

        });

        already.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });
    }
}