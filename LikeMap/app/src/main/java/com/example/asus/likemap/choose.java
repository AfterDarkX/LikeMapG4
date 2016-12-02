package com.example.asus.likemap;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class choose extends AppCompatActivity {
  //  private Spinner list;
    private String word;
    private RadioButton br;
    private TextView text;
    private String[] listCollection;
    private Button next;
    private Intent nextPage;
    boolean check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        nextPage = new Intent(choose.this, chooseRoom.class);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Cloud-Bold.otf");// setfont
        TextView myTextview = (TextView) findViewById(R.id.choose);// setFont  R.id.choose คือ ตัวหนังสือ "เลือกอาคาร"
        myTextview.setTypeface(myTypeface);// set font
        check = false;
        init(); // เมทตอด ที่สร้างขึ้น เมตตอดอยู่ข้างล่าง

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check) {
                    nextPage = new Intent(choose.this, chooseRoom.class);// เมื่อกด ปุ่ม next มันก็จะเปลี่นนหน้าไป chooRoom.class(หน้าเลือกห้อง)
                    nextPage.putExtra("Building", word);// นี่คือส่งค่าที่ชื่อ word ไปหน้าถัดไป โดยให้ key ตือ Building( บอก อ.แบบนี้ได้เลยนะถ้าถามว่าคือไร)
                    startActivity(nextPage);// เริ่ม action   ของ nextpage
                }else{
                    Toast.makeText(choose.this,"กรุณาเลือก อาคาร",Toast.LENGTH_LONG).show();
                }
            }
        });

        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                br.setChecked(true);
                word = br.getText().toString();
                nextPage.putExtra("Building", word);
                check = true;
            }
        });
    }
    public void init(){// นี่คือประกาศตัวแปรต่างๆ จากด้านบนลองไล่ดูว่า มันประกาศอยู่
       // list = (Spinner)findViewById(R.id.list);// spinner คือตัวที่เรากด แล้วมันแสดงรายการ
        text = (TextView)findViewById(R.id.choose);// text คือตัวหนังสือ "เลือกอาคาร"
        next = (Button)findViewById(R.id.next);// กำหนดค่าให้ ปุ่ม nex;t ใส่ใน ตัวแปล next
        br = (RadioButton)findViewById(R.id.check);
    }
}
