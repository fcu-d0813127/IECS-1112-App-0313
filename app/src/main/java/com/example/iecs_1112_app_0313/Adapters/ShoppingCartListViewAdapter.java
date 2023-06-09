package com.example.iecs_1112_app_0313.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iecs_1112_app_0313.ImageManagement;
import com.example.iecs_1112_app_0313.MenuItem;
import com.example.iecs_1112_app_0313.R;

import java.util.List;

public class ShoppingCartListViewAdapter extends BaseAdapter {
  private Context context;
  private List<MenuItem> menuItems;

  public ShoppingCartListViewAdapter( Context context, List<MenuItem> menuItems ) {
    this.context = context;
    this.menuItems = menuItems;
  }

  @Override
  public int getCount() {
    return menuItems.size();
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
      view = LayoutInflater.from( context ).inflate( R.layout.shopping_cart_list_layout, viewGroup, false );
    }

    MenuItem menuItem = menuItems.get( i );

    ImageView imageView = view.findViewById( R.id.iv_shopping_cart_icon );
    Bitmap bitmap = ImageManagement.loadImage( menuItem.getProduct().image_path );
    imageView.setImageBitmap( bitmap );

    TextView foodName = view.findViewById( R.id.tv_shopping_cart_food_name );
    foodName.setText( menuItem.getProduct().name );

    TextView foodPrice = view.findViewById( R.id.tv_shopping_cart_price );
    foodPrice.setText( String.valueOf( menuItem.getProduct().price ) );

    TextView foodNumber = view.findViewById( R.id.tv_shopping_cart_number );
    foodNumber.setText( String.valueOf( menuItem.getNumber() ) );

    return view;
  }
}
