package com.jasonette.seed.Rawfood;

import android.content.Context;
import android.util.Log;

import com.jasonette.seed.Launcher.Launcher;
import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.AlimentCategory;
import com.jasonette.seed.Rawfood.Database.Entity.AlimentNutrition;
import com.jasonette.seed.Rawfood.Database.RawfoodDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AlimentUpdater {
    public static final String ALIMENT_URL = "https://raw.githubusercontent.com/realitix/food-database/master/out/data.json";

    private JSONObject download_json(final Context context) {
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(ALIMENT_URL).build();
        OkHttpClient client = ((Launcher)context.getApplicationContext()).getHttpClient(10);
        String data_raw = null;
        try {
            data_raw = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject data = null;
        try {
            data = new JSONObject(data_raw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void update(final Context context) {
        Log.i("rawfood", "Update aliments");
        JSONObject data = download_json(context);
        RawfoodDatabase db = RawfoodDatabase.getInstance(context);
        int nb_aliments_updated = 0;
        try {
            Map<String, AlimentCategory> categoryMap = new HashMap<String, AlimentCategory>();
            JSONArray aliments = data.getJSONArray("aliments");
            for (int i = 0; i < aliments.length(); i++) {
                JSONObject JSONaliment = aliments.getJSONObject(i);

                // Get category
                String group_name = JSONaliment.getString("group_name_fr");
                boolean fresh = JSONaliment.getBoolean("fresh");
                AlimentCategory ac = db.alimentCategoryDao().getByName(group_name); // Return NULL
                if (ac == null) {
                    AlimentCategory ac_insert = new AlimentCategory(group_name, fresh);
                    long new_id = db.alimentCategoryDao().insert(ac_insert);
                    ac = ac_insert;
                    ac.id = new_id;
                }

                // Create nutrition
                JSONObject JSONnutrition = JSONaliment.getJSONObject("nutrition");
                int protein = JSONnutrition.getInt("protein");
                int glucid = JSONnutrition.getInt("glucid");
                int lipid = JSONnutrition.getInt("lipid");
                AlimentNutrition nutrition = new AlimentNutrition(protein, glucid, lipid);

                // Insert aliment
                String n = JSONaliment.getString("name_fr");
                Aliment a = db.alimentDao().getByName(n);
                if (a == null) {
                    Aliment a_insert = new Aliment(n, n, ac.id, nutrition);
                    db.alimentDao().insert(a_insert);
                    nb_aliments_updated++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("rawfood", nb_aliments_updated + " aliments updated");
    }
}
