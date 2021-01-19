// Starter switch for starting and stopping the recorder by hand
//
// Copyright (C) 2020  Masanobu UMEDA (umerin@ci.kyutech.ac.jp)
//
// $Id$

package jp.kyutech.example.worklogger;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ToggleButton;

import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.os.Handler;
import android.widget.Toast;


/**
 * Starter switch class which implements ToggleButton.OnCheckedChangeListener.
 *
 * @author Masanobu UMEDA
 * @version $Revision$
 */

public class WorkTimer extends AppCompatActivity {
    private LogLister logLister = null;

    private static final String LOGTAG = "Timer";
    private MainActivity activity = null;
    private WorkRecordManager recordManager = null;
    private ToggleButton button = null;
    private Drawable drawable_timer_stop = null;

    private EditText inputTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timer);
        inputTime = findViewById(R.id.input_time);

        Button startButton = findViewById(R.id.start_button);
        Button returnButton = findViewById(R.id.stop_button);

        startButton.setOnClickListener(v -> {
            String workTime = inputTime.getText().toString();
            long time = Long.valueOf(workTime);
            if (workTime.equals("")) {
                time = 15 * 60000;
            }
            time = time * 60000;
            startTimer(time);

        });
        returnButton.setOnClickListener(v -> finish());


    }

    protected void startTimer(long workTime) {
        Log.d(LOGTAG, "startTimer():");
        TimerTask task = new TimerTask() {
            public void run() {
                onDialog();

                //ここにWorkRecordDataBaseに作業時間を記録するタスクを記述する
                System.out.println("タスクが実行されました。");

            }
        };
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(task, workTime);
    }

    final Handler handler = new Handler();

    protected void onDialog() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        AlertDialog.Builder builder;
                        builder = new AlertDialog.Builder(WorkTimer.this);
                        Log.d(LOGTAG, "timer finish");
                        builder.setMessage("目標時間になりました！")
                                .setPositiveButton("home", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getApplication(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                        builder.show();
                    }
                });
            }
        }).start();
    }
}



