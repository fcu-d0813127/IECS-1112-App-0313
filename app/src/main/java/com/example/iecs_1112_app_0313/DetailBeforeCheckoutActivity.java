package com.example.iecs_1112_app_0313;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailBeforeCheckoutActivity extends AppCompatActivity {
  private Context selfContext;
  private PopupWindow doubleCheckWindow = null;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_detail_before_checkout );

    selfContext = this;

    TextView tvTitle = findViewById( R.id.tv_detail_title );
    tvTitle.setText( "確認餐點" );

    TextView tvTotalPrice = findViewById( R.id.tv_total_price );
    tvTotalPrice.setText( "總計: " );

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
        Intent intent = new Intent( DetailBeforeCheckoutActivity.this, DetailAfterCheckoutActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
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
