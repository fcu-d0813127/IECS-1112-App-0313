package com.example.iecs_1112_app_0313.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.R;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_order_history );

    ListView lvOrderHistoryList = findViewById( R.id.lv_order_history_list );

    // Get orders from database
    int maxOrderId = DatabaseController.db.orderDao().getMaxOrderId();
    List<String> times = new ArrayList<>();
    for ( int i = 1; i <= maxOrderId; ++i ) {
      times.add( DatabaseController.db.orderDao().getById( i ).get( 0 ).pick_up_time );
    }

    // Create adapter
    ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, times );

    // Set adapter
    lvOrderHistoryList.setAdapter( adapter );

    // Set list view item click listener
    lvOrderHistoryList.setOnItemClickListener( ( adapterView, view, i, l ) -> {
      Intent intent = new Intent( OrderHistoryActivity.this, DetailAfterCheckoutActivity.class );
      intent.putExtra( "orderId", i + 1 );
      startActivity( intent );
    });
  }
}
