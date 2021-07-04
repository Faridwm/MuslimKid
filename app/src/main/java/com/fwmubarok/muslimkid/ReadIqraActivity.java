package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class ReadIqraActivity extends AppCompatActivity {

    public static final String EXTRA_IQRA_NUMBER = "";

    //WebView
    WebView wv_pdf;

    //Progress Bar
    ProgressBar loading_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_iqra);

        wv_pdf = findViewById(R.id.wv_pdf);
        loading_bar = findViewById(R.id.loading_bar);

        wv_pdf.setVisibility(View.GONE);
        loading_bar.setVisibility(View.VISIBLE);

        wv_pdf.getSettings().setJavaScriptEnabled(true);
//        wv_pdf.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 );
        wv_pdf.getSettings().setAppCacheEnabled(true);

        String url = "";
        int iqra_num = getIntent().getIntExtra(EXTRA_IQRA_NUMBER, 1);
        switch (iqra_num) {
            case 1:
                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra1";
                break;
            case 2:
                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra2";
                break;
            case 3:
                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra3";
                break;
            case 4:
                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra4";
                break;
            case 5:
                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra5";
                break;
            case 6:
                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra6";
                break;
        }

        wv_pdf.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);

        wv_pdf.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                wv_pdf.setVisibility(View.VISIBLE);
                loading_bar.setVisibility(View.GONE);
            }
        });
    }
}