// Starter switch for starting and stopping the recorder by hand
//
// Copyright (C) 2020  Masanobu UMEDA (umerin@ci.kyutech.ac.jp)
//
// $Id$

package jp.kyutech.example.worklogger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Starter switch class which implements ToggleButton.OnCheckedChangeListener.
 *
 * @author Masanobu UMEDA
 * @version $Revision$
 */

class TimerSwitch
        implements ToggleButton.OnCheckedChangeListener {
    private static final String LOGTAG = "TimerSwitch";
    private static final int VIBRATION_PERIOD = 1000; // 1000msec.
    private MainActivity activity = null;
    private WorkRecordManager recordManager = null;
    private ToggleButton button = null;
    private Drawable drawable_timer_stop = null;
    private Drawable drawable_timer_start = null;
    private Drawable drawable_timer_disabled = null;

    TimerSwitch(MainActivity activity, ToggleButton button, WorkRecordManager recordManager) {
        this.activity = activity;
        this.button = button;
        this.recordManager = recordManager;

        updateTimerView();

    }

    public static void timer(String[] args) {
        System.out.println("タスクを3秒後に実行されるようセットしました。");
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("タスクが実行されました。");
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);

    }

    void updateTimerView() {
        Log.d(LOGTAG, "updateTimerView():");
        if (button.isChecked()) {
            button.setBackground(drawable_timer_stop);
            button.setText("Stop working");
        } else {
            button.setBackground(drawable_timer_start);
            button.setText("Start Working");
        }
        button.setEnabled(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}

