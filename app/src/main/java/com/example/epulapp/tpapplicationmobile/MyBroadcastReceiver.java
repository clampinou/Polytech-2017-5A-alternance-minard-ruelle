package com.example.epulapp.tpapplicationmobile;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

/**
 * Created by Epulapp on 29/11/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private static final int NOTIF_JEU = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO : Générer une notif
        Log.d("log Broadcast Receive", String.valueOf(intent.getExtras().get("data")));

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Jeuuuuuuu")
                        .setContentText(String.valueOf(intent.getExtras().get("data")));
        Intent resultIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(NOTIF_JEU, mBuilder.build());
    }
}
