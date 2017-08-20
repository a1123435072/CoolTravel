package com.zzu.cooltravel.bean;




import cn.bmob.v3.BmobUser;


/**
 * Created by xm on 2016/5/15.
 */
public class User extends BmobUser{


    private String cpassword;
//    private String email;


    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

}
