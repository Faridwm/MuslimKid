package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        Intent intent = new Intent(context, ReadIqraActivity.class);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_iqra_1) {
                    intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 1);
                    startActivity(intent);
                } else if (v == btn_iqra_2) {
                    intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 2);
                    startActivity(intent);
                } else if (v == btn_iqra_3) {
                    intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 3);
                    startActivity(intent);
                } else if (v == btn_iqra_4) {
                    intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 4);
                    startActivity(intent);
                } else if (v == btn_iqra_5) {
                    intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 5);
                    startActivity(intent);
                } else if (v == btn_iqra_6) {
                    intent.putExtra(ReadIqraActivity.EXTRA_IQRA_NUMBER, 6);
                    startActivity(intent);
                }
            }
        };

        btn_iqra_1.setOnClickListener(clickListener);
        btn_iqra_2.setOnClickListener(clickListener);
        btn_iqra_3.setOnClickListener(clickListener);
        btn_iqra_4.setOnClickListener(clickListener);
        btn_iqra_5.setOnClickListener(clickListener);
        btn_iqra_6.setOnClickListener(clickListener);
    }
}