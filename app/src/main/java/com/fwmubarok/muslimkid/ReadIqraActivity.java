package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ReadIqraActivity extends AppCompatActivity {

    public static final String EXTRA_IQRA_NUMBER = "";
    private String url = "";

    //WebView
    WebView wv_pdf;

    //Progress Bar
    ProgressBar loading_bar;

    //PDF view
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_iqra);

        pdfView = findViewById(R.id.pdf_view);
//        wv_pdf = findViewById(R.id.wv_pdf);
//        loading_bar = findViewById(R.id.loading_bar);

//        wv_pdf.setVisibility(View.GONE);
        pdfView.setVisibility(View.GONE);
//        loading_bar.setVisibility(View.VISIBLE);

        pdfView.fromAsset("iqra1.pdf")
                .scrollHandle(new DefaultScrollHandle(this))
                .load();











//        wv_pdf.getSettings().setJavaScriptEnabled(true);
////        wv_pdf.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 );
//        wv_pdf.getSettings().setAppCacheEnabled(true);
//
//
//        int iqra_num = getIntent().getIntExtra(EXTRA_IQRA_NUMBER, 1);
//        switch (iqra_num) {
//            case 1:
//                url = "https://drive.google.com/file/d/1ae0u73T-6sdTbSXEHXN3GRcMi_m-6gAX/view";
////                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra1";
//                break;
//            case 2:
//                url = "https://drive.google.com/file/d/1-NcgQOBuznFTHckqzwE5vLBTbb6O3b-i/view";
////                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra2";
//                break;
//            case 3:
//                url = "https://drive.google.com/file/d/1QXFNXeCbioNy7dNpG5nI44EkHiiqQrug/view";
////                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra3";
//                break;
//            case 4:
//                url = "https://drive.google.com/file/d/1khnyLfa30KYGU4yV1JYl6935BLVlXJjn/view";
////                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra4";
//                break;
//            case 5:
//                url = "https://drive.google.com/file/d/1mYgeQ9qhHvEh7cQBjDzPq-ep_JuDWayy/view";
////                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra5";
//                break;
//            case 6:
//                url = "https://drive.google.com/file/d/1eB5f1aNkLvM-g9cpcfj9ygbrcshGZBsR/view";
////                url = "https://islamic-api-indonesia.herokuapp.com/api/data/pdf/iqra6";
//                break;
//        }
//
//
////        wv_pdf.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
////        wv_pdf.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
//        wv_pdf.loadUrl(url);
////
//        wv_pdf.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                wv_pdf.setVisibility(View.VISIBLE);
//                loading_bar.setVisibility(View.GONE);
//            }
////
////            @Override
////            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                view.loadUrl(url);
////                return super.shouldOverrideUrlLoading(view, request);
////            }
//        });
    }


}