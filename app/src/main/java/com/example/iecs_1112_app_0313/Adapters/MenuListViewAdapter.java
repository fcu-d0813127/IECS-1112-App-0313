package com.example.iecs_1112_app_0313.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.iecs_1112_app_0313.Activities.FoodDetailActivity;
import com.example.iecs_1112_app_0313.DatabaseModels.Product;
import com.example.iecs_1112_app_0313.MenuItem;
import com.example.iecs_1112_app_0313.R;

import java.util.List;

public class MenuListViewAdapter extends BaseAdapter {
  private final Context context;
  private final List<Product> products;
  private PopupWindow foodPopupWindow = null;

  public MenuListViewAdapter( Context context, List<Product> products ) {
    this.context = context;
    this.products = products;
  }

  @Override
  public int getCount() {
    return products.size();
  }

  @Override
  public Object getItem( int i ) {
    return null;
  }

  @Override
  public long getItemId( int i ) {
    return 0;
  }

  @Override
  public View getView( int i, View view, ViewGroup viewGroup ) {
    if ( view == null ) {
      view = LayoutInflater.from( context ).inflate( R.layout.menu_layout, viewGroup, false );
    }

    Product product = products.get( i );

    ImageView imageView = view.findViewById( R.id.imageView );
//    imageView.setImageResource( menuItem.getImageId() );

    TextView foodName = view.findViewById( R.id.tv_food_name );
    foodName.setText( product.name );

    TextView foodPrice = view.findViewById( R.id.tv_food_price );
    foodPrice.setText( String.valueOf( product.price ) );

    view.setOnClickListener( v -> {
      if ( context.getClass().getSimpleName().equals( "MenuActivity" ) ) {
        initPopupWindow( product );
      } else if ( context.getClass().getSimpleName().equals( "FoodEditActivity" ) ) {
        Intent intent = new Intent( context, FoodDetailActivity.class );
        intent.putExtra( "foodName", product.name );
        intent.putExtra( "foodDescription", "Test" );
        intent.putExtra( "foodPrice", String.valueOf( product.price ) );
        context.startActivity( intent );
      }
    });

    return view;
  }

  private void initPopupWindow( Product product ) {
    if ( foodPopupWindow != null ) {
      return;
    }

    View view = LayoutInflater.from( context ).inflate( R.layout.food_popup_window_layout, null );
    foodPopupWindow = new PopupWindow( view );

    TextView foodName = view.findViewById( R.id.tv_popup_food_name );
    foodName.setText( product.name );

    ImageView foodImage = view.findViewById( R.id.iv_popup_food_image );
//    foodImage.setImageResource( menuItem.getImageId() );

    // 設定視窗大小與位置
    foodPopupWindow.setHeight( ViewGroup.LayoutParams.WRAP_CONTENT );
    foodPopupWindow.setWidth( ViewGroup.LayoutParams.WRAP_CONTENT );
    foodPopupWindow.showAtLocation( view, Gravity.CENTER_HORIZONTAL, 0, 0 );

    TextView tvNumber = view.findViewById( R.id.tv_popup_number );

    View.OnClickListener listener = v -> {
      int number = Integer.parseInt( tvNumber.getText().toString() );

      if ( v.getId() == R.id.btn_popup_add ) {
        number++;
      } else if ( v.getId() == R.id.btn_popup_sub && number > 1 ) {
        number--;
      } else if ( v.getId() == R.id.btn_popup_confirm ) {
        foodPopupWindow.dismiss();
        foodPopupWindow = null;
      } else if ( v.getId() == R.id.btn_popup_cancel ) {
        foodPopupWindow.dismiss();
        foodPopupWindow = null;
      }

      tvNumber.setText( String.valueOf( number ) );
    };

    Button btnAdd = view.findViewById( R.id.btn_popup_add );
    Button btnSub = view.findViewById( R.id.btn_popup_sub );
    Button btnConfirm = view.findViewById( R.id.btn_popup_confirm );
    Button btnCancel = view.findViewById( R.id.btn_popup_cancel );

    btnAdd.setOnClickListener( listener );
    btnSub.setOnClickListener( listener );
    btnConfirm.setOnClickListener( listener );
    btnCancel.setOnClickListener( listener );
  }
}
