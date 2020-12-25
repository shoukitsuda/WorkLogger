package jp.kyutech.example.worklogger;

import java.util.Timer;
import java.util.TimerTask;

public class TimerController {
    public static void main(String[] args) {
        System.out.println("タスクを3秒後に実行されるようセットしました。");
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("タスクが実行されました。");
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);

    }

}

