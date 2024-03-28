package com.example.datiqiapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datiqiapp.R;
import com.example.datiqiapp.Sqlite.UserDBUtils;
import com.example.datiqiapp.bean.User;
/**
 * 学生信息界面
 */
public class XueshengActivity extends AppCompatActivity {
EditText xueshengname;
EditText xuehao;
TextView commit_xuesheng;
    private ImageView ivback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuesheng);
        xueshengname=findViewById(R.id.xueshengname);
        xuehao=findViewById(R.id.xuehao);
        ivback =findViewById(R.id.iv_back);
        commit_xuesheng=findViewById(R.id.commit_xuesheng);


        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //提交按钮commit_xuesheng点击时将输入的学生信息封装到User对象中
        // 通过调用UserDBUtils对数据库进行修改操作。
        // 同时将保存操作的结果通过Toast弹窗显示，提示用户成功或者失败
        commit_xuesheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setUsername(xueshengname.getText().toString());
                user.setUserpwd(xuehao.getText().toString());

                int i= UserDBUtils.getInstance(getApplicationContext()).saveUser(user);
                if(i==1){
                    Toast.makeText(XueshengActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(XueshengActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}