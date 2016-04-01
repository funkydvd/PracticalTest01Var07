package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

import ro.pub.cs.systems.eim.practicaltest01var07.Constants;
/**
 * Created by student on 4/1/16.
 */
public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;
    private String fst;
    private String snd;
    private Random random = new Random();
    public ProcessingThread(Context context, String fst, String snd) {
        this.context = context;
        this.fst = fst;
        this.snd = snd;

    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + fst + " " + snd);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}