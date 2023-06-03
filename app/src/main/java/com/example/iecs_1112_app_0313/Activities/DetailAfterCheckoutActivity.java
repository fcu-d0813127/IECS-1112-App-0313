package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iecs_1112_app_0313.Adapters.ShoppingCartListViewAdapter;
import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Order;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.MenuItem;
import com.example.iecs_1112_app_0313.R;

import java.util.ArrayList;
import java.util.List;

public class DetailAfterCheckoutActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_detail_after_checkout );

    // Get order id
    int orderId = getIntent().getIntExtra( "orderId", -1 );

    ListView listView = findViewById( R.id.lv_food_details_list );

    // Get order
    List<Order> orderList = DatabaseController.db.orderDao().getAll( orderId );

    // Generate menu item list
    List<MenuItem> menuItems = new ArrayList<>();
    for ( Order order : orderList ) {
      Product product = DatabaseController.db.productDao().getById( order.food_id );
      menuItems.add( new MenuItem( product, order.quantity ) );
    }

    // Set adapter
    listView.setAdapter( new ShoppingCartListViewAdapter( this, menuItems ) );

    TextView tvTitle = findViewById( R.id.tv_detail_title );
    tvTitle.setText( "完成訂單" );

    TextView tvTotalPrice = findViewById( R.id.tv_total_price );
    // Calculate total price
    int totalPrice = 0;
    for ( Order order : orderList ) {
      int price = DatabaseController.db.productDao().getById( order.food_id ).price;
      totalPrice += price * order.quantity;
    }
    tvTotalPrice.setText( "總計: " + totalPrice + " 元" );

    TextView tvTimeTitle = findViewById( R.id.tv_detail_pickup_time_title );
    tvTimeTitle.setText( "取餐時間" );

    TextView tvTime = findViewById( R.id.tv_detail_pickup_time );
    tvTime.setText( orderList.get( 0 ).pick_up_time );

    Button btnBackToMain = findViewById( R.id.btn_finish );
    btnBackToMain.setText( "返回主頁面" );

    View.OnClickListener listener = view -> {
      Intent intent = new Intent( DetailAfterCheckoutActivity.this, MainActivity.class );
      startActivity( intent );
    };

    btnBackToMain.setOnClickListener( listener );
  }
}
