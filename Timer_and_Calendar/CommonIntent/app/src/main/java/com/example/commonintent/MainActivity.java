package com.example.commonintent;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.EditText;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alarmClockActivate(View view) {
        EditText msg = (EditText) findViewById(R.id.messageInput);
        EditText sec = (EditText) findViewById(R.id.secondInput) ;
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, msg.getText().toString())
                .putExtra(AlarmClock.EXTRA_LENGTH, Integer.parseInt(sec.getText().toString()))
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calendarActivate(View view) {
        EditText title = (EditText) findViewById(R.id.titleInput);
        EditText location = (EditText) findViewById(R.id.locationInput);
        EditText begin = (EditText) findViewById(R.id.beginInput);
        String s = begin.getText().toString();
        String[] getDate = s.split("/");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, Integer.parseInt(getDate[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(getDate[1]) - 1);
        cal.set(Calendar.YEAR, Integer.parseInt(getDate[2]));
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 0);

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title.getText().toString())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString())
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTime().getTime())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTime().getTime())
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}