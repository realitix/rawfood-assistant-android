package com.jasonette.seed.Action;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jasonette.seed.Helper.JasonHelper;
import com.jasonette.seed.Launcher.Launcher;
import com.jasonette.seed.Rawfood.AlimentUpdater;
import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.AlimentCategory;
import com.jasonette.seed.Rawfood.Database.RawfoodDatabase;

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

    public void updateAliments(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                AlimentUpdater au = new AlimentUpdater();
                au.update(context);
                JSONObject empty = new JSONObject();
                JasonHelper.next("success", action, empty, event, context);
            }
        });
        /*AlimentUpdater au = new AlimentUpdater();
        au.update(context);
        JSONObject empty = new JSONObject();
        JasonHelper.next("success", action, empty, event, context);*/
    }

    public void insertAlimentCategory(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        /*RawfoodDatabase db = RawfoodDatabase.getInstance(context);
        AlimentCategory ac = new AlimentCategory("test", true);
        db.alimentCategoryDao().insert(ac);

        Aliment a = new Aliment("name", "name2", ac);
        db.alimentDao().insert(a);

        JSONObject empty = new JSONObject();
        JasonHelper.next("success", action, empty, event, context);*/
    }
}
