package com.dxt2.greendaoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.dxt2.greendaoo.bean.Product;
import com.dxt2.greendaoo.bean.ProductDao;
import com.dxt2.greendaoo.callback.OnItemClickListener;
import com.dxt2.greendaoo.callback.OnItemLongClickListener;
import com.dxt2.greendaoo.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProductDao productDao;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recyclerView = findViewById(R.id.reyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void setOnItemClickListener(int pos) {
                //一般进入详情；插入
                Product p = new Product(pos, "哈哈", 10);
                DBManager.getDBInstance(MainActivity.this).insert(p);
                query(null);
            }
        });

        myAdapter.setmOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public void setOnItemLongClickListener(int pos, Product product) {
                DBManager.getDBInstance(MainActivity.this).delete(product);
                query(null);
            }
        });
    }

    //点击事件
    public void insert(View view) {
        //批量插入，一般用作缓存
        List<Product> products = new ArrayList<>();
        Product p = new Product(0, "aaaa", 20);
        products.add(p);

        Product p2 = new Product(1, "bbbbb", 20);
        products.add(p2);

        Product p3 = new Product(2, "ccc", 20);
        products.add(p3);
        Product p4 = new Product(3, "dddd", 20);
        products.add(p4);

        DBManager.getDBInstance(this).insertAll(products);
        queryData(null);

    }

    public void query(View view) {
        //查询所有
        queryData(null);
    }

    public void delete(View view) {
        Product p4=new Product(3,"ciciic",20);
        DBManager.getDBInstance(this).delete(p4);
        queryData(null);
    }

    public void update(View view) {
        Product p2 = new Product(1,"kikiki",546);
        DBManager.getDBInstance(this).update(p2);
        query(null);
    }

    private void queryData(String key) {
        List<Product> products = new DBManager(this).queryAll(key);
        refresh(products);
    }

    //刷新列表
    public void refresh(List<Product> products) {
        myAdapter.setProductList(products);
        //刷新列表
        myAdapter.notifyDataSetChanged();
    }
}
