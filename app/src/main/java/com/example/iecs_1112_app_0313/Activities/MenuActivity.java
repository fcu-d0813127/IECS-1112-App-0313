package com.example.iecs_1112_app_0313.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iecs_1112_app_0313.Adapters.MenuListViewAdapter;
import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.R;

import java.util.List;

public class MenuActivity extends AppCompatActivity {
  private String storeName;
  private ListView menuListView;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_menu );

    menuListView = findViewById( R.id.lv_menu_list );
    Button btnShoppingCart = findViewById( R.id.btn_next );
    btnShoppingCart.setText( "購物車" );

    // 獲取使用者點擊的餐廳名稱並更改餐廳詳細頁面中的餐廳名稱
    Intent intent = getIntent();
    storeName = intent.getStringExtra( "restaurant_name" );
    TextView restaurantName = findViewById( R.id.tv_title_name );
    restaurantName.setText( storeName );

    refreshListView();

    View.OnClickListener listener = view -> {
      Intent shoppingCartIntent = new Intent( MenuActivity.this, ShoppingCartActivity.class );
      startActivity( shoppingCartIntent );
    };

    btnShoppingCart.setOnClickListener( listener );
  }

  @Override
  public boolean onCreateOptionsMenu( Menu menu ) {
    getMenuInflater().inflate( R.menu.menu_main, menu );
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
    int id = item.getItemId();

    if ( id == R.id.menu_add_food) {
      Intent intent = new Intent( MenuActivity.this, FoodAddActivity.class );
      int storeId = DatabaseController.db.storeDao().findByName( storeName ).get( 0 ).id;
      intent.putExtra( "store_id", storeId );
      startActivity( intent );
    } else if ( id == R.id.menu_edit_food) {
      Intent intent = new Intent( MenuActivity.this, FoodEditActivity.class );
      int storeId = DatabaseController.db.storeDao().findByName( storeName ).get( 0 ).id;
      intent.putExtra( "store_id", storeId );
      startActivity( intent );
    }

    return true;
  }

  @Override
  public void onRestart() {
    super.onRestart();

    refreshListView();
  }

  private void refreshListView() {
    // 從資料庫獲取所有資料
    int storeId = DatabaseController.db.storeDao().findByName( storeName ).get( 0 ).id;
    List<Product> products = DatabaseController.db.productDao().getAll( storeId );

    menuListView.setAdapter( new MenuListViewAdapter( this, products ) );
  }
}
