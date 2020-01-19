package com.jasonette.seed.Rawfood.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jasonette.seed.Rawfood.Database.Dao.AlimentCategoryDao;
import com.jasonette.seed.Rawfood.Database.Dao.AlimentDao;
import com.jasonette.seed.Rawfood.Database.Dao.ReceipeDao;
import com.jasonette.seed.Rawfood.Database.Dao.ReceipeStepDao;
import com.jasonette.seed.Rawfood.Database.Entity.Aliment;
import com.jasonette.seed.Rawfood.Database.Entity.AlimentCategory;
import com.jasonette.seed.Rawfood.Database.Entity.Receipe;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStep;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepAliment;
import com.jasonette.seed.Rawfood.Database.Entity.ReceipeStepReceipe;

@Database(entities = {
        Aliment.class,
        AlimentCategory.class,
        Receipe.class,
        ReceipeStep.class,
        ReceipeStepAliment.class,
        ReceipeStepReceipe.class
}, exportSchema = false, version = 1)
public abstract class RawfoodDatabase extends RoomDatabase {
    private static final String DB_NAME = "rawfood_db";
    private static RawfoodDatabase instance;

    public static synchronized RawfoodDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), RawfoodDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract AlimentCategoryDao alimentCategoryDao();
    public abstract AlimentDao alimentDao();
    public abstract ReceipeDao receipeDao();
    public abstract ReceipeStepDao receipeStepDao();
}
