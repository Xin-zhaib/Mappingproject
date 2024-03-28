package com.example.datiqiapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.datiqiapp.R;
/**
 * 教师主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView addtext;//添加题目
//    private TextView checktext;//查看题库
    private TextView checkchengji;//查看成绩
    private TextView managerxuesheng;//管理学生
    private TextView lianxiti;//练习题目
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addtext=findViewById(R.id.addtext);
//        checktext=findViewById(R.id.checktext);
//        checkchengji=findViewById(R.id.checkchengji);
//        lianxiti=findViewById(R.id.lianxiti);
        managerxuesheng=findViewById(R.id.managerxuesheng);
        addtext.setOnClickListener(this);
//        lianxiti.setOnClickListener(this);
//        checktext.setOnClickListener(this);
//        checkchengji.setOnClickListener(this);
        managerxuesheng.setOnClickListener(this);
    }

    //设置监听器，当点击时跳转到相应的界面
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addtext:
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                break;
//            case R.id.checktext:
//                Intent intent1=new Intent(MainActivity.this,TimuActivity.class);
//                startActivity(intent1);
//                break;
//            case R.id.lianxiti:
//                Intent intent5=new Intent(MainActivity.this,ShijuanActivity.class);
//                startActivity(intent5);
//                break;
//            case R.id.checkchengji:
//                Intent intent2=new Intent(MainActivity.this,ChengjiActivity.class);
//                startActivity(intent2);
//                break;
            case R.id.managerxuesheng:
                Intent intent3=new Intent(MainActivity.this,XueshengActivity.class);
                startActivity(intent3);
                break;
        }
    }
}