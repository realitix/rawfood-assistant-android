package com.jasonette.seed.Action;

import android.content.Context;
import android.util.Log;

import com.jasonette.seed.Helper.JasonHelper;
import com.jasonette.seed.Launcher.Launcher;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JasonRawfoodAction {
    public void ready(final JSONObject action, final JSONObject data, final JSONObject event, final Context context){
        String url = "http://127.0.0.1:18385";
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();
        OkHttpClient client = ((Launcher)context.getApplicationContext()).getHttpClient(1);
        final int max_retry = 60;
        int i = 0;
        for(i = 0; i < max_retry; i++) {
            try {
                client.newCall(request).execute();
                break;
            } catch (IOException e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        String next = i < max_retry ? "success" : "error";
        JSONObject empty = new JSONObject();
        JasonHelper.next(next, action, empty, event, context);
    }
}
