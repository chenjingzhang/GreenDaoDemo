package com.dxt2.greendaoo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2018/4/24 0024.
 */
@Entity
public class Product {
    //表示主键，而且自动增长,备注：主键必须是long类型,autoincrement自动增长无效
    @Id(autoincrement = false)
    long id;
    String name;
    int price;

    @Generated(hash = 358248395)
    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
