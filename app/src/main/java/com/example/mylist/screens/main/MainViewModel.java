package com.example.mylist.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mylist.App;
import com.example.mylist.model.Product;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Product>> productLiveData = App.getInstance().getProductDao().getAllLiveData();

    public LiveData<List<Product>> getProductLiveData(){
        return productLiveData;
    }
}
