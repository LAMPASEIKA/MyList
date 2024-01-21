package com.example.mylist.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mylist.model.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
