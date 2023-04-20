package com.example.iecs_1112_app_0313;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuListViewAdapter extends BaseAdapter {
  private final Context context;
  private final List<MenuItem> menuItems;

  public MenuListViewAdapter(Context context, List<MenuItem> menuItems) {
    this.context = context;
    this.menuItems = menuItems;
  }

  @Override
  public int getCount() {
    return menuItems.size();
  }

  @Override
  public Object getItem(int i) {
    return null;
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    if (view == null) {
      view = LayoutInflater.from(context).inflate(R.layout.menu_layout, viewGroup, false);
    }

    MenuItem menuItem = menuItems.get(i);

    ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
    imageView.setImageResource(menuItem.getImageId());

    TextView foodName = (TextView) view.findViewById(R.id.tv_food_name);
    foodName.setText(menuItem.getFoodName());

    TextView foodPrice = (TextView) view.findViewById(R.id.tv_food_price);
    foodPrice.setText(String.valueOf(menuItem.getFoodPrice()));

    return view;
  }
}
