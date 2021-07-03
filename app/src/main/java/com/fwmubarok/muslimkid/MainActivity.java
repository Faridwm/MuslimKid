package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Context context = this;

    //Button
    private Button btn_iqra, btn_doa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_iqra = findViewById(R.id.btn_act_iqra);
        btn_doa = findViewById(R.id.btn_act_doa);

        btn_iqra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IqraMenuActivity.class);
                startActivity(intent);
            }
        });

        btn_doa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, DoaMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}