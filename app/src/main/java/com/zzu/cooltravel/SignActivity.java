package com.zzu.cooltravel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zzu.cooltravel.bean.User;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by xm on 2016/5/11.
 */
public class SignActivity extends Activity {

    private EditText et_name;
    private EditText et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        Bmob.initialize(this, "d84a3bce9ec85cf2714b5d7b8a3e2f12");

        et_name = (EditText) findViewById(R.id.et_name);
        et_pass = (EditText) findViewById(R.id.et_pass);



        //点击注册，返回到注册页面
        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button btn_problem = (Button) findViewById(R.id.btn_problem);
        btn_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignActivity.this);
                //设置图标
                builder.setIcon(android.R.drawable.alert_dark_frame);
                //设置标题
//                builder.setTitle("对话框");
                //设置文本
                builder.setMessage("亲，请及时与管理员进行联系");

                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"你点击的是确定",Toast.LENGTH_LONG).show();
                       dialog.dismiss();
                    }
                });

//                //设置取消按钮
//                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                        Toast.makeText(MainActivity.this,"你点击的是取消",Toast.LENGTH_LONG).show();
//                        dialog.dismiss();
//                    }
//                });

                builder.show();
            }
        });

    }

    public void sign(View view) {
        final String username = et_name.getText().toString();
        final String password = et_pass.getText().toString();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(SignActivity.this,
                    "用户名与密码不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        User user2 = new User();
        user2.setUsername(username);
        user2.setPassword(password);

        final ProgressDialog progress = new ProgressDialog(SignActivity.this);
        progress.setMessage("登录ing>->->");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        user2.login(SignActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                progress.dismiss();
                //通过BmobUser user = BmobUser.getCurrentUser(context)获取登录成功后的本地用户信息
                //如如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(context,MyUser.class)获取自定义用户信息
                Toast.makeText(SignActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",username);
                editor.putString("password",password);
                editor.commit();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(SignActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        });

        finish();
    }
}

