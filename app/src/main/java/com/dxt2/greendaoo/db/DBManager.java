package com.dxt2.greendaoo.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.dxt2.greendaoo.bean.DaoMaster;
import com.dxt2.greendaoo.bean.DaoSession;
import com.dxt2.greendaoo.bean.Product;
import com.dxt2.greendaoo.bean.ProductDao;
import com.dxt2.greendaoo.bean.StudentDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class DBManager {
    ProductDao productDao;
    private static DBManager dbManager = null;

    public static DBManager getDBInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    public DBManager(Context context) {
        //参数2:数据库的名称
        DaoSession daoSession = DaoMaster.newDevSession(context, "1122");
        //获取实体类对应的表
        StudentDao studentDao = daoSession.getStudentDao();
        productDao = daoSession.getProductDao();
    }

    //单条插入
    public void insert(Product p) {
        productDao.insertOrReplace(p);
    }

    public void insertAll(List<Product> list) {
        productDao.insertOrReplaceInTx(list);
    }

    //删除单条数据
    public void delete(Product p) {
        productDao.delete(p);
    }

    //删除所有
    public void deleteAll() {
        productDao.deleteAll();
    }

    public void update(Product p) {
        productDao.update(p);
    }

    public void queryAll() {
        //按条件查询QueryBuilder，建造者模式
        QueryBuilder<Product> builder = productDao.queryBuilder();
        Query<Product> query = builder.build();
        List<Product> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            Log.e("=====", "===getId===" + list.get(i).getId());
            Log.e("=====", "==getName====" + list.get(i).getName());
            Log.e("=====", "===getPrice===" + list.get(i).getPrice());
        }
    }

    public void queryAll(int offset, int limit) {
        //模拟分页
        //offset偏移量，limit()限制查询的条数，
        //offset(m).limit(n)从第m个数开始查询，查询n条数据

        QueryBuilder<Product> builder = productDao.queryBuilder();
        Query<Product> query = builder.offset(10).limit(10).build();
        List<Product> list = query.list();
        for (int i = 0; i < list.size(); i++) {
            Log.e("=====", "===getId===" + list.get(i).getId());
            Log.e("=====", "==getName====" + list.get(i).getName());
            Log.e("=====", "===getPrice===" + list.get(i).getPrice());
        }

    }

    //模糊查询
    //
    public List<Product> queryAll(String key) {
        //模糊查询 "where name  like %N%"
        QueryBuilder<Product> builder = productDao.queryBuilder();
        Query<Product> query = null;
        if (!TextUtils.isEmpty(key)) {
            //按条件查询
            query = builder
                    .where(ProductDao.Properties.Name.like("%" + key + "%"))
                    .build();
        } else {
            //查询所有
            query = builder.build();
        }
        List<Product> list = query.list();
        return list;
    }
}

























