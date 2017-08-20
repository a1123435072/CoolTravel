package com.zzu.cooltravel;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;


import com.zzu.cooltravel.bean.Shuoshuo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xm on 2016/5/16.
 */
public class AddActivity extends Activity {

    private EditText et_content;
    private ImageView iv_add;


    private static final int CAMERA_WITH_DATA = 3023;
    String picUrl = "";
    private Uri mOutPutFileUri = null;
    private  String picName = "";
    private int action;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的
    ArrayList<ImageView> imageViewList= new ArrayList<ImageView>();
    ArrayList<String> imagePathList= new ArrayList<String>();
    String[] items = { "拍照", "相册" };
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private LinearLayout ly_add;


    private MyDatabaseHelper myDatabaseHelper;
    private Button insert;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDatabaseHelper = new MyDatabaseHelper(this, "find.db", 1);
        insert = (Button) findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的内容
                String title = ((EditText)findViewById(R.id.title)).getText().toString();
                String content = ((EditText)findViewById(R.id.content)).getText().toString();
                //插入
                insertData(myDatabaseHelper.getReadableDatabase(),title,content);
                Toast.makeText(AddActivity.this,"发布成功了",Toast.LENGTH_SHORT).show();
            }
        });

        //添加照片
        ly_add = (LinearLayout) findViewById(R.id.ly_add);

        ly_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this)
                        .setTitle("")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                    switch (i) {
                                        case 0:
                                            action = PHOTO_REQUEST_TAKEPHOTO;
                                            Toast.makeText(AddActivity.this,"拍照",Toast.LENGTH_SHORT).show();
                                            getImage();
                                            dialogInterface.dismiss();
                                            break;
                                        case 1:
                                            action = PHOTO_REQUEST_GALLERY;
                                            Toast.makeText(AddActivity.this,"相册",Toast.LENGTH_SHORT).show();
                                            setImage();
                                            dialogInterface.dismiss();
                                            break;
                                    }
                                }
                        });
                builder.create().show();//显示

            }
        });
    }

    private void insertData(SQLiteDatabase db, String title, String content){
        //执行插入语句
        // 执行插入语句
        db.execSQL("insert into find1 values(null , ? , ?)"
                , new String[] {title, content });
    }

    public void onDestroy()
    {
        super.onDestroy();
        // 退出程序时关闭MyDatabaseHelper里的SQLiteDatabase
        if (myDatabaseHelper != null)
        {
            myDatabaseHelper.close();
        }
    }


    private void getImage(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picName = "IMG_"+System.currentTimeMillis()+".jpg";
        String filePath = Environment.getExternalStorageDirectory()+"/sdcard/CoolTravel/image/";
        picUrl = filePath+picName;
        Log.i("test","picUrl:"+picUrl);
        File path1 = new File("/storage/emulated/0/sdcard/CoolTravel/image");
        if (!path1.exists()) {
            path1.mkdirs();
        }

        File file = new File(picUrl);
        mOutPutFileUri = Uri.fromFile(file);
        Log.i("test","mOutPutFileUri:"+mOutPutFileUri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
        startActivityForResult(intent, CAMERA_WITH_DATA);
    }




    private void setImage() {
        // TODO Auto-generated method stub
        //使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片
        Intent getAlbum = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode != RESULT_OK) {        //此处的 RESULT_OK 是系统自定义得一个常量
            Log.e("TAG->onresult", "ActivityResult resultCode error");
            return;
        }
        Bitmap bm = null;
        //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
        ContentResolver resolver = getContentResolver();
        //此处的用于判断接收的Activity是不是你想要的那个
        if (requestCode == IMAGE_CODE&&imageViewList.size()<9||requestCode == CAMERA_WITH_DATA) {

            try {
                Uri originalUri =null;
                if(requestCode == IMAGE_CODE){
                    originalUri = data.getData();
                    //获得图片的uri
                    Log.i("test","picuri"+originalUri);
                    //通过uri(相对路径)获取图片，并设置大小
                    Bitmap bmp = BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
                    bm = Bitmap.createScaledBitmap(bmp, 210, 210, true);
                }else if(requestCode == CAMERA_WITH_DATA&&data != null){
                    Bitmap photo = data.getParcelableExtra("data");
                    bm = Bitmap.createScaledBitmap(photo, 210, 210, true);
                }
                else if(requestCode == CAMERA_WITH_DATA&&data == null){
                    Bitmap bmp_2 = BitmapFactory.decodeStream(resolver.openInputStream(mOutPutFileUri));
                    bm = Bitmap.createScaledBitmap(bmp_2, 210, 210, true);
                }

                //九宫格显得到bitmap图片
                LinearLayout layout_pic = (LinearLayout)findViewById(R.id.ly_pic);
                LinearLayout layout_pic_2 = (LinearLayout)findViewById(R.id.ly_pic_2);
                LinearLayout layout_pic_3 = (LinearLayout)findViewById(R.id.ly_pic_3);
                ImageView imageView = new ImageView(this);  //创建imageview

                imageView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));  //image的布局方式
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(10, 10, 10, 10);

                imageView.setLayoutParams(lp);//设置imageView之间的间距
                imageView.setImageBitmap(bm);//设置imageView的背景

                imageViewList.add(imageView);//将imageView添加到imageViewList中

                Log.i("test", "imageViewList.size():" + imageViewList.size());
                //排列成九宫格显示
                if(imageViewList.size()<=3){
                    layout_pic.addView(imageView);
                }else if(imageViewList.size()<=6&&imageViewList.size()>3){
                    layout_pic_2.addView(imageView);
                }else{
                    layout_pic_3.addView(imageView);
                }

                if(requestCode == IMAGE_CODE){
                    //    这里开始的第二部分，获取图片的路径：
                    String[] proj = {MediaStore.Images.Media.DATA};
                    //好像是android多媒体数据库的封装接口，具体的看Android文档
                    Cursor cursor = getContentResolver().query(originalUri, proj, null, null, null);
                    //按我个人理解 这个是获得用户选择的图片的索引值
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    //将光标移至开头 ，这个很重要，不小心很容易引起越界
                    cursor.moveToFirst();
                    Log.i("test", "column_index" + column_index);
                    //最后根据索引值获取图片路径
                    picUrl = cursor.getString(column_index);
                    Log.i("test","picUrl"+cursor.getString(column_index));
                    cursor.close();
                }
                imagePathList.add(picUrl);
            }catch (IOException e) {
                Log.e("TAG-->Error", e.toString());
            }
        }
        else{
            Toast.makeText(this, "图片数量已达上限", Toast.LENGTH_SHORT).show();
        }

    }






//    public void fabu(View view) {
//        et_content = (EditText) findViewById(R.id.content);
//        String content = et_content.getText().toString();
//        String path = "";
//        Shuoshuo ss = new Shuoshuo();
//        Log.i("content",content);
//        Log.i("paht",path);

//        FragmentFindActivity fragmentFindActivity =   new FragmentFindActivity();
//        Bundle bundle = new Bundle();
//        bundle.putString("content",content);
//        fragmentFindActivity.setArguments(bundle);
//        android.app.FragmentManager manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.et_content, fragmentFindActivity);
//        transaction.commit();



//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = sDateFormat.format(new Date());
//        if(imagePathList.size() > 0){
//            for(int i = 0;i < imagePathList.size();i++){
//                if(i == imagePathList.size()-1){
//                    path += (imagePathList.get(i));
//                }else{
//                    path += (imagePathList.get(i)+",");
//                }
//            }
//        }
//
//        System.out.println("content:"+content);
//        System.out.println("path:"+path);
//        System.out.println("date:"+date);
//
//        ss.setContent(content);
//        ss.setImagePath(path);
//        ss.setTime(date);



//    }
}
