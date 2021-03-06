package com.jasonette.seed.Rawfood.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jasonette.seed.Rawfood.Database.Entity.AlimentCategory;

import java.util.List;

@Dao
public interface AlimentCategoryDao {
    @Query("SELECT * FROM AlimentCategory WHERE name=:name")
    AlimentCategory getByName(String name);

    @Insert
    long insert(AlimentCategory alimentCategory);
}
