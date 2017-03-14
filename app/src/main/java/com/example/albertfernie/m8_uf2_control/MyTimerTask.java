package com.example.albertfernie.m8_uf2_control;

import android.content.Context;
import android.os.Handler;

import java.util.TimerTask;

/**
 * Created by albertfernie on 28/02/2017.
 */

class MyTimerTask extends TimerTask {
    Handler handler = new Handler();
    Context context;
    GView gview;

    public MyTimerTask(GView gview){
        this.gview=gview;
    }
    public void run() {
        handler.post(new Runnable() {
            public void run() {
                gview.taskTimer();
            }
        });
    }
}
