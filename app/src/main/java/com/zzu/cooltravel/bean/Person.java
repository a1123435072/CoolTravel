package com.zzu.cooltravel.bean;

/**
 * Created by xm on 2016/5/16.
 */
public class Person {
    private String number;//账号
    private String name;	//昵称
    private String gander;	//性别
    private String age;		//年龄
    private String job;		//职业
    private String liveNow;	//先居地
    private String status;	//目前状态（有技术/有项目/有资金）

    public Person()
    {
        super();
    }

    public Person(String number,String name,String gander,String age,String job,
                        String liveNow,String status)
    {
        super();
        this.number = number;
        this.name = name;
        this.gander = gander;
        this.age = age;
        this.job = job;
        this.liveNow = liveNow;
        this.status = status;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGander()
    {
        return gander;
    }

    public void setGander(String gander)
    {
        this.gander = gander;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getJob()
    {
        return job;
    }

    public void setJob(String job)
    {
        this.job = job;
    }

    public String getLiveNow()
    {
        return liveNow;
    }

    public void setLiveNow(String liveNow)
    {
        this.liveNow = liveNow;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    /*
     * 此方法并未用到，可随意修改
     */
    public String toString()
    {
        return number+name+gander+age+job+liveNow+status;
    }

    public String packingData() {
        String sp = ",";
        return number+ sp +name+ sp +gander+ sp +age+ sp +job+ sp +liveNow+ sp + status ;
    }

    public Person(String data) {
        String ss[] = data.split("[,]");
        this.number = ss[0];
        this.name = ss[1];
        this.gander = ss[2];
        this.age = ss[3];
        this.job = ss[4];
        this.liveNow = ss[5];
        this.status = ss[6];
    }
}

