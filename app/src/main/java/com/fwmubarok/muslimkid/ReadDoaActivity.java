package com.fwmubarok.muslimkid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    private NestedScrollView ns_scroll;

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

        ns_scroll = findViewById(R.id.nested_scroll);

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
                    ns_scroll.scrollTo(0,0);
                } else if (v == btn_prev) {
                    position--;
                    updateView(position);
                    ns_scroll.scrollTo(0,0);
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

        int color_primary = R.color.disable_gray;
        int color_secondary = R.color.disable_gray;

        if (position % 4 == 0) {
            color_primary = R.color.primary_red;
            color_secondary = R.color.secondary_red;
        } else if (position % 4 == 1){
            color_primary = R.color.primary_blue;
            color_secondary = R.color.secondary_blue;
        } else if (position % 4 == 2){
            color_primary = R.color.primary_green;
            color_secondary = R.color.secondary_green;
        } else {
            color_primary = R.color.primary_orange;
            color_secondary = R.color.secondary_orange;
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(color_secondary));
        tv_title.setTextColor(ContextCompat.getColor(context, color_primary));
        tv_latin.setTextColor(ContextCompat.getColor(context, color_primary));
        tv_trans.setTextColor(ContextCompat.getColor(context, color_primary));
        cv_arabic.setCardBackgroundColor(ContextCompat.getColor(context, color_primary));
        cv_artinya.setCardBackgroundColor(ContextCompat.getColor(context, color_primary));
        btn_next.setBackgroundTintList(ContextCompat.getColorStateList(context, color_primary));
        btn_prev.setBackgroundTintList(ContextCompat.getColorStateList(context, color_primary));
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(color_primary)));

        String latin = "\"" + doa.getInLatin() + "\"";
        tv_title.setText(doa.getTitle());
        tv_arabic.setText(doa.getInArabic());
        tv_latin.setText(latin);
        tv_trans.setText(doa.getTranslation());

        btn_prev.setEnabled(position > 0);
        btn_next.setEnabled(position + 1 < doas.size());
    }
}