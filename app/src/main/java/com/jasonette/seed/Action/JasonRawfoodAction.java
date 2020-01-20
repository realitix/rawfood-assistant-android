package com.jasonette.seed.Action;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.jasonette.seed.Helper.JasonHelper;
import com.jasonette.seed.Launcher.Launcher;
import com.jasonette.seed.Rawfood.AlimentUpdater;
import com.jasonette.seed.Rawfood.Database.Dao.ReceipeDao;
import com.jasonette.seed.Rawfood.Database.Dao.ReceipeStepDao;
import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.Meal;
import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepAliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepReceipe;
import com.jasonette.seed.Rawfood.Database.RawfoodDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

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

    public void createMeal(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long timestamp = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    timestamp = options.getLong("timestamp");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                Meal meal = new Meal(timestamp, 2, "");
                long meal_id = db.mealDao().insert(meal);
                JasonHelper.next("success", action, toJsonId(meal_id), event, context);
            }
        });
    }

    public void createReceipe(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String receipeName = "";
                try {
                    final JSONObject options = action.getJSONObject("options");
                    receipeName = options.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                Receipe receipe = new Receipe(receipeName, 1, 3);
                long receipe_id = db.receipeDao().insert(receipe);
                JasonHelper.next("success", action, toJsonId(receipe_id), event, context);
            }
        });
    }

    public void createReceipeStep(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String description = "";
                long receipeId = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    description = options.getString("description");
                    receipeId = options.getLong("receipe_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                ReceipeStep step = new ReceipeStep(receipeId, 0, description, 10);
                long step_id = db.receipeStepDao().insert(step);
                JasonHelper.next("success", action, toJsonId(step_id), event, context);
            }
        });
    }

    public void createReceipeStepAliment(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long alimentId = 0;
                long stepId = 0;
                int quantity = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    alimentId = options.getLong("aliment_id");
                    stepId = options.getLong("step_id");
                    quantity = options.getInt("quantity");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                ReceipeStepAliment stepAliment = new ReceipeStepAliment(alimentId, stepId, quantity);
                long step_id = db.receipeStepAlimentDao().insert(stepAliment);
                JasonHelper.next("success", action, toJsonId(step_id), event, context);
            }
        });
    }

    public void createReceipeStepReceipe(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long receipeId = 0;
                long stepId = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    receipeId = options.getLong("receipe_id");
                    stepId = options.getLong("step_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                ReceipeStepReceipe stepReceipe = new ReceipeStepReceipe(receipeId, stepId);
                long step_id = db.receipeStepReceipeDao().insert(stepReceipe);
                JasonHelper.next("success", action, toJsonId(step_id), event, context);
            }
        });
    }

    public void viewMeal(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long mealId = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    mealId = options.getLong("meal_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                Meal meal = db.mealDao().get(mealId);
                JasonHelper.next("success", action, toJson(meal), event, context);
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
                    receipe_id = options.getLong("receipe_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                ReceipeDao.ReceipeFull receipe = db.receipeDao().getFull(receipe_id);
                JasonHelper.next("success", action, toJson(receipe), event, context);
            }
        });
    }

    public void viewReceipeStep(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long step_id = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    step_id = options.getLong("step_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                ReceipeStepDao.ReceipeStepFull step = db.receipeStepDao().getFull(step_id);
                Log.e("test", toJson(step));
                JasonHelper.next("success", action, toJson(step), event, context);
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

    public void searchAliments(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String search = "";
                try {
                    final JSONObject options = action.getJSONObject("options");
                    search = options.getString("search");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String asciiName = Normalizer.normalize(search, Normalizer.Form.NFD)
                        .replaceAll("[^\\p{ASCII}]", "");

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                List<Aliment> aliments = db.alimentDao().search(asciiName);
                JasonHelper.next("success", action, toJson(aliments), event, context);
            }
        });
    }

    public void searchMeals(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                long begin = 0;
                long end = 0;
                try {
                    final JSONObject options = action.getJSONObject("options");
                    begin = options.getLong("begin");
                    end = options.getLong("end");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                List<Meal> meals = db.mealDao().search(begin, end);
                JasonHelper.next("success", action, toJson(meals), event, context);
            }
        });
    }

    public void searchReceipes(final JSONObject action, final JSONObject data, final JSONObject event, final Context context) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                String search = "";
                try {
                    final JSONObject options = action.getJSONObject("options");
                    search = options.getString("search");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RawfoodDatabase db = RawfoodDatabase.getInstance(context);
                List<Receipe> aliments = db.receipeDao().search(search);
                JasonHelper.next("success", action, toJson(aliments), event, context);
            }
        });
    }
}
