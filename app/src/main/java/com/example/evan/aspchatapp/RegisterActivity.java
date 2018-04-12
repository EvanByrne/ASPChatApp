package com.example.evan.aspchatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText regUsername, regEmail, regPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        Toolbar mToolbar = findViewById(R.id.reg_toolbar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Sign Up");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //getSupportActionBar().setTitle("Sign Up");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        regUsername = findViewById(R.id.txtInpRegName);
        regEmail = findViewById(R.id.txtInpRegEmail);
        regPassword = findViewById(R.id.txtInpRegPassword);

        Button registerAcc = findViewById(R.id.btnRegister);

        registerAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = regUsername.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();

                RegisterAccount(name, email, password);
            }
        });
    }

    //Validation needs to be implemented further for strong passwords , valid emails and limit input length
    private void RegisterAccount(String name, String email, String password) {
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(RegisterActivity.this, "Please type your name",
                    Toast.LENGTH_LONG).show();

        }if(TextUtils.isEmpty(email))
        {
            Toast.makeText(RegisterActivity.this, "Please type your email",
                    Toast.LENGTH_LONG).show();

        }if(TextUtils.isEmpty(password))
        {
            Toast.makeText(RegisterActivity.this, "Please type a valid password ",
                    Toast.LENGTH_LONG).show();

        }else
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error Creating Account", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
