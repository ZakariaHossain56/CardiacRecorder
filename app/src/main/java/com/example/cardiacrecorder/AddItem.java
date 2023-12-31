package com.example.cardiacrecorder;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity {

    PickDate datePicker;
    PickTime timePicker;
    TextView tvdate,tvtime;
    EditText tvsys,tvdias,tvrate,tvcomment;
    Button btnsave;

    /**
     * entry point of this activity
     * called when activity is created
     * sets content view to the layout defined
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        tvdate=findViewById(R.id.tvdate);
        tvtime=findViewById(R.id.tvtime);
        tvsys=findViewById(R.id.tvsys);
        tvdias=findViewById(R.id.tvdias);
        tvrate=findViewById(R.id.tvrate);
        tvcomment=findViewById(R.id.tvcomment);
        btnsave=findViewById(R.id.btnsave);

        if(getIntent().getStringExtra("sys")!=null){
            tvsys.setText(getIntent().getStringExtra("sys"));
            tvdias.setText(getIntent().getStringExtra("dias"));
            tvrate.setText(getIntent().getStringExtra("rate"));
            tvdate.setText(getIntent().getStringExtra("date"));
            tvtime.setText(getIntent().getStringExtra("time"));
            tvcomment.setText(getIntent().getStringExtra("comment"));
            btnsave.setText("Update");
        }

        datePicker = new PickDate(value ->{

                //Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
            tvdate.setText(value);
        });

        timePicker = new PickTime(time ->{

                //Toast.makeText(this, "Invalid time", Toast.LENGTH_SHORT).show();
            tvtime.setText(time);

        });

        tvdate.setOnClickListener(view -> datePicker.show(getSupportFragmentManager(),"Date picker"));
        tvtime.setOnClickListener(view -> timePicker.show(getSupportFragmentManager(),"Time picker"));


        /**
         * Open corresponding picked dialogues on click
         */
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date=tvdate.getText().toString().trim();
                String time=tvtime.getText().toString().trim();
                String systolic_pressure=tvsys.getText().toString().trim();
                String diastolic_pressure=tvdias.getText().toString().trim();
                String heart_rate=tvrate.getText().toString().trim();
                String comment=tvcomment.getText().toString().trim();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("data").child(mAuth.getUid());
                DatabaseReference newChildRef = databaseReference.push();
                String childId = newChildRef.getKey(); // Get the generated unique ID

                if(getIntent().getStringExtra("sys")!=null){
                    childId=getIntent().getStringExtra("RECORDDD_UID");
                    newChildRef=databaseReference.child(getIntent().getStringExtra("RECORDDD_UID"));
                }



                Map<String,Object> map=new HashMap<>();
                map.put("date",date);
                map.put("time",time);
                map.put("sys",systolic_pressure);
                map.put("dias",diastolic_pressure);
                map.put("rate",heart_rate);
                map.put("comment",comment);
                map.put("UserID",mAuth.getUid());
                map.put("RecordID",childId);

                //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("data").child(mAuth.getUid());
               // databaseReference.push().updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                newChildRef.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    /**
                     * It is attached to the update operation to handle the completion of the task.
                     * If the task is successful, a toast message is displayed indicating that the data is saved,
                     * and the user is redirected to the MainActivity.
                     * Otherwise, an error toast message is displayed with the exception message.
                     * @param task
                     */
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddItem.this, "Saved", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(AddItem.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(AddItem.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}