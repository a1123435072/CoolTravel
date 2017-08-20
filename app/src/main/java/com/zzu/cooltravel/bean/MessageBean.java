package com.zzu.cooltravel.bean;

import java.util.Date;

/**
 * Created by xm on 2016/5/17.
 */
public class MessageBean {
    private String msg;	//发送的信息
    private Date time;	//发送时间
    public Type type;	//信息类型发送还是收到的
    /*
     * 信息类型
     * MSGIN：收到的信息
     * MSGOUT:发出的信息
     */
    public enum Type{
        MSGIN,MSGOUT
    }

    public MessageBean()
    {
    }

    public MessageBean(String msg,Date time,Type type)
    {
        super();
        this.msg = msg;
        this.time = time;
        this.type = type;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Date getTime()
    {
        return time;
    }

    public void setTime(Date time)
    {
        this.time = time;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }



}

