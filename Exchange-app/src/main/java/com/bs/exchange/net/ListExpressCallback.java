package com.bs.exchange.net;

import com.bs.exchange.bean.ExchangeInfo;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;



public abstract class ListExpressCallback extends Callback<ExchangeInfo> {
    @Override
    public ExchangeInfo parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        ExchangeInfo exchangeInfo = new Gson().fromJson(string, ExchangeInfo.class);
        return exchangeInfo;
    }


}

