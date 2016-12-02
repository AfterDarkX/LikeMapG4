package com.example.asus.likemap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by octoboy on 22/11/2557.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        Intent i = new Intent(context, ShowEvent.class);
        Bundle b = arg1.getExtras();
        String t = b.getString("Text");
        int p = b.getInt("pic");
        i.putExtra("Text",t);
        i.putExtra("pic",p);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }


}