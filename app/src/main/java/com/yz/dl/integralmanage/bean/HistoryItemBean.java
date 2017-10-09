package com.yz.dl.integralmanage.bean;

/**
 * 历史积分列表实体类
 * Created by chaman on 2017/10/9.
 */

public class HistoryItemBean {
    String dateTime;
    String typeName;
    String typeNum;
    int point;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(String typeNum) {
        this.typeNum = typeNum;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
