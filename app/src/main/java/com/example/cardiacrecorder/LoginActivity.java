package com.example.cardiacrecorder;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;





public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    private EditText emailin, passwordin;
    private TextView textViewin;
    private Button buttonin;

    /**
     * Called when the activity is starting. Sets up the UI and initializes Firebase authentication.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.setTitle("Login");


        mAuth = FirebaseAuth.getInstance();
        emailin = (EditText) findViewById(R.id.emailin);
        passwordin = (EditText) findViewById(R.id.passwordin);
        textViewin = (TextView) findViewById(R.id.textViewin);
        buttonin = (Button) findViewById(R.id.buttonin);


        buttonin.setOnClickListener(this);
        textViewin.setOnClickListener(this);
    }

    /**
     * Handles the click events for views in the activity.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonin) {
            userlogin();

        } else if (v.getId() == R.id.textViewin) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        }


    }


    /**
     *
     * Called when the activity is becoming visible to the user. Checks if the user is already authenticated
     * and redirects to the main activity if so.
     */

    @Override
    public void onStart() {

        super.onStart();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null)
        {

           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
           startActivity(intent);
           finish();
        }
        else
        {

        }
    }


    /**
     * Performs the login process by validating the user input and attempting to sign in with Firebase authentication.
     */

    private void userlogin() {


        String email=emailin.getText().toString().trim();
        String pass=passwordin.getText().toString().trim();

        //validity of email
        if(email.isEmpty())
        {
            emailin.setError("Enter email address");
            Toast.makeText(LoginActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailin.setError("Enter valid email address");
            Toast.makeText(LoginActivity.this,"Invalid Email",Toast.LENGTH_SHORT).show();
            emailin.requestFocus();
            return;
        }


        if(pass.isEmpty())
        {
            passwordin.setError("Enter a password");
            Toast.makeText(LoginActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
        }
        if(pass.length()<6)
        {
            passwordin.setError("Password must be at least 6 characters long");
            Toast.makeText(LoginActivity.this,"Too short Password",Toast.LENGTH_SHORT).show();
            passwordin.requestFocus();
            return;
        }
        if(pass.length()>32)
        {
            passwordin.setError("Password must shorter than 32 characters");
            Toast.makeText(LoginActivity.this,"Too long Password",Toast.LENGTH_SHORT).show();
            passwordin.requestFocus();
            return;
        }
        //progressbarin.setVisibility(View.VISIBLE);

        /**
         *
         */
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //progressbarin.setVisibility(View.GONE);

                if(task.isSuccessful())
                {


                    finish();//page wont be seen while returning
                    Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

               }
               else
                {
                   Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }

           }
        });

        /*mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //progressbarin.setVisibility(View.GONE);

                if(task.isSuccessful())
                {
                    FirebaseUser u=FirebaseAuth.getInstance().getCurrentUser();
                    String S=u.getUid();

                     DatabaseReference reff=FirebaseDatabase.getInstance().getReference().child("UserInfo").child(S);
                     reff.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(@NonNull DataSnapshot snapshot) {
                             UserData userData=new UserData();
                            // userData.AccountType=snapshot.child("accountType").getValue().toString();
                             userData.setAccountType(snapshot.child("accountType").getValue().toString());
                             String c=userData.getAccountType();
                             //Toast.makeText(MainActivity.this, c, Toast.LENGTH_SHORT).show();
                             if(c.equalsIgnoreCase("GeneralUser"))

                             {
                                 FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
                                 DatabaseReference databaseReference;
                                 String Cur_date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());


                                 if(user != null) {
                                     String uid = user.getUid();
                                     databaseReference = FirebaseDatabase.getInstance().getReference().child("Data")
                                             .child(uid).child(Cur_date);

                                     databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(@NonNull DataSnapshot snapshot) {

                                             if (!snapshot.exists()) {
                                                 sharedPreferences.edit().putInt(Cur_date, 0).apply();
                                             }
                                             else {
                                                 Data dt = snapshot.getValue(Data.class);
                                                 int stp = 0;
                                                 if(dt != null){
                                                     stp = dt.getSteps();
                                                 }
                                                 sharedPreferences.edit().putInt(Cur_date,stp).apply();
                                             }

                                             finish();//page wont be seen while returning
                                             Intent intent= new Intent(MainActivity.this,IndexActivity.class);
                                             intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                             startActivity(intent);

                                         }

                                         @Override
                                         public void onCancelled(@NonNull DatabaseError error) {

                                         }
                                     });
                                 }





                             }

                             else
                             {
                                 finish();//page wont be seen while returning
                                 Intent intent= new Intent(MainActivity.this,ProviderActivity.class);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 startActivity(intent);

                             }



                         }

                         @Override
                         public void onCancelled(@NonNull DatabaseError error) {

                         }
                     });




//                    FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
//                    DatabaseReference databaseReference;
//                    String Cur_date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//
//
//                    if(user != null) {
//                        String uid = user.getUid();
//                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Data")
//                                .child(uid).child(Cur_date);
//
//                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                                if (!snapshot.exists()) {
//                                    sharedPreferences.edit().putInt(Cur_date, 0).apply();
//                                }
//                                else {
//                                    Data dt = snapshot.getValue(Data.class);
//                                    int stp = 0;
//                                    if(dt != null){
//                                        stp = dt.getSteps();
//                                    }
//                                    sharedPreferences.edit().putInt(Cur_date,stp).apply();
//                                }
//
//                                finish();//page wont be seen while returning
//                                Intent intent= new Intent(MainActivity.this,IndexActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login Unsuccessful",Toast.LENGTH_SHORT).show();
                }

            }
        });*/





    }


}