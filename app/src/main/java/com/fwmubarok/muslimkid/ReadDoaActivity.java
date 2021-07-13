package com.fwmubarok.muslimkid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fwmubarok.muslimkid.Model.Doa;

import java.util.ArrayList;

public class ReadDoaActivity extends AppCompatActivity {
    private static final String TAG = "ReadDoaActivity";

    private Context context = this;
    private Doa doa;
    private ArrayList<Doa> doas = new ArrayList<>();
    private int position;

    ActionBar actionBar;

    //Text View
    private TextView tv_title, tv_arabic, tv_latin, tv_trans;

    //image Button
    private Button btn_next, btn_prev;

    //Card vIew
    private CardView cv_arabic, cv_artinya;

    //EXTRA
    public static final String EXTRA_DOA = "extra_doa";
    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_doa);

        actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_title = findViewById(R.id.read_title);
        tv_arabic = findViewById(R.id.read_arabic);
        tv_latin = findViewById(R.id.read_latin);
        tv_trans = findViewById(R.id.read_translation);

        cv_arabic = findViewById(R.id.cv_read_arabic);
        cv_artinya = findViewById(R.id.cv_artinya);

        btn_next = findViewById(R.id.btn_doa_next);
        btn_prev = findViewById(R.id.btn_doa_prev);

        doas = getIntent().getParcelableArrayListExtra(EXTRA_DOA);
//        doa = getIntent().getParcelableExtra(EXTRA_DOA);
        position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_next) {
                    position++;
                    updateView(position);
                } else if (v == btn_prev) {
                    position--;
                    updateView(position);
                }
            }
        };

        btn_next.setOnClickListener(clickListener);
        btn_prev.setOnClickListener(clickListener);

        updateView(position);
    }

    private void updateView(int position) {
        Log.d(TAG, "Position " + position);
        doa = doas.get(position);

        float alpha = 0.5f;

        if (position == 0) {
            btn_prev.setAlpha(alpha);
        } else if (position + 1 == doas.size()) {
            btn_next.setAlpha(alpha);
        } else {
            btn_prev.setAlpha(1.0f);
            btn_next.setAlpha(1.0f);
        }

        if (position % 2 == 0) {
            tv_title.setTextColor(ContextCompat.getColor(context, R.color.primary_orange));
            tv_latin.setTextColor(ContextCompat.getColor(context, R.color.primary_orange));
            tv_trans.setTextColor(ContextCompat.getColor(context, R.color.primary_orange));
            cv_arabic.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_orange));
            cv_artinya.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_orange));
            btn_next.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.primary_orange));
            btn_prev.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.primary_orange));
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary_orange)));
        } else {
            tv_title.setTextColor(ContextCompat.getColor(context, R.color.primary_blue));
            tv_latin.setTextColor(ContextCompat.getColor(context, R.color.primary_blue));
            tv_trans.setTextColor(ContextCompat.getColor(context, R.color.primary_blue));
            cv_arabic.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_blue));
            cv_artinya.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary_blue));
            btn_next.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.primary_blue));
            btn_prev.setBackgroundTintList(ContextCompat.getColorStateList(context, R.color.primary_blue));
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.primary_blue)));
        }

        String latin = "\"" + doa.getInLatin() + "\"";
        tv_title.setText(doa.getTitle());
        tv_arabic.setText(doa.getInArabic());
        tv_latin.setText(latin);
        tv_trans.setText(doa.getTranslation());

        btn_prev.setEnabled(position > 0);
        btn_next.setEnabled(position + 1 < doas.size());
    }
}