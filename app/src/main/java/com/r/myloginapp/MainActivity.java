package com.r.myloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText signInEmailEditText, signInPasswordEditText;
    Button signInButton;
    TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign in Activity");

        signInEmailEditText=findViewById(R.id.signInEmailEditTextId);
        signInPasswordEditText=findViewById(R.id.signInPasswordEditTextId);
        signInButton=findViewById(R.id.signInButtonId);
        signUpText=findViewById(R.id.signUpTextId);

        signInButton.setOnClickListener(this);
        signUpText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signInButtonId:

                break;
            case R.id.signUpTextId:
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }
}