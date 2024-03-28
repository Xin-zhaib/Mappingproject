package com.example.datiqiapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.datiqiapp.R;
/**
 * 首页界面
 */
public class DaohangActivity extends AppCompatActivity {
    private TextView teacher;//老师登录
    private TextView student;//学生登录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daohang);
        teacher=findViewById(R.id.teacher);
        student=findViewById(R.id.student);

        //给学生和教师入口设置点击监听器在点击时启动登录界面（DengluActivity）。
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DaohangActivity.this,DengluActivity.class);
                startActivity(intent);
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DaohangActivity.this,DengluActivity.class);
                startActivity(intent);
            }
        });
    }
}