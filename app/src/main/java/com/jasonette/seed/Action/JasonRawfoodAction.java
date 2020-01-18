package com.jasonette.seed.Action;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.jasonette.seed.Helper.JasonHelper;
import com.jasonette.seed.Launcher.Launcher;
import com.jasonette.seed.Rawfood.AlimentUpdater;
import com.jasonette.seed.Rawfood.Database.Dao.ReceipeDao;
import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.AlimentCategory;
import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.RawfoodDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JasonRawfoodAction {
    class ReturnClass {
        public long id;
    }

    private String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    private String toJsonId(long id) {
        ReturnClass result = new ReturnClass();
        result.id = id;
        return toJson(result);
    }

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
    }

    public void createReceipe(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String receipe_name = "";
                try {
                    final JSONObject options = action.getJSONObject("options");
                    receipe_name = options.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                Receipe receipe = new Receipe(receipe_name, 1, 3);
                long receipe_id = db.receipeDao().insert(receipe);
                JasonHelper.next("success", action, toJsonId(receipe_id), event, context);
            }
        });
    }

    public void viewReceipe(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long receipe_id = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    receipe_id = options.getLong("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                ReceipeDao.ReceipeWithSteps receipe = db.receipeDao().getWithSteps(receipe_id);
                Log.e("test", toJson(receipe));
                JasonHelper.next("success", action, toJson(receipe), event, context);
            }
        });
    }

    public void listReceipes(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                List<Receipe> receipes = db.receipeDao().getList();
                JasonHelper.next("success", action, toJson(receipes), event, context);
            }
        });
    }
}