package com.zzu.cooltravel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zzu.cooltravel.bean.MessageBean;
import com.zzu.cooltravel.R;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xm on 2016/5/17.
 */
public class MsgAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    List<MessageBean> mDatas;


    public MsgAdapter(Context context, List<MessageBean> mDatas)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }



    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        MessageBean mMessage = mDatas.get(position);
        if(mMessage.getType() == MessageBean.Type.MSGIN){
            //收到的信息返回0
            return 0;
        }
        //发出的信息返回1
        return 1;
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        MessageBean msgBean = mDatas.get(position);
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            //通过type为信息设置不同的布局
            if(getItemViewType(position) == 0){
                //收到的信息
                convertView = mInflater.inflate(R.layout.from_msg_layout,parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mTime = (TextView) convertView.findViewById(R.id.from_msg_time);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.from_msg_text);
            }else{
                convertView = mInflater.inflate(R.layout.to_msg_layout, parent,false);
                viewHolder = new ViewHolder();
                viewHolder.mTime = (TextView) convertView.findViewById(R.id.to_msg_time);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.to_msg_text);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.mTime.setText(df.format(msgBean.getTime()));
        viewHolder.mMsg.setText(msgBean.getMsg());
        return convertView;
    }

    public final class ViewHolder{
        TextView mTime;
        TextView mMsg;
    }

}

