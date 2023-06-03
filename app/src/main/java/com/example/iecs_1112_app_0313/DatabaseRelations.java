package com.example.iecs_1112_app_0313;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.iecs_1112_app_0313.DatabaseModels.Order;
import com.example.iecs_1112_app_0313.DatabaseModels.OrderDao;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.DatabaseModels.ProductDao;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;
import com.example.iecs_1112_app_0313.DatabaseModels.StoreDao;

@Database( version = 1, entities = { Store.class, Product.class, Order.class } )
public abstract class DatabaseRelations extends RoomDatabase {
  abstract public StoreDao storeDao();
  abstract public ProductDao productDao();
  abstract public OrderDao orderDao();
}
