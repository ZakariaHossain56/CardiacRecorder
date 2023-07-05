package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


//
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;
//



public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private EditText emailup,passwordup,passwordupconfirm,Username,phone;
    private TextView textViewup;
    private Button buttonuser;
    EditText height,weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.setTitle("Sign Up");


        mAuth = FirebaseAuth.getInstance();


        Username=(EditText) findViewById(R.id.username);
        phone=findViewById(R.id.phone);
        emailup=(EditText) findViewById(R.id.emailup);
        passwordup=(EditText) findViewById(R.id.passwordup);
        passwordupconfirm=(EditText) findViewById(R.id.passwordupconfirm);
        textViewup=(TextView) findViewById(R.id.textViewup);
        buttonuser=findViewById(R.id.buttonuser);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);



        textViewup.setOnClickListener(this);
        buttonuser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.buttonuser)
        {
          UserRegister();

        }
        else if (v.getId()==R.id.textViewup){
            Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(intent);
        }

    }

    private void GeneralReg() {

        String email=emailup.getText().toString().trim();
        String pass=passwordup.getText().toString().trim();
        String Phone_no=phone.getText().toString().trim();
        String user=Username.getText().toString().trim();
        String h=height.getText().toString().trim();
        String w=weight.getText().toString().trim();
       // String username=Username.getText().toString().trim();


       mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {




                if (task.isSuccessful()) {
                    //push Data
                    UserInfo userData=new UserInfo();
//                    userData.AccountType="GeneralUser";
//                    userData.name=user;
//                    userData.phone=Phone_no;
//                    userData.email=email;
//                    userData.userid=mAuth.getUid();
                    //userData.setAccountType("GeneralUser");
                    userData.setUserid(mAuth.getUid());
                    userData.setName(user);
                    userData.setEmail(email);
                    userData.setPhone(Phone_no);
                    userData.setHeight(h);
                    userData.setWeight(w);

                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("UserInfo");
                    databaseReference.child(mAuth.getUid()).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(SignUpActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, "Data NOT Updated", Toast.LENGTH_SHORT).show();
                        }
                    });


                    //
//                    mAuth.createUserWithEmailAndPassword(email, pass)
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        sendVerificationEmail();
//                                    } else {
//                                        handleSignUpFailure(task.getException());
//                                    }
//                                }
//                            });

                    //




                    Toast.makeText(getApplicationContext(), "User Registration Successful", Toast.LENGTH_SHORT).show();
                    finish();//page wont be seen while returning
                    Intent intent= new Intent(SignUpActivity.this, MainActivity.class);
                   // Intent intent= new Intent(SignUpActivity.this, VerificationActivity.class);
                   // intent.putExtra("EMAIL_VERIFICATION", email);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

                else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {Toast.makeText(getApplicationContext(), "User is already registered", Toast.LENGTH_LONG).show();}
                    else{Toast.makeText(getApplicationContext(), "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();}
                }




            }
        });















    }

    private void handleSignUpFailure(Exception exception) {
        Toast.makeText(this, "Email Verification Failed", Toast.LENGTH_SHORT).show();
    }


    private void sendVerificationEmail() {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Verification email sent", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }



    private void UserRegister()
    {
        String user=Username.getText().toString().trim();

        if(user.isEmpty())
        {
            Username.setError("Enter  name");
            Toast.makeText(SignUpActivity.this,"name not entered",Toast.LENGTH_SHORT).show();
            return;
        }
        if(user.length()>30)
        {
            Username.setError("Name must be within 8 characters");
            Toast.makeText(SignUpActivity.this,"Name too long",Toast.LENGTH_SHORT).show();
            return;
        }
        String Phone_no=phone.getText().toString().trim();
        if(Phone_no.isEmpty())
        {
            Username.setError("Enter phone Number");
            Toast.makeText(SignUpActivity.this,"Phone Number not entered",Toast.LENGTH_SHORT).show();
            return;
        }


        String email=emailup.getText().toString().trim();

        //validity of email
        if(email.isEmpty())
        {
            emailup.setError("Enter email address");
            Toast.makeText(SignUpActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailup.setError("Enter valid email address");
            Toast.makeText(SignUpActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
            emailup.requestFocus();
            return;
        }
        String pass=passwordup.getText().toString().trim();

        if(pass.isEmpty())
        {
            passwordup.setError("Enter a password");
            Toast.makeText(SignUpActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.length()<6)
        {
            passwordup.setError("Password must be at least 6 characters long");
            Toast.makeText(SignUpActivity.this,"Too short Password",Toast.LENGTH_SHORT).show();
            passwordup.requestFocus();
            return;
        }
        if(pass.length()>32)
        {
            passwordup.setError("Password must be shorter than 32 characters");
            Toast.makeText(SignUpActivity.this,"Too long Password",Toast.LENGTH_SHORT).show();
            passwordup.requestFocus();
            return;
        }

        String passcmp=passwordupconfirm.getText().toString().trim();
        if(!passcmp.equals(pass))
        {
            passwordupconfirm.setError("Passwords do not match");
            Toast.makeText(SignUpActivity.this,"Passwords not matched",Toast.LENGTH_SHORT).show();
            passwordupconfirm.requestFocus();
            return;
        }

        String h,w;
        h=height.getText().toString();
        w=weight.getText().toString();
        if(h.isEmpty())
        {
            height.setError("Enter Height");
            Toast.makeText(SignUpActivity.this,"Enter Height",Toast.LENGTH_SHORT).show();
            return;
        }
        if(w.isEmpty())
        {
            weight.setError("Enter Weight");
            Toast.makeText(SignUpActivity.this,"Enter Weight",Toast.LENGTH_SHORT).show();
            return;
        }
        GeneralReg();





    }






}




