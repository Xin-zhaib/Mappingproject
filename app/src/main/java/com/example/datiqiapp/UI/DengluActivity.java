package com.example.datiqiapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datiqiapp.R;
import com.example.datiqiapp.Sqlite.UserDBUtils;
import com.example.datiqiapp.utils.utils1;
/**
 * 登录界面
 */
public class DengluActivity extends AppCompatActivity  {
    EditText etPhone;//名输入框
    EditText etPwd;//密码输入框
    TextView tvLogin;//登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        etPhone=findViewById(R.id.user);
        etPwd=findViewById(R.id.mima);
        tvLogin=findViewById(R.id.login);


        //给登录按钮设置点击监听器，在点击时判断用户填入的名和密码是否正确
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果为管理员账号"admin"和固定密码"123"则启动MainActivity
                    if(etPwd.getText().toString().equals("123")&&etPhone.getText().toString().equals("admin")){
                        startActivity(new Intent(DengluActivity.this,MainActivity.class));
                    }else {//否则使用UserDBUtils查询用户表中是否存在对应名和密码
                        utils1.NAME = etPwd.getText().toString();
                    int i = UserDBUtils.getInstance(getApplicationContext()).Quer(etPhone.getText().toString(),etPwd.getText().toString());
                    if (i == 1){// 如果查询结果为1则登录成功并进入试题页面（ShijuanActivity），否则提示登录失败
                        startActivity(new Intent(DengluActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}