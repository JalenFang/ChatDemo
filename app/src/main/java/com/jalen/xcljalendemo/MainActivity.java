package com.jalen.xcljalendemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /*
        findViewById(R.id.activity_main_full_features).setVisibility(View.GONE);
        findViewById(R.id.activity_main_partial_features).setVisibility(View.VISIBLE);
        */
        findViewById(R.id.activity_main_full_features).setVisibility(View.VISIBLE);
        findViewById(R.id.activity_main_partial_features).setVisibility(View.GONE);
    }
}
