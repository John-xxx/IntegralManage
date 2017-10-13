package com.yz.dl.integralmanage.utils.tasks;

/**
 * Created by chaman on 2017/10/13.
 */

public interface TaskHandler {

    boolean supportPause();

    boolean supportResume();

    boolean supportCancel();

    void pause();

    void resume();

    void cancel();

    boolean isPaused();

    boolean isCancelled();
}
