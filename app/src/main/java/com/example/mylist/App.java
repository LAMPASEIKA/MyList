package com.example.mylist;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.example.mylist.data.AppDatabase;
import com.example.mylist.data.ProductDao;

public class App extends Application {

    private AppDatabase database;
    private ProductDao productDao;

    private static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "app_db")
                .allowMainThreadQueries()
                .build();

        productDao = database.productDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
