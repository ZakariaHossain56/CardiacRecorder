package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecordDetailsActivity extends AppCompatActivity {
    String REC,USER;
   private EditText delsys,deldias,delrate,deldate,deltime,delcomment;
    private Button delererec,editrec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_details);

        REC=getIntent().getStringExtra("RECORDDD_UID");
        USER=getIntent().getStringExtra("USERRRR_UID");
        delsys=findViewById(R.id.delsys);
        deldias=findViewById(R.id.deldias);
        delrate=findViewById(R.id.delrate);
        deldate=findViewById(R.id.deldate);
        deltime=findViewById(R.id.deltime);
        delcomment=findViewById(R.id.delcomment);
        delererec=findViewById(R.id.deleterec);
        editrec=findViewById(R.id.editrec);


     
    }




}