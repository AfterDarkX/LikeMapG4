package com.example.asus.likemap;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class clock extends AppCompatActivity {
    private Button setTime;
    private Button next;
    private EditText ed;
    private Info info;
    private boolean check;
    private RoomDatabase data;
    TimePicker myTimePicker;
    public static ArrayList<String> listValue;
    private ListView listAlarm;
    TimePickerDialog timePickerDialog;
    Intent c;
    final static int RQS_1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        Bundle bundle = getIntent().getExtras();
        final String building = bundle.getString("Building");
        final String room = bundle.getString("ROOM");
        final int picture = bundle.getInt("pic");
//        data.updateText(info);

        check = false;
        listAlarm = (ListView)findViewById(R.id.list);
        listValue = new ArrayList<String>();
        setTime = (Button)findViewById(R.id.set);
        next =  (Button)findViewById(R.id.next);


        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check =true;
                showTimePickerDialog(setTime);
                ed = (EditText)findViewById(R.id.setText);
                info = new Info("3",building,room,String.valueOf(picture),ed.getText().toString());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check) {
                    Intent po = new Intent(clock.this, showInfo.class);
                    po.putExtra("ROOM", info.getRoom());//ส่งค่าไปหน้า setTime มี key ชื่อว่า ROOM
                    po.putExtra("Building", info.getBuilding());//ส่งค่าไปหน้า setTime มี key ชื่อว่า Building
                    po.putExtra("pic", info.getPic());//ส่งค่าไปหน้า setTime มี key ชื่อว่า pic
                    startActivity(po);
                }else{
                    Toast.makeText(clock.this,"กรุณา ตั้งค่าเวลา",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    public class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        int callCount = 0;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if(callCount==0){
                // Do something with the time chosen by the user
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);

                setAlarm(cal);
            }
            callCount++;
        }
    }

    private void setAlarm(Calendar targetCal){

        listValue.add(targetCal.getTime()+"");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
        listAlarm.setAdapter(adapter);

        final int _id = (int) System.currentTimeMillis();

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        intent.putExtra("Text",info.getText());
        intent.putExtra("pic",info.getPic());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listValue);
        listAlarm.setAdapter(adapter);
    }
}
