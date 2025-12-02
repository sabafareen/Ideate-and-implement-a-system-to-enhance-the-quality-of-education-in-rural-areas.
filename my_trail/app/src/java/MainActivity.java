package com.example.mytrail1;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    TextView signup;
    EditText email, pwd,name;
    Button login;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       
        mAuth = FirebaseAuth.getInstance();

       
        signup = findViewById(R.id.signupText);
        email = findViewById(R.id.emailBox);
        pwd = findViewById(R.id.pwdBox);
        name=findViewById(R.id.nameBox);
        login = findViewById(R.id.logBtn);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = email.getText().toString();
                String password = pwd.getText().toString();

                if (emailid.isEmpty() || !emailid.contains("@gmail.com")) {
                    Toast.makeText(MainActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty() || password.length() < 8) {
                    Toast.makeText(MainActivity.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);

              
                    mAuth.signInWithEmailAndPassword(emailid, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                                        String s_name = name.getText().toString();
                                        String s_email= email.getText().toString();
                                        intent.putExtra("emailid", s_email);
                                        intent.putExtra("name",s_name);
                                        startActivity(intent);
                                        finish(); 
                                        finish(); 
                                    } else {
                                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}
