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

class Time extends AppCompatActivity {
    private static final String LOGTAG = "Timer";
    private static final int VIBRATION_PERIOD = 1000; // 1000msec.
    private MainActivity activity = null;
    private WorkRecordManager recordManager = null;
    private ToggleButton button = null;
    private Drawable drawable_timer_stop = null;
    private Drawable drawable_timer_start = null;
    private Drawable drawable_timer_disabled = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer);

        Button returnButton = findViewById(R.id.stop_button);
//        returnButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

//        lambda式
        returnButton.setOnClickListener(v -> finish());
    }

    protected void
    Timer(MainActivity activity,
          ToggleButton button,
          WorkRecordManager recordManager) {
        this.activity = activity;
        this.button = button;
        this.recordManager = recordManager;

        Bitmap bitmap_timer_stop =
                BitmapFactory.decodeResource(activity.getResources(),
                        R.drawable.starter_stop);
        drawable_timer_stop =
                new BitmapDrawable(activity.getResources(), bitmap_timer_stop);

        updateTimerView();
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

    void updateTimerView() {
        Log.d(LOGTAG, "updateTimerView():");
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("タスクが実行されました。");
                button.setBackground(drawable_timer_stop);
                button.setText("Stop working");
                button.setEnabled(true);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }
}

