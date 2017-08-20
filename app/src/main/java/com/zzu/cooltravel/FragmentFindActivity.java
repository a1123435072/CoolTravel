package com.zzu.cooltravel;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zzu.cooltravel.bean.Find;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xm on 2016/5/12.
 */
public class FragmentFindActivity extends Fragment {


    private TextView ff_tv;


    List<Find> findList;
    @Nullable
    @Override
    //返回的View对象会作为fragment01的内容显示在屏幕
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //使用内容填充器进行填充
        View view = inflater.inflate(R.layout.fragment_find,null);

        findList = new ArrayList<Find>();

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getActivity(),"find.db",1);
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("find1",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            String title = cursor.getString(1);
            String content = cursor.getString(2);

            Find find = new Find(title,content);
            findList.add(find);
        }

//        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
//        //把数据显示到屏幕上
//        for (Find find : findList ){
//            TextView textView = new TextView(getActivity());
//            textView.setText(find.toString());
//            ll.addView(textView);
//        }

        ListView lv = (ListView) view.findViewById(R.id.lv);

        lv.setAdapter(new FindAdapter());
        return  view;
    }

    class FindAdapter extends BaseAdapter{

        //系统调用的，然后可以获得集合中有多少元素
        @Override
        public int getCount() {
            return findList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        //由系统调用，获取一个view对象，作为ListView的条目
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           //TextView tv = new TextView(getActivity());
           //tv.setText("这是tv");
            // Find find = findList.get(position);
           //tv.setText(find.toString());
           //tv.setTextSize(20);
           //return tv;

            //把布局填充成一个view对象
            Find find = findList.get(position);
            View v = View.inflate(getActivity(),R.layout.item_listview,null);

            TextView title = (TextView) v.findViewById(R.id.tv_title);
            title.setText(find.getTitle());
            TextView content = (TextView) v.findViewById(R.id.tv_content);
            content.setText(find.getContent());
            return v;

        }
    }
}
