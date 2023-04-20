package com.example.iecs_1112_app_0313;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantGridViewAdapter extends BaseAdapter {
  Context context;
  Restaurant[] restaurants;
  LayoutInflater inflater;

  public RestaurantGridViewAdapter(Context context, Restaurant[] restaurants) {
    this.context = context;
    this.restaurants = restaurants;
    this.inflater = LayoutInflater.from(this.context);
  }

  @Override
  public int getCount() {
    return restaurants.length;
  }

  @Override
  public Object getItem(int i) {
    return restaurants[i];
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View convert_view, ViewGroup parent) {
    View view = convert_view;
    if ( view == null ) {
      view = LayoutInflater.from(context).inflate(R.layout.restaurant_layout, parent, false);
    }

    Restaurant restaurant = restaurants[i];
    TextView restaurant_name = view.findViewById(R.id.restaurant_name);
    ImageView restaurant_logo = view.findViewById(R.id.restaurant_logo);

    restaurant_logo.setImageResource(restaurant.logo_id);
    restaurant_logo.setScaleType(ImageView.ScaleType.CENTER_CROP);

    restaurant_name.setText(restaurant.name);

    // 為每個物件添加點擊事件並包裝被點擊的餐廳名稱
    view.setOnClickListener(v -> {
      Intent intent = new Intent(context, MenuActivity.class);
      intent.putExtra("restaurant_name", restaurant.name);
      context.startActivity(intent);
    });

    return view;
  }
}
