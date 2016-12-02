package com.example.asus.likemap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class chooseRoom extends AppCompatActivity {
    private Spinner list;
    private ImageView pics;
    private String word;
    private RoomDatabase data;
    private ArrayList<String> listCollection;
    private Button setTime,back,home;
    private Boolean check = false;
    public ArrayList<Integer> pic;
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_room);
        listCollection = new ArrayList<String>();
        pics = (ImageView)findViewById(R.id.pic);
        data = new RoomDatabase(this);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Cloud-Bold.otf");
        TextView myTextview = (TextView) findViewById(R.id.chooseRoom);
        myTextview.setTypeface(myTypeface);
        init();

        Bundle bundle = getIntent().getExtras();//ตัวรับค่า

        final String building = bundle.getString("Building");// รับค่าที่เป็น key จากหน้าที่แล้วที่เราส่งมาอะ

       for(Info a: data.getAllData()) {
           System.out.print(a.getRoom());
           Log.e("Room", a.getRoom());
           Log.e("Building", a.getBuilding());
           Log.e("Pic", a.getRoom());
           listCollection.add(a.getRoom());

       }
        ArrayAdapter<String> listB = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, listCollection);
        list.setAdapter(listB);
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                word = listCollection.get(i);// อันนี้ก็เหมือนว่าถ้าเรากด ตรง list ที่มันเด้งลงมาให้เราเลือกอะ เรากดตัวไหน มันก็จะเอาตัวนั้น เก็บค่าไว้ในตัวแปล word
                pics.setImageResource(pic.get(i));
                check = true;
                index = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        }
        );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextPage = new Intent(chooseRoom.this, choose.class);
                startActivity(nextPage);
                finish();
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clock = new Intent(chooseRoom.this, clock.class);
                clock.putExtra("ROOM", word);//ส่งค่าไปหน้า setTime มี key ชื่อว่า ROOM
                    clock.putExtra("Building", building);//ส่งค่าไปหน้า setTime มี key ชื่อว่า Building
                    clock.putExtra("pic", pic.get(index));//ส่งค่าไปหน้า setTime มี key ชื่อว่า pic
                startActivity(clock);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//กดปุ่ม back ก็กลับไปหน้า เริ่มต้น
                Intent po = new Intent(chooseRoom.this, MainActivity.class);
                startActivity(po);
                finish();
            }
        });



    }
    public void init(){
        list = (Spinner)findViewById(R.id.spinRoom);
        setTime = (Button)findViewById(R.id.setTime);
        back = (Button)findViewById(R.id.back);
        home = (Button)findViewById(R.id.home);
        pic = new ArrayList<Integer>();
        for(Info a: data.getAllData()){
            pic.add(a.getPic());
        }

    }
}
