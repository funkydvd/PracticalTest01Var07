package ro.pub.cs.systems.eim.practicaltest01var07;

/**
 * Created by student on 4/1/16.
 */
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var07Service extends Service {

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String fst = intent.getStringExtra("et1");
        String snd = intent.getStringExtra("et2");
        processingThread = new ProcessingThread(this, fst, snd);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }

}