package com.example.iecs_1112_app_0313;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu);

    // 獲取使用者點擊的餐廳名稱並更改餐廳詳細頁面中的餐廳名稱
    Intent intent = getIntent();
    TextView restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
    restaurantName.setText(intent.getStringExtra("restaurant_name"));

    List<MenuItem> menuItems = new ArrayList<>();
    menuItems.add(new MenuItem(R.drawable.ic_launcher_background, "Home", 100));
    menuItems.add(new MenuItem(R.drawable.ic_launcher_background, "Home", 100));
    menuItems.add(new MenuItem(R.drawable.ic_launcher_background, "Home", 100));

    ListView menuListView = (ListView) findViewById(R.id.lv_menu_list);
    menuListView.setAdapter(new MenuListViewAdapter(this, menuItems));
  }
}
