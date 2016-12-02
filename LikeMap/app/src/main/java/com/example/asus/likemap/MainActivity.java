package com.example.asus.likemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent next = new Intent(MainActivity.this, choose.class);// ตัวนนี้คือตัวเปลี่ยนหน้า MainActivity.this คือหน้านี้ส่วน , choose.class คือหน้าที่ เมื่อคลิกแล้วจะเปลี่ยนไปตรงหน้านั้น
                startActivity(next);
            }
        });   // เมือคลิกปุ่ม start ก็จะเปลี่ยนไปหน้้า choose
    }
    public void init(){
        start = (Button)findViewById(R.id.start);
    }
}
