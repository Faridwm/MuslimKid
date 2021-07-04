package com.fwmubarok.muslimkid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.fwmubarok.muslimkid.Adapter.DailyDoaAdapter;
import com.fwmubarok.muslimkid.Model.DailyDoa;
import com.fwmubarok.muslimkid.Model.Doa;
import com.fwmubarok.muslimkid.Model.Result;
import com.fwmubarok.muslimkid.MyInterface.MuslimApiInterface;
import com.fwmubarok.muslimkid.REST.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoaMenuActivity extends AppCompatActivity implements DailyDoaAdapter.OnDailyDoaListener{
    private final String TAG = this.getClass().getSimpleName();
    private DailyDoa result;
    private List<Doa> doas = new ArrayList<>();

    private MuslimApiInterface muslimApiInterface;

    private RecyclerView rv_daily_doa;
    private RecyclerView.LayoutManager layoutManager;
    private DailyDoaAdapter dailyDoaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_menu);

        muslimApiInterface = ApiClient.getClient().create(MuslimApiInterface.class);

        rv_daily_doa = findViewById(R.id.rv_daily_doa);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_daily_doa.setLayoutManager(layoutManager);

        getDailyDoa();
    }

    private void getDailyDoa() {
        Call<DailyDoa> call = muslimApiInterface.getDailyDoa();
        call.enqueue(new Callback<DailyDoa>() {
            @Override
            public void onResponse(Call<DailyDoa> call, Response<DailyDoa> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "Code: " + response.code());
                }
                result = response.body();
                if (result != null) {
                    Log.d(TAG, "onResponse: Size Data" + result.getResult().getData().size());
                    List<Doa> doas = result.getResult().getData();
                    addToLisDoas(doas);
                }
            }

            @Override
            public void onFailure(Call<DailyDoa> call, Throwable t) {
                Log.d(TAG, "Message: " + t.getMessage());
            }
        });

    }

    private void addToLisDoas(List<Doa> doas) {
        this.doas = doas;
        dailyDoaAdapter = new DailyDoaAdapter(this.doas, this);
        rv_daily_doa.setAdapter(dailyDoaAdapter);
    }

    @Override
    public void OnDailyDoaClick(int position) {

    }
}