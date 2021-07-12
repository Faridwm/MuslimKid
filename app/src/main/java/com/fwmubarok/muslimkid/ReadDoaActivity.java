package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.fwmubarok.muslimkid.Model.Doa;

public class ReadDoaActivity extends AppCompatActivity {

    private Context context = this;
    private Doa doa;
    private int position;

    //Text View
    private TextView tv_title, tv_arabic, tv_latin, tv_trans;

    //Card vIew
    private CardView cv_arabic, cv_artinya;

    //EXTRA
    public static final String EXTRA_DOA = "extra_doa";
    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_doa);

        tv_title = findViewById(R.id.read_title);
        tv_arabic = findViewById(R.id.read_arabic);
        tv_latin = findViewById(R.id.read_latin);
        tv_trans = findViewById(R.id.read_translation);

        cv_arabic = findViewById(R.id.cv_read_arabic);
        cv_artinya = findViewById(R.id.cv_artinya);

        doa = getIntent().getParcelableExtra(EXTRA_DOA);
        position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        if (position % 2 == 0) {
            tv_title.setTextColor(ContextCompat.getColor(context, R.color.primary_orange));
            tv_latin.setTextColor(ContextCompat.getColor(context, R.color.primary_orange));
            tv_trans.setTextColor(ContextCompat.getColor(context, R.color.primary_orange));
            cv_arabic.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_orange));
            cv_artinya.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_orange));
        }

        String latin = "\"" + doa.getInLatin() + "\"";
        tv_title.setText(doa.getTitle());
        tv_arabic.setText(doa.getInArabic());
        tv_latin.setText(latin);
        tv_trans.setText(doa.getTranslation());
    }
}