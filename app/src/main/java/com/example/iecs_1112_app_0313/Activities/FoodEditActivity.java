package com.example.iecs_1112_app_0313.Activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iecs_1112_app_0313.Adapters.MenuListViewAdapter;
import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.R;

import java.util.List;

public class FoodEditActivity extends AppCompatActivity {
  private ListView menuListView;
  private int storeId;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_food_edit );

    storeId = getIntent().getIntExtra( "store_id", 0 );

    menuListView = findViewById( R.id.lv_food_edit_list );

    refreshListView();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    refreshListView();
  }

  private void refreshListView() {
    List<Product> products = DatabaseController.db.productDao().getAll( storeId );
    menuListView.setAdapter( new MenuListViewAdapter( this, products ) );
  }
}
