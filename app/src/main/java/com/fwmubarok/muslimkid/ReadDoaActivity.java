package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.fwmubarok.muslimkid.Model.Doa;

public class ReadDoaActivity extends AppCompatActivity {

    private Doa doa;

    //Text View
    TextView tv_title, tv_arabic, tv_latin, tv_trans;

    //EXTRA
    public static final String EXTRA_DOA = "extra_doa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_doa);

        tv_title = findViewById(R.id.read_title);
        tv_arabic = findViewById(R.id.read_arabic);
        tv_latin = findViewById(R.id.read_latin);
        tv_trans = findViewById(R.id.read_translation);


        doa = getIntent().getParcelableExtra(EXTRA_DOA);
        tv_title.setText(doa.getTitle());
        tv_arabic.setText(doa.getInArabic());
        tv_latin.setText("\"" + doa.getInLatin() + "\"");
        tv_trans.setText(doa.getTranslation());
    }
}