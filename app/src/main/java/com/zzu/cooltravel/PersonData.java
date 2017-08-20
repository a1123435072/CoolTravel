package com.zzu.cooltravel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xm on 2016/5/16.
 */
public class PersonData extends Activity {
    private TextView exit_id;
    private TextView personal_edit;
    private TextView my_number_id;
    private TextView ni_cheng_id;
    private TextView age_id;
    private TextView gander_id;
    private TextView work_id;
    private TextView address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persondata);
        initView();
        initEvents();
    }


    //退出本地帐号
    private void initEvents()
    { // 事件处理方式
        exit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                CheckExitCurrentCount();
            }
        });
        //退出登录的按钮事件处理

        personal_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // 跳转到编辑资料界面
                // number, name, gander, age, job, liveNow, status
                Intent intent = new Intent(PersonData.this, ChangeInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("number", my_number_id.getText().toString());
                bundle.putString("name", ni_cheng_id.getText().toString());
                bundle.putString("age", age_id.getText().toString());
                bundle.putString("gander", gander_id.getText().toString());
                bundle.putString("job", work_id.getText().toString());
                bundle.putString("liveNow", address_id.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                //getActivity().finish();
            }
        });

    }

    public void initView()
    { // 初始化布局文件的控件对象

        exit_id = (TextView)findViewById(R.id.exit_id);
        personal_edit = (TextView)findViewById(R.id.personal_edit);

        my_number_id = (TextView) findViewById(R.id.my_number_id);
        ni_cheng_id = (TextView)findViewById(R.id.ni_cheng_id);
        age_id = (TextView)findViewById(R.id.age_id);
        gander_id = (TextView)findViewById(R.id.gander_id);
        work_id = (TextView)findViewById(R.id.work_id);
        address_id = (TextView)findViewById(R.id.address_id);


        readMsg();
    }

    /*
     * 从本地读取信息，并显示
     */
    private void readMsg()
    {
        // number, name, gander, age, job, liveNow, status
        SharedPreferences share = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String number = share.getString("number",null);
        String name = share.getString("name",null);
        String gander = share.getString("gander",null);
        String age = share.getString("age",null);
        String job = share.getString("job",null);
        String liveNow = share.getString("liveNow",null);
        String status = share.getString("status",null);
        //显示读取到的信息
        setMsg(number, name, gander, age, job, liveNow, status);


    }
    /*
     * 显示读取到的信息
     */
    private void setMsg(String number, String name, String gander,
                        String age, String job, String liveNow, String status)
    {
        my_number_id.setText(number);
        ni_cheng_id.setText(name);
        age_id.setText(age);
        gander_id.setText(gander);
        work_id.setText(job);
        address_id.setText(liveNow);
    }

    /**
     * 把对话框提取出来
     */
    public void CheckExitCurrentCount(){
        AlertDialog.Builder builder = new AlertDialog.Builder(PersonData.this);
        builder.setTitle("提示信息 : ");
        builder.setMessage("你确定要退出当前账户? ");
        //确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getActivity(),"你点击了确定按钮",Toast.LENGTH_SHORT).show();
                //清空本地保存的登录信息
                SharedPreferences spf = getSharedPreferences(
                        "userInfo", Activity.MODE_PRIVATE);
                SharedPreferences.Editor edit = spf.edit();
                edit.clear();
                edit.commit();
                // 重新跳转到主界面
                Intent intent = new Intent(PersonData.this, MainActivity.class);
                //设置返回码，通知关闭上一个activity
                setResult(1);
                startActivity(intent);
                finish();
            }
        });
        //取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(PersonData.this, "你取消了该操作", Toast.LENGTH_SHORT).show();
            }
        });
        //显示对话框
        builder.create().show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        readMsg();
    }
}
