package com.example.iecs_1112_app_0313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iecs_1112_app_0313.DatabaseController;
import com.example.iecs_1112_app_0313.DatabaseModels.Store;
import com.example.iecs_1112_app_0313.R;

import java.util.ArrayList;
import java.util.List;

public class StoreListActivity extends AppCompatActivity {
  private ListView listView;
  private List<Store> stores;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_store_list );

    listView = findViewById( R.id.lv_store_list );

    refreshListView();

    // Set listview onclick listener
    listView.setOnItemClickListener( ( parent, view, position, id ) -> {
      Intent intent = new Intent( StoreListActivity.this, StoreEditActivity.class );
      intent.putExtra( "store_id", stores.get( position ).id );
      startActivity( intent );
    });
  }

  @Override
  protected void onRestart() {
    super.onRestart();

    refreshListView();
  }

  private void refreshListView() {
    // Get data from database
    stores = DatabaseController.db.storeDao().getAll();

    // Extract names to string list
    List<String> storeNames = new ArrayList<>();
    for ( Store store : stores ) {
      storeNames.add( store.name );
    }

    // Set listview adapter
    ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, storeNames );
    listView.setAdapter( adapter );
  }
}
