package com.fwmubarok.muslimkid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadIqraActivity extends AppCompatActivity {
    private Context context = this;

    public static final String EXTRA_IQRA_NUMBER = "";
    private String pdf_name = "";
    private int color_primary = R.color.disable_gray;
    private int color_secondary = R.color.disable_gray;

    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page page;
    private ParcelFileDescriptor parcelFileDescriptor;
    private Window window;

    //Toolbar
    private Toolbar r_iqra_toolbar;

    //Text View
    private TextView tv_iqra_num, tv_page_num;

    //Image view
    private PhotoView iv_pdf_page;
    private ImageView iv_half_circle;

    //Button
    private Button btn_next, btn_prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_iqra);

        window = getWindow();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_iqra_num = findViewById(R.id.tv_read_iqra_num);
        tv_page_num = findViewById(R.id.tv_page_num);
        iv_pdf_page = findViewById(R.id.pdf_page);
        iv_half_circle = findViewById(R.id.iv_half_circle);
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
                color_primary = R.color.primary_red;
                color_secondary = R.color.secondary_red;
                break;
            case 2:
                pdf_name = "iqro2.pdf";
                color_primary = R.color.primary_green;
                color_secondary = R.color.secondary_green;
                break;
            case 3:
                pdf_name = "iqro3.pdf";
                color_primary = R.color.primary_blue;
                color_secondary = R.color.secondary_blue;
                break;
            case 4:
                pdf_name = "iqro4.pdf";
                color_primary = R.color.primary_yellow;
                color_secondary = R.color.secondary_yellow;
                break;
            case 5:
                pdf_name = "iqro5.pdf";
                color_primary = R.color.primary_blue;
                color_secondary = R.color.secondary_blue;
                break;
            case 6:
                pdf_name = "iqro6.pdf";
                color_primary = R.color.primary_orange;
                color_secondary = R.color.secondary_orange;
                break;
        }

        String i_num = "Iqra " + iqra_num;
        tv_iqra_num.setText(i_num);
        actionBar.setTitle(Html.fromHtml("<b>" + i_num + "</b>"));

        iv_half_circle.setBackgroundTintList(ContextCompat.getColorStateList(context, color_primary));

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(color_secondary));

        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(color_primary)));

        btn_next.setBackgroundTintList(ContextCompat.getColorStateList(context, color_primary));
        btn_prev.setBackgroundTintList(ContextCompat.getColorStateList(context, color_primary));
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
        String pg_num = "Page " + Integer.toString(pageIndex);
        if (pageIndex == 0) {
            pg_num = "Cover";
        }
        tv_page_num.setText(pg_num);
        tv_page_num.setTextColor(ContextCompat.getColor(context, color_primary));

        float alpha = 0.5f;

        if (page.getIndex() == 0) {
            btn_prev.setAlpha(alpha);
        } else if (page.getIndex() + 1 == pdfRenderer.getPageCount()) {
            btn_next.setAlpha(alpha);
        } else {
            btn_prev.setAlpha(1.0f);
            btn_next.setAlpha(1.0f);
        }

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