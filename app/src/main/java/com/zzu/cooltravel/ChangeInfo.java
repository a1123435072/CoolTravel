package com.zzu.cooltravel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zzu.cooltravel.bean.Person;
import com.zzu.cooltravel.bean.User;
import com.zzu.cooltravel.util.MyNumberPicker;

/**
 * Created by xm on 2016/5/16.
 */
public class ChangeInfo extends Activity{

        // 声明文本框变量
        ImageView change_my_photo_id;
        EditText change_ni_cheng_id;
        TextView change_gander_id;
        TextView change_age_id;
        EditText change_work_id;
        EditText change_address_id;
        TextView change_status_id;
        TextView my_number_id;

        TextView edit_info_save;	//保存按钮

        // 数字选择器工具类
        MyNumberPicker num;

        //账号
        String number ;

        // 保存选中的个人状态信息
        String resultSelect = "";

       Person person;

        // 声明本地保存用户信息的文件名
        private String fileName = "userInfo";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.change_info);

        }

//    /*
//     * 初始化布局
//     */
//    private void initView()
//    {
//        my_number_id = (TextView) findViewById(R.id.my_number_id);				//账号
//        change_my_photo_id = (ImageView) findViewById(R.id.change_my_photo_id);	//头像
//        change_ni_cheng_id = (EditText) findViewById(R.id.change_ni_cheng_id);	//昵称
//        change_gander_id = (TextView) findViewById(R.id.change_gander_id);		//性别
//        change_age_id = (TextView) findViewById(R.id.change_age_id);			//年龄
//        change_work_id = (EditText) findViewById(R.id.change_work_id);			//职业
//        change_address_id = (EditText) findViewById(R.id.change_address_id);	//地址
////       change_status_id = (TextView) findViewById(R.id.change_status_id);		//状态
//        edit_info_save = (TextView) findViewById(R.id.edit_info_save);			//保存
//
////        number = CheckInfo.lookFile(ChangeInfo.this);// 本地保存的帐号
//
//        //获取传来的值
//        // number, name, gander, age, job, liveNow, status
//        Bundle bundle = this.getIntent().getExtras();
//        String tp_number = bundle.getString("number");
//        String tp_name = bundle.getString("name");
//        String tp_gander = bundle.getString("gander");
//        String tp_age = bundle.getString("age");
//        String tp_job = bundle.getString("job");
//        String tp_liveNow = bundle.getString("liveNow");
//        String tp_status = bundle.getString("status");
//        my_number_id.setText(tp_number);
//        change_ni_cheng_id.setText(tp_name);
//        change_gander_id.setText(tp_gander);
//        change_age_id.setText(tp_age);
//        change_work_id.setText(tp_job);
//        change_address_id.setText(tp_liveNow);
//        change_status_id.setText(tp_status);
//
//    }
//
//    /*
//     * 添加监听事件
//     */
//    private void initEvent()
//    {
//        change_my_photo_id.setOnClickListener(new PhotoListener());
//        change_gander_id.setOnClickListener(new GanderListener());
//        change_age_id.setOnClickListener(new AgeListener());
//        change_status_id.setOnClickListener(new StatusListener());
////        edit_info_save.setOnClickListener(new SaveListener());
//    }
//

    /*
     * 从图库中选择图片作为头像
     */
    private class PhotoListener implements View.OnClickListener {

        @Override
        public void onClick(View arg0)
        {
            Intent intent = new Intent(Intent.ACTION_PICK,null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 100);
            //自动回调onActivityResult方法
        }

    }

    //重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 100){
            if(data != null){
                //如果选择的图片存在，显示图片
                change_my_photo_id.setImageURI(data.getData());
            }
        }
    }

//    /*
//     * 性别选择
//     */
//    private class GanderListener implements View.OnClickListener
//    {
//
//        @Override
//        public void onClick(View v)
//        {
//            final String[] data =
//                    { "男", "女" };
//            AlertDialog.Builder builder = new AlertDialog.Builder(
//                    ChangeInfo.this);
//            builder.setItems(data, new DialogInterface.OnClickListener()
//            {
//
//                @Override
//                public void onClick(DialogInterface arg0, int select)
//                {
//                    switch (select)
//                    {
//
//                        case 0:
//                            change_gander_id.setText("男");
//                            break;
//                        case 1:
//                            change_gander_id.setText("女");
//                            break;
//                    }
//                }
//            });
//            builder.show();
//        }
//
//    }
//
//    /*
//     * 年龄选择
//     */
//    private class AgeListener implements View.OnClickListener
//    {
//
//        @Override
//        public void onClick(View v)
//        {
//            num = new MyNumberPicker(ChangeInfo.this);
//            AlertDialog.Builder builder = new AlertDialog.Builder(
//                    ChangeInfo.this);
//            builder.setView(num);
//            builder.setPositiveButton("确定",
//                    new DialogInterface.OnClickListener()
//                    {
//
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1)
//                        {
//                            change_age_id.setText(num.number1 + ""
//                                    + num.number2 + "");
//                        }
//                    });
//            builder.show();
//        }
//
//    }
//
//    /*
//     * 当前状态选择
//     */
//    private class StatusListener implements View.OnClickListener
//    {
//
//        @Override
//        public void onClick(View v)
//        {
//            // 状态分类
//            final String[] status =
//                    { "有技术", "有项目", "有资金" };
//            // 是否选中
//            final boolean isSelect[] =
//                    { false, false, false };
//            // 创建对话框
//            AlertDialog.Builder builder = new AlertDialog.Builder(
//                    ChangeInfo.this);
//            builder.setMultiChoiceItems(status, isSelect,
//                    new DialogInterface.OnMultiChoiceClickListener()
//                    {
//
//                        @Override
//                        public void onClick(DialogInterface arg0, int which,
//                                            boolean isChecked)
//                        {
//                            // 记录哪一项被选中
//                            isSelect[which] = isChecked;
//
//                        }
//                    });
//            builder.setPositiveButton("确定",
//                    new DialogInterface.OnClickListener()
//                    {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int select)
//                        {
//                            for (int i = 0; i < isSelect.length; i++)
//                            {
//                                if (isSelect[i])
//                                {
//                                    // 保存被选中的选项
//                                    resultSelect = resultSelect + " "
//                                            + status[i];
//                                    // 清除弹窗里的记录
//                                    isSelect[i] = false;
//                                }
//                                // 设置文本框显示的选中的内容
//                                change_status_id.setText(resultSelect);
//                            }
//                            // 清除保存的值
//                            resultSelect = "";
//                        }
//                    });
//            builder.setNegativeButton("取消",
//                    new DialogInterface.OnClickListener()
//                    {
//
//                        @Override
//                        public void onClick(DialogInterface arg0, int arg1)
//                        {
//
//                        }
//                    });
//            // 显示对话框
//            builder.show();
//        }
//
//    }
//
//    /*
//     * 保存信息
//     */
//    public class SaveListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View v)
//        {
//            //change_my_photo_id.setDrawingCacheEnabled(true);
//            //Bitmap bitmap = Bitmap.createBitmap(change_my_photo_id.getDrawingCache());
//            //change_my_photo_id.setDrawingCacheEnabled(false);
//            String nicheng = change_ni_cheng_id.getText().toString().trim();
//            String gander = change_gander_id.getText().toString().trim();
//            String age = change_age_id.getText().toString().trim();
//            String work = change_work_id.getText().toString().trim();
//            String address = change_address_id.getText().toString().trim();
//            String status = change_status_id.getText().toString().trim();
//
//            person = new Person(number, nicheng, gander, age, work, address, status);
//            ChangeInfoTask c = new ChangeInfoTask();
//            c.execute();
//        }
//
//    }
//
//    class ChangeInfoTask extends AsyncTask<Void, Void, Integer> {
//
//        @Override
//        protected Integer doInBackground(Void... params)
//        {
//            int result  = new NetRequest().updataUserInformation(person);
//
//            return result;
//        }
//        @Override
//        protected void onPostExecute(Integer result)
//        {
//
//            super.onPostExecute(result);
//            if(result == Agreement.Data_Update_UserInformation_Success){
//                Toast.makeText(ChangeInfo.this, "修改成功", Toast.LENGTH_SHORT).show();
//                SharedPreferences share = ChangeInfo.this
//                        .getSharedPreferences("userInfo",
//                                Activity.MODE_PRIVATE);
//                SharedPreferences.Editor edit = share.edit();
//                edit.putString("number", person.getNumber());
//                edit.putString("name", person.getName());
//                edit.putString("gander", person.getGander());
//                edit.putString("age", person.getAge());
//                edit.putString("job", person.getJob());
//                edit.putString("liveNow", person.getLiveNow());
//                edit.putString("status", person.getStatus());
//                edit.commit();
//                finish();
//            }else{
//                Toast.makeText(ChangeInfo.this, "服务器异常", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


}