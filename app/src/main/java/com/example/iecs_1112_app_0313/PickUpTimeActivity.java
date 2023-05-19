package com.example.iecs_1112_app_0313;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class PickUpTimeActivity extends AppCompatActivity {

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_pick_up_time );

    Spinner pickUpTimeOptions = findViewById( R.id.sp_time );

    ArrayList<String> timeList = generateTimeList();

    ArrayAdapter<String> adapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, timeList );
    pickUpTimeOptions.setAdapter( adapter );

    Button btnNext = findViewById( R.id.btn_pick_up_time_next );

    View.OnClickListener listener = view -> {
      Intent intent = new Intent( PickUpTimeActivity.this, DetailBeforeCheckoutActivity.class );
      startActivity( intent );
    };

    btnNext.setOnClickListener( listener );
  }

  private ArrayList<String> generateTimeList() {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get( Calendar.HOUR_OF_DAY );
    int minute = calendar.get( Calendar.MINUTE );

    ArrayList<String> timeList = new ArrayList<>();

    for ( int i = 0; i < 6; ++i ) {
      minute += 10;
      if ( minute >= 60 ) {
        hour++;
        minute -= 60;
      }
      String time = String.format( "%02d:%02d", hour, minute );
      timeList.add( time );
    }

    return timeList;
  }
}
