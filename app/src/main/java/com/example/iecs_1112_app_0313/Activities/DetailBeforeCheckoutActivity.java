package com.example.iecs_1112_app_0313.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iecs_1112_app_0313.Adapters.ShoppingCartListViewAdapter;
import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Order;
import com.example.iecs_1112_app_0313.MenuItem;
import com.example.iecs_1112_app_0313.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailBeforeCheckoutActivity extends AppCompatActivity {
  private PopupWindow doubleCheckWindow = null;
  private String time;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_detail_before_checkout );

    time = getIntent().getStringExtra( "time" );

    ListView listView = findViewById( R.id.lv_food_details_list );
    List<MenuItem> menuItems = MenuItem.ShoppingCart;
    listView.setAdapter( new ShoppingCartListViewAdapter( this, menuItems ) );

    TextView tvTitle = findViewById( R.id.tv_detail_title );
    tvTitle.setText( "確認餐點" );

    TextView tvTotalPrice = findViewById( R.id.tv_total_price );
    // Calculate total price
    int totalPrice = 0;
    for ( MenuItem menuItem : menuItems ) {
      totalPrice += menuItem.getProduct().price * menuItem.getNumber();
    }
    tvTotalPrice.setText( "總計: " + totalPrice + " 元" );

    TextView tvTimeTitle = findViewById( R.id.tv_detail_pickup_time_title );
    tvTimeTitle.setText( "" );

    TextView tvTime = findViewById( R.id.tv_detail_pickup_time );
    tvTime.setText( "" );

    Button btnCheckout = findViewById( R.id.btn_finish );
    btnCheckout.setText( "結帳" );

    View.OnClickListener listener = view -> initPopupWindow();

    btnCheckout.setOnClickListener( listener );
  }

  private void initPopupWindow() {
    if ( doubleCheckWindow != null ) {
      return;
    }

    View view = LayoutInflater.from( this ).inflate( R.layout.double_check_window_layout, null );
    doubleCheckWindow = new PopupWindow( view );

    // 設定視窗大小與位置
    doubleCheckWindow.setHeight( ViewGroup.LayoutParams.WRAP_CONTENT );
    doubleCheckWindow.setWidth( ViewGroup.LayoutParams.WRAP_CONTENT );
    doubleCheckWindow.showAtLocation( view, Gravity.CENTER_HORIZONTAL, 0, 0 );

    View.OnClickListener listener = view1 -> {
      if ( view1.getId() == R.id.btn_check_cancel ) {
        doubleCheckWindow.dismiss();
      } else if ( view1.getId() == R.id.btn_check_confirm ) {
        // Generate order id
        int id = DatabaseController.db.orderDao().getMaxOrderId() + 1;
        System.out.println( "id: " + id );

        // Create time string
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get( Calendar.YEAR );
        int month = calendar.get( Calendar.MONTH ) + 1;
        int day = calendar.get( Calendar.DAY_OF_MONTH );
        String timeString = year + "-" + month + "-" + day + " " + time;

        // Create order list
        List<Order> orders = new ArrayList<>();
        for ( MenuItem menuItem : MenuItem.ShoppingCart ) {
          orders.add( new Order( id, menuItem.getProduct().id, menuItem.getNumber(), timeString ) );
        }

        // Insert orders into database
        for ( Order order : orders ) {
          DatabaseController.db.orderDao().insert( order );
        }

        // Clear shopping cart
        MenuItem.ShoppingCart.clear();

        Intent intent = new Intent( DetailBeforeCheckoutActivity.this, DetailAfterCheckoutActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra( "orderId", id );
        startActivity( intent );
        finish();
        doubleCheckWindow.dismiss();
      }
      doubleCheckWindow = null;
    };

    Button btnCancel = view.findViewById( R.id.btn_check_cancel );
    Button btnConfirm = view.findViewById( R.id.btn_check_confirm );

    btnCancel.setOnClickListener( listener );
    btnConfirm.setOnClickListener( listener );
  }
}
