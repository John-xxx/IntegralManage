package com.yz.dl.integralmanage.utils;

import java.util.ArrayList;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class InrgralArrayList extends ArrayList<Object> {

    public String getString(int index) {
        return (String) get(index);
    }

    public boolean getBoolean(int index) {
        return (Boolean) get(index);
    }

    public int getInt(int index) {
        return (Integer) get(index);
    }

    public float getFloat(int index) {
        return (Float) get(index);
    }

    public double getDouble(int index) {
        return (Double) get(index);
    }

    public IntegralHashMap getSAFHashMap(int index) {
        return (IntegralHashMap) get(index);
    }

    public InrgralArrayList getSAFArrayList(int index) {
        return (InrgralArrayList) get(index);
    }

}