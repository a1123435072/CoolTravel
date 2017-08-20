package com.zzu.cooltravel;

import android.app.Activity;
import android.os.Bundle;
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
public class LoginActivity extends Activity {
    private EditText login_name,login_password,login_cpassword, login_email;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "d84a3bce9ec85cf2714b5d7b8a3e2f12");

        login_name = (EditText) findViewById(R.id.name);
        login_password = (EditText) findViewById(R.id.password);
        login_cpassword = (EditText) findViewById(R.id.cpassword);
        login_email = (EditText) findViewById(R.id.email);

    }

    public void login(View view) {
        String username = login_name.getText().toString();
        String password = login_password.getText().toString();
        String cpassword = login_cpassword.getText().toString();
        String email = login_email.getText().toString();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCpassword(cpassword);
        user.setEmail(email);

        user.signUp(LoginActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginActivity.this,"注册失败"+s,Toast.LENGTH_SHORT).show();
            }
        });

    }
}


