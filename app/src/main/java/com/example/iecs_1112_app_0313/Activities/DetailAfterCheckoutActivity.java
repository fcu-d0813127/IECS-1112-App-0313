package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iecs_1112_app_0313.R;

public class DetailAfterCheckoutActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_detail_after_checkout );

    TextView tvTitle = findViewById( R.id.tv_detail_title );
    tvTitle.setText( "完成訂單" );

    TextView tvTotalPrice = findViewById( R.id.tv_total_price );
    tvTotalPrice.setText( "總計: " );

    TextView tvTimeTitle = findViewById( R.id.tv_detail_pickup_time_title );
    tvTimeTitle.setText( "取餐時間" );

    TextView tvTime = findViewById( R.id.tv_detail_pickup_time );
    tvTime.setText( "2023-01-01 08 : 00" );

    Button btnBackToMain = findViewById( R.id.btn_finish );
    btnBackToMain.setText( "返回主頁面" );

    View.OnClickListener listener = view -> {
      Intent intent = new Intent( DetailAfterCheckoutActivity.this, MainActivity.class );
      startActivity( intent );
    };

    btnBackToMain.setOnClickListener( listener );
  }
}
