package com.fwmubarok.muslimkid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.fwmubarok.muslimkid.Adapter.DailyDoaAdapter;
import com.fwmubarok.muslimkid.Model.DailyDoa;
import com.fwmubarok.muslimkid.Model.Doa;
import com.fwmubarok.muslimkid.MyInterface.MuslimApiInterface;
import com.fwmubarok.muslimkid.REST.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoaMenuActivity extends AppCompatActivity implements DailyDoaAdapter.OnDailyDoaListener{
    private Context context = this;
    private final String TAG = this.getClass().getSimpleName();
    private DailyDoa result;
    private ArrayList<Doa> doas = new ArrayList<>();
    private boolean isSuccess;

    private MuslimApiInterface muslimApiInterface;

    private TextView tv_err_msg;

    private Button btn_refresh;

    private ConstraintLayout layout_loader, layout_failed;
    private NestedScrollView layout_success;

    private RecyclerView rv_daily_doa;
    private RecyclerView.LayoutManager layoutManager;
    private DailyDoaAdapter dailyDoaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        layout_success = findViewById(R.id.layout_get_data);
        layout_success.setVisibility(View.GONE);
        layout_failed = findViewById(R.id.layout_failed_get_data);
        layout_failed.setVisibility(View.GONE);
        layout_loader = findViewById(R.id.layout_loader);
        layout_loader.setVisibility(View.VISIBLE);

        invalidateOptionsMenu();

        //Component layout error
        tv_err_msg = findViewById(R.id.tv_doa_error_msg);
        btn_refresh = findViewById(R.id.btn_refresh);

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        });

        muslimApiInterface = ApiClient.getClient().create(MuslimApiInterface.class);

        rv_daily_doa = findViewById(R.id.rv_daily_doa);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv_daily_doa.setLayoutManager(layoutManager);

        getDailyDoa();
    }

    private void getDailyDoa() {
//        Log.d(TAG, "getDailyDoa: " + isCanAccessInternet());
        if (true) {
            Call<DailyDoa> call = muslimApiInterface.getDailyDoa();
            call.enqueue(new Callback<DailyDoa>() {
                @Override
                public void onResponse(Call<DailyDoa> call, Response<DailyDoa> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "Code: " + response.code());
                        tv_err_msg.setText(response.message());
                        layout_loader.setVisibility(View.GONE);
                        layout_success.setVisibility(View.GONE);
                        layout_failed.setVisibility(View.VISIBLE);
                        isSuccess = false;
                        invalidateOptionsMenu();
                    }
                    result = response.body();
                    if (result != null) {
                        Log.d(TAG, "onResponse: Size Data" + result.getResult().getData().size());
                        ArrayList<Doa> doas = result.getResult().getData();
                        layout_loader.setVisibility(View.GONE);
                        layout_success.setVisibility(View.VISIBLE);
                        layout_failed.setVisibility(View.GONE);
                        isSuccess = true;
                        invalidateOptionsMenu();
                        addToListDoas(doas);
                    }
                }

                @Override
                public void onFailure(Call<DailyDoa> call, Throwable t) {
                    Log.d(TAG, "Message: " + t.getMessage());
                    tv_err_msg.setText(t.getMessage());
                    layout_loader.setVisibility(View.GONE);
                    layout_success.setVisibility(View.GONE);
                    layout_failed.setVisibility(View.VISIBLE);
                    isSuccess = false;
                    invalidateOptionsMenu();
                }
            });
        } else {
            String no_inet = "No Internet Connection";
            tv_err_msg.setText(no_inet);
            layout_loader.setVisibility(View.GONE);
            layout_success.setVisibility(View.GONE);
            layout_failed.setVisibility(View.VISIBLE);
            isSuccess = false;
            invalidateOptionsMenu();
        }
    }

    private void addToListDoas(ArrayList<Doa> doas) {
        this.doas = doas;
        dailyDoaAdapter = new DailyDoaAdapter(this.doas, this);
        rv_daily_doa.setAdapter(dailyDoaAdapter);
    }

    @Override
    public void OnDailyDoaClick(int position) {
        Log.d(TAG, "OnDailyDoaClick: Clicked");
        Intent intent = new Intent(context, ReadDoaActivity.class);
        intent.putParcelableArrayListExtra(ReadDoaActivity.EXTRA_DOA, doas);
//        intent.putExtra(ReadDoaActivity.EXTRA_DOA, doas.get(position));
        intent.putExtra(ReadDoaActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.doa_menu, menu);

        MenuItem searchItemDoa = menu.findItem(R.id.search_doa);
        SearchView searchView = (SearchView) searchItemDoa.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchItemDoa.setVisible(isSuccess);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                dailyDoaAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private boolean isCanAccessInternet() {
        try {
            String command = "ping -c 1 google.com";
            return Runtime.getRuntime().exec(command).waitFor() == 0;

        } catch (Exception e) {
            Log.d(TAG, "isCanAccessInternet: " + e.getMessage());
        }
        return false;
    }
}