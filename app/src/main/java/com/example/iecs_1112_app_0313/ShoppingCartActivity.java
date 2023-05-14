package com.example.iecs_1112_app_0313;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_shopping_cart );

    TextView tvShoppingCart = findViewById( R.id.tv_title_name );
    Button btnNext = findViewById( R.id.btn_next );

    tvShoppingCart.setText( "購物車" );
    btnNext.setText( "下一步" );

    List<MenuItem> menuItems = new ArrayList<>();
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );
    menuItems.add( new MenuItem( R.drawable.ic_launcher_background, "Cart", 100 ) );

    ListView menuListView = findViewById( R.id.lv_menu_list );
    menuListView.setAdapter( new MenuListViewAdapter( this, menuItems ) );

    View.OnClickListener listener = view -> {
      Intent intent = new Intent( ShoppingCartActivity.this, PickUpTimeActivity.class );
      startActivity( intent );
    };

    btnNext.setOnClickListener( listener );
  }
}
