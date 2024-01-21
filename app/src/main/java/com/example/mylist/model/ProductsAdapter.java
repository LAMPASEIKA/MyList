package com.example.mylist.model;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.mylist.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

    private SortedList<Product> products = new SortedList<>(Product.class, new SortedList.Callback<Product>() {
        @Override
        public int compare(Product product, Product product2) {
            if (product.getUid() < product2.getUid()){
                return -1;
            } else if (product.getUid() > product2.getUid()) {
                return 1;
            }
            return 0;
        }

        @Override
        public boolean areContentsTheSame(Product product, Product product2) {
            return product2.equals(product);
        }

        @Override
        public boolean areItemsTheSame(Product contact, Product contact2) {
            return false;
        }

        @Override
        public void onChanged(int pos, int count) {
            notifyItemRangeChanged(pos, count);
        }

        @Override
        public void onInserted(int pos, int count) {
            notifyItemRangeInserted(pos, count);
        }

        @Override
        public void onRemoved(int pos, int count) {
            notifyItemRangeRemoved(pos, count);
        }

        @Override
        public void onMoved(int pos, int count) {
            notifyItemMoved(pos, count);
        }
    });

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.products, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        productViewHolder.bind(products.get(i));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void putItem(Product product) {
        products.add(product);
    }

    public void removeItemAt(int index) {
        products.removeItemAt(index);
    }

    public void setItems(List<Product> productslist) {
        products.replaceAll(productslist);
    }

    public SortedList<Product> getProductList(){
        return products;
    }

    public int getIndex(){
        if (products.size() > 0){
            return products.get(products.size() - 1).getUid() + 1;
        }
        return 1;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.productName);
        }

        public void bind(Product contact) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(contact.getName())) {
                sb.append(contact.getUid());
                sb.append(") ");
                sb.append(contact.getName());
            }
            textView.setText(sb);
        }
    }
}
