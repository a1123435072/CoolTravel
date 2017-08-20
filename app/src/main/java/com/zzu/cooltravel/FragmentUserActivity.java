package com.zzu.cooltravel;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by xm on 2016/5/12.
 */
public class FragmentUserActivity extends Fragment {

    @Nullable
    @Override
    //返回的View对象会作为fragment01的内容显示在屏幕
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //使用内容填充器进行填充
        View view = inflater.inflate(R.layout.fragment_user,null);

        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relativeLayout);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PersonData.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
