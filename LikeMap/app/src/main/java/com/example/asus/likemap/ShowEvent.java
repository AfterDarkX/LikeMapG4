package com.example.asus.likemap;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowEvent extends Activity implements OnClickListener {
	
	PowerManager pm;
	WakeLock wl;
	KeyguardManager km;
	KeyguardLock kl;
	Ringtone r;
	ImageView p;
	
	Button btnStop;
	 	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        Log.i("ShowEvent", "onCreate() in DismissLock");
			setContentView(R.layout.sec);

			p = (ImageView)findViewById(R.id.pic);
			Bundle bundle = getIntent().getExtras();
			String text = bundle.getString("Text");
			int pic = bundle.getInt("pic");
			TextView t = (TextView)findViewById(R.id.setText);
			t.setText(text);
			p.setImageResource(pic);
	        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	        km=(KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
	        kl=km.newKeyguardLock("ShowEvent");
	        wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP| PowerManager.ON_AFTER_RELEASE, "ShowEvent");
	        wl.acquire(); //wake up the screen
	        kl.disableKeyguard();

	        
	        btnStop = (Button)findViewById(R.id.btnStop);
	        btnStop.setOnClickListener(this);
	        
	 }
	 	
	 	@Override
		public void onClick(View v) {
	 		if(v.getId() == R.id.btnStop){
	 			clock.listValue.remove(0);
	 			this.finish();
	 		}			
		}
	 	
	 	@Override
	 	protected void onResume() {
	 	   
	 	    super.onResume();
	 	    wl.acquire();//must call this!
	 	   	Uri notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
	        	if(notif==null){ 
				notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
				if(notif==null){ 
					notif = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				}
	        	}
			r = RingtoneManager.getRingtone(getApplicationContext(), notif);
			r.play();
	 	}
	 	
	 	@Override
		public void onPause(){
			super.onPause();
			wl.release();
			if(r.isPlaying()){
				r.stop();
			}
		}

}
