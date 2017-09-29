package com.yz.dl.integralmanage.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/31.
 */

public class MessageBean implements Serializable {
    private  int  id;
    private  String  sendname;
    private  String  msgcon;
    private  String  date;
    private  int  msgstate;
    private String  userid;
    private  String  title;
    public  int  getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUserid() {
        return userid;
    }
    public  String  getSendname(){
        return  sendname;
    }
    public  String  getMsgcon(){
        return msgcon;
    }
    public  String  getDate(){
        return  date;
    }
public   int  getMsgstate(){
    return   msgstate;
}
public  void setId(int id){
    this.id = id;
}
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public  void setSendname(String sendname){
    this.sendname = sendname;
}
public  void  setMsgcon(String msgcon){
    this.msgcon = msgcon;
}

    public void setTitle(String title) {
        this.title = title;
    }

    public  void setDate(String date){
    this.date = date;
}
public  void  setMsgstate(int msgstate){
    this.msgstate  = msgstate;
}


}
