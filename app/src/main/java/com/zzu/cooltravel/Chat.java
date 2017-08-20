package com.zzu.cooltravel;

import android.app.Activity;
import android.os.*;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zzu.cooltravel.Adapter.MsgAdapter;
import com.zzu.cooltravel.bean.MessageBean;
import com.zzu.cooltravel.net.MyServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xm on 2016/5/17.
 */
public class Chat extends Activity{
    // 显示信息的listView
    private ListView chat_msg_list;
    // 列表数据源
    private List<MessageBean> mDatas;
    // 适配器
    private MsgAdapter mAdapter;
    // 消息发送框
    private EditText et_send_msg;
    // 消息发送按钮
    private Button btn_send_msg;

    // 监控服务器发送来的信息
    private Handler handler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            MessageBean fromMessage = (MessageBean) msg.obj;
            mDatas.add(fromMessage);
            mAdapter.notifyDataSetChanged();
            // 设置listView显示在最后一行
            chat_msg_list.setSelection(mDatas.size() - 1);
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        // 初始化组件
        initView();

        // 模拟一条数据
        initDatas();

        // 发送信息并接收
        initSendMsg();
    }

    // 模拟一条数据
    private void initDatas()
    {
        mDatas.add(new MessageBean("你好", new Date(), MessageBean.Type.MSGIN));
    }

    // 发送信息并接收
    private void initSendMsg()
    {
        btn_send_msg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String toMsg = et_send_msg.getText().toString();
                if (TextUtils.isEmpty(toMsg))
                {
                    et_send_msg.setError("消息不能为空");
                    return;
                }
                // 绑定数据
                MessageBean toMessage = new MessageBean();
                toMessage.setTime(new Date());
                toMessage.setMsg(toMsg);
                toMessage.setType(MessageBean.Type.MSGOUT);
                mDatas.add(toMessage);
                mAdapter.notifyDataSetChanged();
                // 清空输入框
                et_send_msg.setText("");

                // 通过线程监控服务器返回来的消息
                new Thread()
                {
                    public void run()
                    {
                        MessageBean fromMeaage = MyServer.sendMessage("123456", toMsg);
                        android.os.Message m = android.os.Message.obtain();
                        m.obj = fromMeaage;
                        handler.sendMessage(m);
                    };
                }.start();
            }
        });
    }

    /*
     * 初始化组件
     */
    private void initView()
    {
        chat_msg_list = (ListView) findViewById(R.id.chat_msg_list);
        et_send_msg = (EditText) findViewById(R.id.et_send_msg);
        btn_send_msg = (Button) findViewById(R.id.btn_send_msg);
        mDatas = new ArrayList<MessageBean>();
        mAdapter = new MsgAdapter(Chat.this, mDatas);
        // 为ListView设置Adapter
        chat_msg_list.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

