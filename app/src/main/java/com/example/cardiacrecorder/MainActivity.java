package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView RecyclerViewRecord;
    private AdapterRecord adapterRecord;
    private ArrayList<single_record>reclist;
    private EditText searchbar;

    FloatingActionButton btnadd;


    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    /**
     * In the onCreate method, the layout for this activity is set using the setContentView method,
     * which inflates the XML layout file (R.layout.activity_main).
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();

//        if (user != null && user.isEmailVerified()) {
//            // User is signed in and email is verified
//            // Proceed with the desired action
//        } else {
//            // User is either not signed in or email is not verified
//            // Redirect to a screen to prompt the user to verify their email
//            Toast.makeText(this, "Email not Verified", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
//            startActivity(intent);
//        }


        btnadd=findViewById(R.id.btnadd);
        RecyclerViewRecord=findViewById(R.id.RecyclerViewRecord);
        searchbar=findViewById(R.id.searchbar);
        loadrec();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddItem.class);
                startActivity(intent);
            }
        });



        //shama--filter


        /**
         *Searchbar to search any record by date
         * A TextWatcher is created and attached to the searchbar ( an instance of EditText representing the search bar).
         * The TextWatcher has three overridden methods: beforeTextChanged, onTextChanged, and afterTextChanged.
         *
         */


        searchbar.addTextChangedListener(new TextWatcher() {
            /**
             * The beforeTextChanged method is called before the text in the searchbar is changed.
             *
             * @param s
             * @param start
             * @param count
             * @param after
             */
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /**
             * The onTextChanged method is called when the text in the searchbar is changed.
             *  the filter method of the adapterRecord is called, passing the new text s as the filter constraint.
             *  The adapterRecord has a custom implementation of the Filterable interface.
             *  which enables filtering of its data based on the provided constraint.
             *
             * Any exceptions that occur during the filtering process are caught and printed to the console (using e.printStackTrace()).
             * @param s
             * @param start
             * @param before
             * @param count
             */

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    adapterRecord.getFilter().filter(s);


                }catch(Exception e)
                {
                    e.printStackTrace();

                }

            }

            /**
             * The afterTextChanged method is called after the text in the searchbar has been changed.
             * @param s
             */

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     * Callback method to be invoked when a view is clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }


    //for sign out

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @return True for the menu to be displayed, false otherwise.
     */



    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        getMenuInflater().inflate(R.menu.logoutmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * This method is called whenever an item in the options menu is selected.
     *
     * @param item The menu item that was selected.
     * @return True if the item was handled, false otherwise.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.LogOutMenuId)
        {

            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }




    /**
     * Load the records from the Firebase Realtime Database and populate the RecyclerView.
     */



    private void loadrec() {
        reclist = new ArrayList<>();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("data").child(mAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                reclist.clear();

                    for(DataSnapshot dss : snapshot.getChildren())
                    {
                        single_record sr= dss.getValue(single_record.class);
                        reclist.add(sr);
                    }


                adapterRecord=new AdapterRecord(MainActivity.this,reclist);
                RecyclerViewRecord.setAdapter(adapterRecord);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}