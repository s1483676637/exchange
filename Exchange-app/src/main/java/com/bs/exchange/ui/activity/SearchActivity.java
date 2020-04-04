package com.bs.exchange.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bmob.exchange.R;
import com.bs.exchange.base.BaseActivity;
import com.bs.exchange.bean.ExchangeInfo;
import com.bs.exchange.ui.adapter.ExchangeListAdapter;

import java.util.List;


public class SearchActivity extends BaseActivity {


    RecyclerView recyclerview;
    private List<ExchangeInfo.ListEntity> list ;
    private ExchangeListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);
        onSetTitle("Info");
        list = (List<ExchangeInfo.ListEntity>) getIntent().getSerializableExtra("list");
        initView();
    }


    private void initView() {
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExchangeListAdapter(this,list);
        recyclerview.setAdapter(adapter);

    }




}
