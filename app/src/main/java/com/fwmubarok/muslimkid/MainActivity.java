package com.fwmubarok.muslimkid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private Context context = this;

    //Button
    private ImageButton btn_iqra;
    private ImageButton btn_doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);

        btn_iqra = findViewById(R.id.btn_act_iqra);
        btn_doa = findViewById(R.id.btn_act_doa);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_iqra) {
                    Intent intent = new Intent(context, IqraMenuActivity.class);
                    startActivity(intent);
                } else if (v == btn_doa) {
                    Intent intent =  new Intent(context, DoaMenuActivity.class);
                    startActivity(intent);
                }
            }
        };

        btn_iqra.setOnClickListener(clickListener);
        btn_doa.setOnClickListener(clickListener);
    }
}