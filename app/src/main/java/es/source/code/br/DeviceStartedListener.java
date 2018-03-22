package es.source.code.br;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import es.source.code.service.UpdateService;

/**
 * Created by Cristo on 2017/10/29.
 */

public class DeviceStartedListener extends BroadcastReceiver{


    private static final String TAG = "BootBroadcastReceiver";
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Boot this system , BootBroadcastReceiver onReceive()");

        if (intent.getAction().equals(ACTION_BOOT)) {
            Log.i(TAG, "BootBroadcastReceiver onReceive(), Do thing!");

           context.startService(new Intent(context,UpdateService.class));

        }
    }
}
