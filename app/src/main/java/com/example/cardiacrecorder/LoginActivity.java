package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailin,passwordin;
    private TextView textViewin;
    private Button buttonin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        this.setTitle("Login");

        emailin=(EditText) findViewById(R.id.emailin);
        passwordin=(EditText) findViewById(R.id.passwordin);
        textViewin=(TextView) findViewById(R.id.textViewin);
        buttonin= (Button) findViewById(R.id.buttonin);


        buttonin.setOnClickListener(this);
        textViewin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonin)
        {

        }
        else if(v.getId()==R.id.textViewin){
            Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
        }


    }
}