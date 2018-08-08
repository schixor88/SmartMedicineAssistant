package com.github.ppartisan.simplealarms.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.github.ppartisan.simplealarms.R;

public class DiseaseActivity extends AppCompatActivity {


    private Button btn_webmd,btn_common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Disease Online");

        btn_webmd = (Button) findViewById(R.id.btn_webmd);
        btn_common = (Button) findViewById(R.id.btn_common);

        btn_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webmd = new Intent(DiseaseActivity.this,CommonDiseaseActivity.class);
                startActivity(webmd);
            }
        });

        btn_webmd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent commom = new Intent(DiseaseActivity.this,WebmdActivity.class);
                startActivity(commom);
            }
        });
    }
}
