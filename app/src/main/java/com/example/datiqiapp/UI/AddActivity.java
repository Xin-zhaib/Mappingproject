package com.example.datiqiapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datiqiapp.R;
import com.example.datiqiapp.Sqlite.DanxuanDbutils;
import com.example.datiqiapp.Sqlite.PanduanDbutils;
import com.example.datiqiapp.Sqlite.TiankongDbutils;
import com.example.datiqiapp.bean.Question;

import java.util.Random;

/**
 * 添加题目界面
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
   private EditText etdanxuan;
   private EditText etpanduan;
    private EditText etjianda;
    private EditText etdanxuan1;
    private EditText etpanduan1;
    private EditText etjianda1;
    private TextView commitdanxuan;
    private TextView commitpanduan;
    private TextView commitjianda;
    private ImageView ivback;//返回键

    //重写父类的onCreate方法，完成界面初始化等操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etdanxuan=findViewById(R.id.et_danxuan);
        etpanduan=findViewById(R.id.et_panduan);
        etjianda=findViewById(R.id.et_jianda);
        etdanxuan1=findViewById(R.id.et_danxuan1);
        etpanduan1=findViewById(R.id.et_panduan1);
        etjianda1=findViewById(R.id.et_jianda1);
        ivback =findViewById(R.id.iv_back);
        commitdanxuan=findViewById(R.id.commit_danxuan);
        commitpanduan=findViewById(R.id.commit_panduan);
        commitjianda=findViewById(R.id.commit_tiankong);
        commitdanxuan.setOnClickListener(this);
        commitpanduan.setOnClickListener(this);
        commitjianda.setOnClickListener(this);
        ivback.setOnClickListener(this);
    }


    //根据布局文件初始化各个控件，并在commitdanxuan、commitpanduan、commitjianda和ivback上设置OnClickListener监听器
    //如果提交了单选题，创建一个Question对象，设置类型为“单选题”，并将问题（etdanxuan）和答案（etdanxuan1）封装到该对象中。
    // 调用DanxuanDbutils.getInstance().insert()方法将该对象插入到数据库中，并在Toast中提示用户添加成功。
    // 如果提交了判断题或填空题，则进行类似的操作。如果单击返回按钮，则调用finish()方法关闭当前界面。
    @Override
    public void onClick(View v) {
        Question question=new Question();
        switch (v.getId()){
            case R.id.commit_danxuan:
                question.setLeixing("单选题");
                question.setDaan(etdanxuan1.getText().toString());
                question.setWenti(etdanxuan.getText().toString());
                DanxuanDbutils.getInstance(getApplicationContext()).insert(question);
                etdanxuan1.setText("");
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.commit_panduan:
                question.setLeixing("判断题");
                question.setDaan(etpanduan1.getText().toString());
                question.setWenti(etpanduan.getText().toString());
                PanduanDbutils.getInstance(getApplicationContext()).insert(question);
                etpanduan1.setText("");
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.commit_tiankong:
                question.setLeixing("Test three");
                String inputText = etjianda1.getText().toString().toLowerCase(); // 在清空之前获取输入文本
                question.setDaan(inputText);
                question.setWenti(etjianda.getText().toString());
                TiankongDbutils.getInstance(getApplicationContext()).insert(question);
                etjianda1.setText(""); // 清空输入框

                String[] resultYes = new String[]{"ENTJ", "ENTP", "ENFJ", "ENFP", "ESTJ"};
                String[] resultNo = new String[]{"INTJ", "INTP", "INFJ", "INFP", "ISTJ", "ISTP", "ISFP"};
                Random random = new Random();

                if (inputText.equals("yes")) {
                    int indexYes = random.nextInt(resultYes.length);
                    Toast.makeText(getApplicationContext(), "You are " + resultYes[indexYes], Toast.LENGTH_SHORT).show();
                } else if (inputText.equals("no")) {
                    int indexNo = random.nextInt(resultNo.length);
                    Toast.makeText(getApplicationContext(), "You are " + resultNo[indexNo], Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}