package com.example.datiqiapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.datiqiapp.R;
import com.example.datiqiapp.Sqlite.DanxuanDbutils;
import com.example.datiqiapp.Sqlite.PanduanDbutils;
import com.example.datiqiapp.Sqlite.TiankongDbutils;
import com.example.datiqiapp.bean.Question;

import java.util.List;
/**
 * 题目界面
 */
public class TimuActivity extends AppCompatActivity {
   ListView lvtimu;//单选
    ListView lvpanduan;//判断
    ListView lvtiankong;//填空
    TimuAdapter timuAdapter;//适配器1，用于绑定单选UI
    private ImageView ivback;//后退

    TimuAdapter1 timuAdapter1;//适配器2，用于绑定判断UI
    TimuAdapter2 timuAdapter2;//适配器3，用于绑定填空UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timu);//设置显示的界面
        lvtimu=findViewById(R.id.lvtimu);
        lvpanduan=findViewById(R.id.lvpanduan);
        lvtiankong=findViewById(R.id.lvtiankong);

        //分别加载三个类型的题目列表，使用三个不同的Adapter
        timuAdapter = new TimuAdapter(getApplicationContext(), DanxuanDbutils.getInstance(getApplicationContext()).load());
        lvtimu.setAdapter(timuAdapter);


        timuAdapter1 = new TimuAdapter1(getApplicationContext(), PanduanDbutils.getInstance(getApplicationContext()).load());
        lvpanduan.setAdapter(timuAdapter1);


        timuAdapter2 = new TimuAdapter2(getApplicationContext(), TiankongDbutils.getInstance(getApplicationContext()).load());
        lvtiankong.setAdapter(timuAdapter2);


        ivback =findViewById(R.id.iv_back);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //根据子项总高度自动调整ListView的高度
        setListViewHeightBasedOnChildren(lvtimu);
        setListViewHeightBasedOnChildren(lvpanduan);
        setListViewHeightBasedOnChildren(lvtiankong);
    }

    class TimuAdapter extends BaseAdapter{
    private List<Question>list;
    Context context;
    public TimuAdapter(Context context,List<Question>list){
        this.context=context;
        this.list=list;
    }
    @Override
        public int getCount() {
            return list.size();
        }//数量

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }//位置

        @Override
        public long getItemId(int position) {
            return position;
        }//id

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            //如果convertView为空，则需要创建新的视图
            // 并通过findViewById方法获取每个列表项中的控件
            // 首先获取item_timu布局文件并将其填充到convertView中
            // 然后根据ID获取对应类型、问题和答案的TextView控件，并将其缓存起来
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_timu, null);

                holder.leixing = convertView.findViewById(R.id.leiixng);
                holder.timu = convertView.findViewById(R.id.timu);
                holder.daan = convertView.findViewById(R.id.daan);
                holder.ll = convertView.findViewById(R.id.ll);
                //将 ViewHolder 对象设置为 convertView 视图的标签，用于在下次获取 convertView 时直接获取 ViewHolder 对象，避免重复创建
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //将获取到的题目类型、问题、答案设置到缓存起来的控件上
            // 使得控件中显示的文字内容与数据源中的记录对应起来
            holder.leixing.setText("类型："+list.get(position).getLeixing());
            holder.timu.setText("问题：" + list.get(position).getWenti());
            holder.daan.setText("答案：" + list.get(position).getDaan());

            //为列表项的LinearLayout控件添加点击监听器
            // 每当用户单击某个条目时，onClick方法将被调用
            // 并通过showNormalDialog方法展示出题目内容的对话框
            // 同时，更新数据源及适配器，使得界面显示的数据更新为最当前的数据
            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNormalDialog(list.get(position).getId());
                    timuAdapter = new TimuAdapter(context, DanxuanDbutils.getInstance(context).load());
                    lvtimu.setAdapter(timuAdapter);
                }
            });

            return convertView;
        }

        class ViewHolder{
            LinearLayout ll;
            TextView leixing, timu, daan;
        }
    }


    class TimuAdapter1 extends BaseAdapter{
        private List<Question>list;
        Context context;
        public TimuAdapter1(Context context,List<Question>list){
            this.context=context;
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_timu, null);

                holder.leixing = convertView.findViewById(R.id.leiixng);
                holder.timu = convertView.findViewById(R.id.timu);
                holder.daan = convertView.findViewById(R.id.daan);
                holder.ll = convertView.findViewById(R.id.ll);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.leixing.setText("类型："+list.get(position).getLeixing());
            holder.timu.setText("问题：" + list.get(position).getWenti());
            holder.daan.setText("答案：" + list.get(position).getDaan());

            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNormalDialog1(list.get(position).getId());
                    timuAdapter = new TimuAdapter(context, PanduanDbutils.getInstance(context).load());
                    lvtimu.setAdapter(timuAdapter);
                }
            });

            return convertView;
        }

        class ViewHolder{
            LinearLayout ll;
            TextView leixing, timu, daan;
        }
    }

    class TimuAdapter2 extends BaseAdapter{
        private List<Question>list;
        Context context;
        public TimuAdapter2(Context context,List<Question>list){
            this.context=context;
            this.list=list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_timu, null);

                holder.leixing = convertView.findViewById(R.id.leiixng);
                holder.timu = convertView.findViewById(R.id.timu);
                holder.daan = convertView.findViewById(R.id.daan);
                holder.ll = convertView.findViewById(R.id.ll);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.leixing.setText("类型："+list.get(position).getLeixing());
            holder.timu.setText("问题：" + list.get(position).getWenti());
            holder.daan.setText("答案：" + list.get(position).getDaan());

            holder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showNormalDialog2(list.get(position).getId());
                    timuAdapter = new TimuAdapter(context, PanduanDbutils.getInstance(context).load());
                    lvtimu.setAdapter(timuAdapter);
                }
            });

            return convertView;
        }

        class ViewHolder{
            LinearLayout ll;
            TextView leixing, timu, daan;
        }
    }


    //删除单选题
    private void showNormalDialog(final int id) {
        //用了AlertDialog.Builder创建一个对话框
        // 并设置标题和消息。在对话框中我们提供了两种选项“确定”和“关闭”
        //setPositiveButton方法以及匿名内部类的方式，为确定选项添加点击事件
        // 在点击时调用PanduanDbutils中封装好的删除方法并将数据更新到界面上
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(TimuActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否删除?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //调用 PanduanDbutils 类中封装好的删除方法，并通过 TimuAdapter 更新 ListView 上的数据；
                        DanxuanDbutils.getInstance(getApplicationContext()).delete(getApplicationContext(),id+"");
                        timuAdapter = new TimuAdapter(getApplicationContext(), DanxuanDbutils.getInstance(getApplicationContext()).load());
                        //将 timuAdapter 对象设置为 lvtimu ListView 的适配器，用于将数据显示到 ListView 上
                        //调用 setAdapter() 方法设置适配器
                        //timuAdapter 是一个 TimuAdapter 对象，用于将题目数据显示到 ListView 上
                        //将 timuAdapter 对象设置为 lvtimu 的适配器后，ListView 会自动调用 TimuAdapter 中的 getView() 方法来获取每个列表项的视图并显示数据
                        lvtimu.setAdapter(timuAdapter);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        // 调用 show() 方法显示对话框
        normalDialog.show();
    }


    //删除判断题
    private void showNormalDialog1(final int id) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(TimuActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否删除?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PanduanDbutils.getInstance(getApplicationContext()).delete(getApplicationContext(),id+"");
                        timuAdapter = new TimuAdapter(getApplicationContext(), PanduanDbutils.getInstance(getApplicationContext()).load());
                        lvtimu.setAdapter(timuAdapter);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }


    //删除填空题
    private void showNormalDialog2(final int id) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(TimuActivity.this);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("是否删除?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TiankongDbutils.getInstance(getApplicationContext()).delete(getApplicationContext(),id+"");
                        timuAdapter = new TimuAdapter(getApplicationContext(), TiankongDbutils.getInstance(getApplicationContext()).load());
                        lvtimu.setAdapter(timuAdapter);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }


    //设置ListView高度的方法
    public void setListViewHeightBasedOnChildren(ListView listView) {

        //首先获取ListView对应的Adapter对象，如果该对象为空，则直接返回，因为此时列表是空的。
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }

        //通过遍历所有列表项，累加它们的高度得到总高度。
        // 在此过程中使用getMeasuredHeight方法获取listItem的高度
        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        //最后根据计算出来的总高度更新ListView的LayoutParams，
        // 使得其大小符合所有元素的高度总和。
        // 其中params.height需要加上ListView自带的分割线高度和去除头部导航的高度（列表项数减1），
        // 最后将新LayoutParams设置到ListView中。
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

        listView.setLayoutParams(params);
    }
}