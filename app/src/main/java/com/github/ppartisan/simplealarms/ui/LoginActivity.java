package com.github.ppartisan.simplealarms.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.ppartisan.simplealarms.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUser;

    private EditText mLogEmail, mLogPassword;
    private Button mBtnLog,mBtnToReg;

    private ProgressDialog mRegProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mBtnToReg = (Button)findViewById(R.id.goto_reg_button);

        mBtnToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });


        //progress dialogue
        mRegProgress = new ProgressDialog(this);

        //fields

        mLogEmail = (EditText) findViewById(R.id.login_email);

        mLogPassword = (EditText) findViewById(R.id.login_password);

        mBtnLog = (Button) findViewById(R.id.login_button);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        mBtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mLogEmail.getText().toString();
                String password = mLogPassword.getText().toString();

                if (!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)){

                    //if not empty then start registration
                    mRegProgress.setTitle("Logging In...");
                    mRegProgress.setMessage("Please wait...");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();

                    login_user(email,password);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Empty Fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login_user(String email, String password) {

    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(i);
            finish();

        }
    });

    }
}
