package com.r.myloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText signUpEmailEditText, signUpPasswordEditText;
    Button signUpButton;
    TextView signInText;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign up Activity");

        mAuth = FirebaseAuth.getInstance();

        signUpEmailEditText=findViewById(R.id.signUpEmailEditTextId);
        signUpPasswordEditText=findViewById(R.id.signUpPasswordEditTextId);
        signUpButton=findViewById(R.id.signUpButtonId);
        signInText=findViewById(R.id.signInTextId);
        progressBar=findViewById(R.id.progressBarId);

        signUpButton.setOnClickListener(this);
        signInText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signUpButtonId:
                UserRegister();
                break;
            case R.id.signInTextId:
                Intent intent=new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void UserRegister() {
        String email=signUpEmailEditText.getText().toString().trim();
        String password=signUpPasswordEditText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            signUpEmailEditText.setError("Enter an email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signUpEmailEditText.setError("Enter a valid email address");
            signUpEmailEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            signUpPasswordEditText.setError("Enter a password");
            signUpPasswordEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if (task.isSuccessful()) {

                    Toast.makeText(SignUpActivity.this,"register is successful",Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(SignUpActivity.this,"register is not successful",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}