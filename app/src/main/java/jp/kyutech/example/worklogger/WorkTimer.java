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

import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;


/**
 * Starter switch class which implements ToggleButton.OnCheckedChangeListener.
 *
 * @author Masanobu UMEDA
 * @version $Revision$
 */

public class WorkTimer extends AppCompatActivity {
    private static final String LOGTAG = "Timer";
    private MainActivity activity = null;
    private WorkRecordManager recordManager = null;
    private ToggleButton button = null;
    private Drawable drawable_timer_stop = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer);
        Button startButton = findViewById(R.id.start_button);
        Button returnButton = findViewById(R.id.stop_button);
//        returnButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

//        lambda式
        startButton.setOnClickListener(v -> startTimer());
        returnButton.setOnClickListener(v -> finish());
    }

    protected void
    Timer(MainActivity activity,
          ToggleButton button,
          WorkRecordManager recordManager) {

        Log.d(LOGTAG, "timer");

        this.activity = activity;
        this.button = button;
        this.recordManager = recordManager;

        startTimer();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d(LOGTAG, "onCheckedChanged():" + isChecked);
        String error_message = null;
        try {
            recordManager.updateWorkRecordBy(isChecked);
        } catch (Exception ex) {
            Log.e(LOGTAG, ex.getMessage(), ex);
            String title =
                    activity.getResources().getString(R.string.dialog_alert_title);
            String message = MessageFormatter.getErrorReason(ex);
            ErrorFragment.showErrorDialog(activity, title, message);
        }
        activity.updateView();
    }

    protected void startTimer() {
        Log.d(LOGTAG, "startTimer():");
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("タスクが実行されました。");
            }
        };
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(task, 300000);
        System.out.println("猫飼いたい");
    }
}

