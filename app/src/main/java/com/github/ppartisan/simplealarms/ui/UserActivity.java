package com.github.ppartisan.simplealarms.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.ppartisan.simplealarms.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class UserActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    TextView tvUsername, tvEmail,tvFullName;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mAuth = FirebaseAuth.getInstance();


        tvFullName = (TextView) findViewById(R.id.tv_fullname);
        tvUsername = (TextView)findViewById(R.id.tv_username);
        tvEmail = (TextView)findViewById(R.id.tv_email);
        btnLog = (Button)findViewById(R.id.btn_logout);

        FirebaseUser current_user = mAuth.getCurrentUser();
         final String uid = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        Log.d("UID",uid);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,String> map = (Map<String, String>) dataSnapshot.getValue();
                String username = map.get("username");
                String email = map.get("email");
                String fullname = map.get("fullname");

               tvUsername.setText("Username: "+username);
               tvEmail.setText("Email: " +email);
               tvFullName.setText("Fullname: "+fullname);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
