package com.zzu.cooltravel;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zzu.cooltravel.bean.User;
import com.zzu.cooltravel.view.SlidingMenu;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity{

    private SlidingMenu mLeftMenu;
    private RelativeLayout firstItem;
    private RelativeLayout signItem;
    private RadioButton main_map;
    private Fragment01Activity fg1;
    private RadioButton main_chat;
    private RadioButton main_add;
    private RadioButton main_find;
    private RadioButton main_user;
    private RelativeLayout toolsItem;
    private RelativeLayout shareItem;
    private RelativeLayout exit;

    private Animation loadAnimation;
    private TextView tv_sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Bmob.initialize(this, "d84a3bce9ec85cf2714b5d7b8a3e2f12");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        tv_sign = (TextView) findViewById(R.id.tv_sign);



        SharedPreferences sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
        String name = sharedPreferences.getString("name","");
        String password = sharedPreferences.getString("password","");
        tv_sign.setText(name);

        //设置默认的Fragmentx显示
        fg1 = new Fragment01Activity();
        //获取fragment管理器
        FragmentManager fm = getFragmentManager();
        //打开事物
        FragmentTransaction ft = fm.beginTransaction();
        //把内容显示到帧布局
        ft.replace(R.id.main_framelayout, fg1);
        //提交
        ft.commit();

        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
        firstItem = (RelativeLayout) findViewById(R.id.loginItem);


        //登录
        signItem = (RelativeLayout) findViewById(R.id.signItem);
        signItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });


        //注册
        firstItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //小工具
        toolsItem = (RelativeLayout) findViewById(R.id.toolsItem);
        toolsItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToolsActivity.class);
                startActivity(intent);
            }
        });
        //分享
        shareItem = (RelativeLayout) findViewById(R.id.shareItem);
        shareItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShareActivity.class);
                startActivity(intent);
            }
        });

        //退出
        exit = (RelativeLayout) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //设置图标
                builder.setIcon(android.R.drawable.alert_dark_frame);
                //设置标题
//                builder.setTitle("对话框");
                //设置文本
                builder.setMessage("亲，确定要退出吗？");

                //设置确定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"你点击的是确定",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

                //设置取消按钮
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this,"你点击的是取消",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });


        //为底部菜单添加点击事件
        main_map = (RadioButton) findViewById(R.id.main_map);
        main_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建Fragment对象，fg1已经提为全局，这里就不new
                //Fragment01Activity fg1 = new Fragment01Activity();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事物
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示到帧布局
                ft.replace(R.id.main_framelayout, fg1);
                //提交
                ft.commit();
            }
        });

        main_chat = (RadioButton) findViewById(R.id.main_chat);
        main_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentChatActivity fg2 = new FragmentChatActivity();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事物
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示到帧布局
                ft.replace(R.id.main_framelayout, fg2);
                //提交
                ft.commit();
            }
        });
        main_add = (RadioButton) findViewById(R.id.main_add);
        main_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAnimation = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.btn_add);
                main_add.startAnimation(loadAnimation);
                startActivity(new Intent(MainActivity.this, AddActivity.class));
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);


            }
        });


        main_find = (RadioButton) findViewById(R.id.main_find);
        main_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentFindActivity fg4 = new FragmentFindActivity();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事物
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示到帧布局
                ft.replace(R.id.main_framelayout, fg4);
                //提交
                ft.commit();
            }
        });

        main_user = (RadioButton) findViewById(R.id.main_user);
        main_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUserActivity fg5 = new FragmentUserActivity();
                //获取fragment管理器
                FragmentManager fm = getFragmentManager();
                //打开事物
                FragmentTransaction ft = fm.beginTransaction();
                //把内容显示到帧布局
                ft.replace(R.id.main_framelayout, fg5);
                //提交
                ft.commit();
            }
        });

    }



    //点击返回键是确认是否退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                AlertDialog.Builder build = new AlertDialog.Builder(this);
                build
//                        .setTitle("注意")
                        .setMessage("亲，确定要退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).show();
                break;
        }
        return false;
        //return super.onKeyDown(keyCode, event);
    }


    public void toggleMenu(View view) {
        mLeftMenu.toggle();
    }



}
