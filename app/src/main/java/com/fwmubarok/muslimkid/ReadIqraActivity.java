package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class ReadIqraActivity extends AppCompatActivity {

    public static final String EXTRA_IQRA_NUMBER = "";
    private String pdf_name = "";

    //PDF view
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_iqra);

        pdfView = findViewById(R.id.pdf_view);
        int iqra_num = getIntent().getIntExtra(EXTRA_IQRA_NUMBER, 1);
        switch (iqra_num) {
            case 1:
                pdf_name = "iqro1.pdf";
                break;
            case 2:
                pdf_name = "iqro2.pdf";
                break;
            case 3:
                pdf_name = "iqro3.pdf";
                break;
            case 4:
                pdf_name = "iqro4.pdf";
                break;
            case 5:
                pdf_name = "iqro5.pdf";
                break;
            case 6:
                pdf_name = "iqro6.pdf";
                break;
        }
        pdfView.fromAsset(pdf_name).load();
    }


}