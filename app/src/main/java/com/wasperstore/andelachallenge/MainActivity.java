package com.wasperstore.andelachallenge;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wasperstore.andelachallenge.apiData.Client;
import com.wasperstore.andelachallenge.apiData.Service;
import com.wasperstore.andelachallenge.model.Item;
import com.wasperstore.andelachallenge.model.ItemResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView Disconnected;
    ProgressDialog pd;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(MainActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Github Users...");
        pd.setCancelable(false);
        pd.show();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        loadJSON();
    }

    private void loadJSON() {
        Disconnected = (TextView) findViewById(R.id.disconnected);
        try {
            Client Client = new Client();
            Service apiService =
                    Client.getClient().create(Service.class);
            Call<ItemResult> call = apiService.getItems();
            call.enqueue(new Callback<ItemResult>() {
                @Override
                public void onResponse(Call<ItemResult> call, Response<ItemResult> response) {
                    List<Item> items = response.body().getItems();
                    recyclerView.setAdapter(new ItemAdapter(getApplicationContext(), items));
                    recyclerView.smoothScrollToPosition(0);
                    swipeContainer.setRefreshing(false);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<ItemResult> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    Disconnected.setVisibility(View.VISIBLE);
                    pd.hide();

                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
