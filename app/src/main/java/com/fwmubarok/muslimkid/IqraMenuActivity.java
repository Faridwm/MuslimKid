package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class IqraMenuActivity extends AppCompatActivity {

    //Context
    private Context context = this;

    //Button
    private ImageButton btn_iqra_1, btn_iqra_2, btn_iqra_3, btn_iqra_4, btn_iqra_5, btn_iqra_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iqra_menu);

        btn_iqra_1 =  findViewById(R.id.btn_iqra_1);
        btn_iqra_2 =  findViewById(R.id.btn_iqra_2);
        btn_iqra_3 =  findViewById(R.id.btn_iqra_3);
        btn_iqra_4 =  findViewById(R.id.btn_iqra_4);
        btn_iqra_5 =  findViewById(R.id.btn_iqra_5);
        btn_iqra_6 =  findViewById(R.id.btn_iqra_6);

        btn_iqra_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadIqraActivity.class);
                intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 1);
                startActivity(intent);
            }
        });

        btn_iqra_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadIqraActivity.class);
                intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 2);
                startActivity(intent);
            }
        });

        btn_iqra_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadIqraActivity.class);
                intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 3);
                startActivity(intent);
            }
        });

        btn_iqra_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadIqraActivity.class);
                intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 4);
                startActivity(intent);
            }
        });

        btn_iqra_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadIqraActivity.class);
                intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 5);
                startActivity(intent);
            }
        });

        btn_iqra_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadIqraActivity.class);
                intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 6);
                startActivity(intent);
            }
        });
    }
}