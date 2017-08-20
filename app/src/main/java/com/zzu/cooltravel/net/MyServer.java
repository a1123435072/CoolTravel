package com.zzu.cooltravel.net;

import com.zzu.cooltravel.bean.MessageBean;

import java.util.Date;

/**
 * Created by xm on 2016/5/17.
 */
public class MyServer{
//       / * uid:要发送的好友的账号
//        * msg：要发送的消息
//        * return：发送信息，得到返回的信息（MessageBean类型）
//        */
        public static MessageBean sendMessage(String uid, String msg){
        MessageBean mBean = new MessageBean();
        mBean.setTime(new Date());
        mBean.setType(MessageBean.Type.MSGIN);
        mBean.setMsg("喔啦啦...");
        return mBean;
        }
        }
