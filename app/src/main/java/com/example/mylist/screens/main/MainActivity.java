package com.example.mylist.screens.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mylist.App;
import com.example.mylist.R;
import com.example.mylist.databinding.ActivityMainBinding;
import com.example.mylist.model.Product;
import com.example.mylist.model.ProductsAdapter;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView ReView;

    private EditText editText;

    private ProductsAdapter adapter;


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ReView = findViewById(R.id.RV);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        ReView.setLayoutManager(manager);
        ReView.setHasFixedSize(true);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                adapter.removeItemAt(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(ReView);

        adapter = new ProductsAdapter();

        ReView.setAdapter(adapter);

        adapter.setItems(App.getInstance().getProductDao().getAll());

        App.getInstance().getDatabase().clearAllTables();

        editText = findViewById(R.id.ET);

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0){
                    adapter.putItem(new Product(adapter.getIndex(), editText.getText().toString()));
                    editText.setText("");
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SortedList<Product> list = adapter.getProductList();
        for (int i = 0; i < adapter.getItemCount(); i++){
            App.getInstance().getProductDao().insertProduct(new Product(i+1, list.get(i).getName()));
        }
    }
}