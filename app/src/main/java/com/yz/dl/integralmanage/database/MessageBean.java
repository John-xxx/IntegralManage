package com.yz.dl.integralmanage.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by chaman on 2017/10/12.
 */

@Entity
public class MessageBean {
    @Id
    private Long id;
    private String sendname;
    private String msgcon;
    private String date;
    private int msgstate;
    private String userid;
    private String title;
    @Generated(hash = 2008017906)
    public MessageBean(Long id, String sendname, String msgcon, String date,
            int msgstate, String userid, String title) {
        this.id = id;
        this.sendname = sendname;
        this.msgcon = msgcon;
        this.date = date;
        this.msgstate = msgstate;
        this.userid = userid;
        this.title = title;
    }
    @Generated(hash = 1588632019)
    public MessageBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSendname() {
        return this.sendname;
    }
    public void setSendname(String sendname) {
        this.sendname = sendname;
    }
    public String getMsgcon() {
        return this.msgcon;
    }
    public void setMsgcon(String msgcon) {
        this.msgcon = msgcon;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getMsgstate() {
        return this.msgstate;
    }
    public void setMsgstate(int msgstate) {
        this.msgstate = msgstate;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
