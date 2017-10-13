package com.yz.dl.integralmanage.utils.tasks;

/**
 * Created by chaman on 2017/10/13.
 */

public class PriorityRunnable extends PriorityObject<Runnable> implements Runnable {

    public PriorityRunnable(Priority priority, Runnable obj) {
        super(priority, obj);
    }

    @Override
    public void run() {
        this.obj.run();
    }
}
