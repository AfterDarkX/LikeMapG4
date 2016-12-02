package com.example.asus.likemap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class showInfo extends AppCompatActivity {
    private ImageView pic;
    private TextView building,room;
    private Button home, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Cloud-Bold.otf");
        TextView myTextview1 = (TextView) findViewById(R.id.room);
        myTextview1.setTypeface(myTypeface);
        TextView myTextview2 = (TextView) findViewById(R.id.Building);
        myTextview2.setTypeface(myTypeface);
        init();

        Bundle bundle = getIntent().getExtras();
        String building = bundle.getString("Building");
        String room = bundle.getString("ROOM");
        int picture = bundle.getInt("pic");

        this.room.setText("ห้อง : "+room);// นี่ก็คือset ตัวหนังสือ ตามที่ได้เลือกไว้หน้า เลือกห้อง
        this.building.setText("อาคาร : "+building);//นี่ก็คือ set ตัวหนังสือ ตามที่ได้เลือกไว้หน้า เลือกอาคาร
        pic.setImageResource(picture);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //กดปุ่ม back ก็กลับไปหน้า เลือกอาคารเพื่อเลือกใหม่
                Intent nextPage = new Intent(showInfo.this, choose.class);
                startActivity(nextPage);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//กดปุ่ม back ก็กลับไปหน้า เริ่มต้น
                Intent po = new Intent(showInfo.this, MainActivity.class);
                startActivity(po);
            }
        });


    }
    public void init(){
        building = (TextView)findViewById(R.id.Building);
        room = (TextView)findViewById(R.id.room);
        pic = (ImageView)findViewById(R.id.picture);
        home = (Button)findViewById(R.id.home);
        back = (Button)findViewById(R.id.back);
    }
}
