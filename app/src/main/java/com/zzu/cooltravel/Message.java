package com.zzu.cooltravel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by xm on 2016/5/17.
 */
public class Message extends Fragment implements View.OnClickListener
{

    private RelativeLayout partner1,partner2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.message,null);
    }

    @Override
    public void onStart()
    {

        partner1 = (RelativeLayout)getActivity().findViewById(R.id.rl_partner1);
        partner2 = (RelativeLayout)getActivity().findViewById(R.id.rl_partner2);
        partner1.setOnClickListener(this);
        partner2.setOnClickListener(this);
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_partner1:
            case R.id.rl_partner2:
                Intent intent = new Intent();
                intent.setClass(getActivity(),Chat.class);
                startActivity(intent);
                break;
        }
    }
}
