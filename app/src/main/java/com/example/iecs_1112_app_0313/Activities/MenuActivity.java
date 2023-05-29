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
import com.example.iecs_1112_app_0313.MenuItem;
import com.example.iecs_1112_app_0313.R;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_menu );

    Button btnShoppingCart = findViewById( R.id.btn_next );
    btnShoppingCart.setText( "購物車" );

    // 獲取使用者點擊的餐廳名稱並更改餐廳詳細頁面中的餐廳名稱
    Intent intent = getIntent();
    TextView restaurantName = findViewById( R.id.tv_title_name );
    restaurantName.setText( intent.getStringExtra( "restaurant_name" ) );

    List<MenuItem> menuItems = new ArrayList<>();
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Home", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Home", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Home", 100 ) );

    ListView menuListView = findViewById( R.id.lv_menu_list );
    menuListView.setAdapter( new MenuListViewAdapter( this, menuItems ) );

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
      startActivity( intent );
    } else if ( id == R.id.menu_edit_food) {
      Intent intent = new Intent( MenuActivity.this, FoodEditActivity.class );
      startActivity( intent );
    }

    return true;
  }
}
