package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *
 */
public class RecordDetailsActivity extends AppCompatActivity {
    String REC,USER;
    private TextView delsys,deldias,delrate,deldate,deltime,delcomment,stsys,stdias;
    private Button delererec,editrec;


    /**
     *
     * @param savedInstanceState
     */
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
        stsys=findViewById(R.id.stsys);
        stdias=findViewById(R.id.stdias);

        delsys.setText(getIntent().getStringExtra("sys")+" mm Hg");
        deldias.setText(getIntent().getStringExtra("dias")+" mm Hg");
        delrate.setText(getIntent().getStringExtra("rate" )+ " bpm");
        deldate.setText(getIntent().getStringExtra("date"));
        deltime.setText(getIntent().getStringExtra("time"));
        delcomment.setText(getIntent().getStringExtra("comment"));

        int sys=Integer.parseInt(getIntent().getStringExtra("sys"));
        int dias=Integer.parseInt(getIntent().getStringExtra("dias"));
        if(sys>90 && sys<=140) {
            stsys.setText("Normal");
            stsys.setBackgroundColor(Color.GREEN);
        }
        else if(sys<90) {
            stsys.setText("Low");
            stsys.setBackgroundColor(Color.BLUE);
            stsys.setTextColor(Color.WHITE);
        }
        else if(sys>140) {
            stsys.setText("High");
            stsys.setBackgroundColor(Color.RED);
            stsys.setTextColor(Color.WHITE);
        }
        if(dias>=60 && dias<=90){
            stdias.setText("Normal");
            stdias.setBackgroundColor(Color.GREEN);
        }
        else if(dias<60){
            stdias.setText("Low");
            stdias.setBackgroundColor(Color.BLUE);
            stdias.setTextColor(Color.WHITE);
        }
        else if(dias>90){
            stdias.setText("High");
            stdias.setBackgroundColor(Color.RED);
            stdias.setTextColor(Color.WHITE);
        }

        editrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordDetailsActivity.this,AddItem.class);
                intent.putExtra("sys",getIntent().getStringExtra("sys"));
                intent.putExtra("dias",getIntent().getStringExtra("dias"));
                intent.putExtra("rate",getIntent().getStringExtra("rate"));
                intent.putExtra("date",getIntent().getStringExtra("date"));
                intent.putExtra("time",getIntent().getStringExtra("time"));
                intent.putExtra("comment",getIntent().getStringExtra("comment"));
                intent.putExtra("RECORDDD_UID",getIntent().getStringExtra("RECORDDD_UID"));
                startActivity(intent);
            }
        });


        delererec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("data")
                        .child(mAuth.getUid()).child(getIntent().getStringExtra("RECORDDD_UID"));
                databaseReference.removeValue();
                finish();



            }
        });


     
    }




}