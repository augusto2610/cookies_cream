package com.incluit.apinto.cookiescream.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.incluit.apinto.cookiescream.R;
import com.incluit.apinto.cookiescream.adapters.FeatureListAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);
        FeatureListAdapter adapter = new FeatureListAdapter(getApplicationContext(), getFeatureList());
        mRecyclerView.setAdapter(adapter);

    }

    private List<String> getFeatureList() {
        List<String> featureList = new ArrayList<>();
        featureList.add("Picture-in-Picture Mode");
        featureList.add("Notifications");
        featureList.add("AutoFill framework");
        featureList.add("Autosizing TexView");
        featureList.add("Unified layout margin and padding");
        featureList.add("Input and Navigation");
        return featureList;
    }



}
