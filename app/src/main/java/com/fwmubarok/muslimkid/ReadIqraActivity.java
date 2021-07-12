package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadIqraActivity extends AppCompatActivity {

    public static final String EXTRA_IQRA_NUMBER = "";
    private String pdf_name = "";

    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page page;
    private ParcelFileDescriptor parcelFileDescriptor;

    //Tezt View
    private TextView tv_iqra_num;

    //Image view
    private ImageView iv_pdf_page;

    //Button
    private Button btn_next, btn_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_iqra);

        tv_iqra_num = findViewById(R.id.tv_read_iqra_num);
        iv_pdf_page = findViewById(R.id.pdf_page);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == btn_next) {
                    renderpage(page.getIndex() + 1);
                } else if (v == btn_prev) {
                    renderpage(page.getIndex() - 1);
                }
            }
        };

//        pdfView = findViewById(R.id.pdf_view);
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

        String i_num = "Iqra " + Integer.toString(iqra_num);
        tv_iqra_num.setText(i_num);

        btn_next.setOnClickListener(clickListener);
        btn_prev.setOnClickListener(clickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initrendered();
        renderpage(0);
    }

    private void renderpage(int pageIndex) {
        if (page != null) {
            page.close();
        }

        page = pdfRenderer.openPage(pageIndex);
        Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        iv_pdf_page.setImageBitmap(bitmap);
        btn_next.setEnabled(page.getIndex() + 1 < pdfRenderer.getPageCount());
        btn_prev.setEnabled(page.getIndex() > 0);
    }

    private void initrendered() {
        try {
            File temp = new File(getCacheDir(), "tempPDF.pdf");
            FileOutputStream fileOutputStream = new FileOutputStream(temp);
            InputStream inputStream = getAssets().open(pdf_name);

            byte[] buffer = new byte[1024];
            int readBytes;
            while ((readBytes = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, readBytes);
            }
            fileOutputStream.close();
            inputStream.close();

            parcelFileDescriptor = ParcelFileDescriptor.open(temp, ParcelFileDescriptor.MODE_READ_ONLY);
            pdfRenderer = new PdfRenderer(parcelFileDescriptor);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        if (isFinishing()) {
            if (page != null) {
                page.close();
            }
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pdfRenderer.close();
        }
        super.onPause();
    }
}