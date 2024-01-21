package com.example.mylist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Product {
    @ColumnInfo(name = "name")
    private String name;

    @PrimaryKey
    private int uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid){this.uid = uid;}

    public Product(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return uid == product.uid && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uid);
    }
}
