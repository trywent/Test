/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.syu.test;

import com.syu.jni.JniI2c;
import com.syu.jni.LsecIfly;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import com.syu.jni.JniI2c;

import android.app.Activity;
import android.app.ActivityManager;
import static android.app.ActivityManager.StackId.PINNED_STACK_ID;
import android.app.IActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.KeyCharacterMap;
import android.view.InputDevice;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.telephony.TelephonyManager;
import android.hardware.input.InputManager;

public class TestActivity extends Activity {
    private static final String TAG="TEST";
    Context mContext;
    LogSave mylog;
    Process rtprocess;
    boolean saving = false;
    Button logbt;
    SeekBar myBar;
    private long mDownTime;
    OnTouchListener ml;
    AudioManager mAudioManager;
    boolean hdmion = false;
    Button hdmi;

   void sendEvent(int action, long when) {
        final KeyEvent ev = new KeyEvent(mDownTime, when, action, KeyEvent.KEYCODE_BACK, 0,
                0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0,
                KeyEvent.FLAG_FROM_SYSTEM | KeyEvent.FLAG_VIRTUAL_HARD_KEY,
                InputDevice.SOURCE_KEYBOARD);
        InputManager.getInstance().injectInputEvent(ev,
                InputManager.INJECT_INPUT_EVENT_MODE_ASYNC);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG,"keyCode down "+keyCode+" event "+event);
        return super.onKeyDown(keyCode,event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event){
        Log.i(TAG,"keyCode up"+keyCode+" event "+event);
        return super.onKeyUp(keyCode,event);
    }
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	//setVolume(this,AudioManager.STREAM_NOTIFICATION);
	//write2sd();
	//finish();
	mContext = this;


        ml = new OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                 final int action = event.getAction();
               switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mDownTime = SystemClock.uptimeMillis();
                        sendEvent(KeyEvent.ACTION_DOWN, mDownTime);
                        break;
                    case MotionEvent.ACTION_UP:
                        sendEvent(KeyEvent.ACTION_UP, mDownTime/*SystemClock.uptimeMillis()*/);
                        break;
                }
                return true;
            }

        };

        setContentView(R.layout.activity_main);
	final Button button1 = (Button) findViewById(R.id.button1);
	button1.setOnClickListener(mListener);	
	final Button button2 = (Button) findViewById(R.id.button2);
	button2.setOnClickListener(mListener);
	final Button button3 = (Button) findViewById(R.id.button3);
	button3.setOnClickListener(mListener);	
	final Button button4 = (Button) findViewById(R.id.button4);
	button4.setOnClickListener(mListener);	
        final Button button5 = (Button) findViewById(R.id.button5);
	button5.setOnClickListener(mListener);	
	logbt = button5;
        final Button button6 = (Button) findViewById(R.id.button6);
	button6.setOnClickListener(mListener);	
        final Button button7 = (Button) findViewById(R.id.button7);
	button7.setOnClickListener(mListener);
        final Button button8 = (Button) findViewById(R.id.button8);
	button8.setOnClickListener(mListener);
        hdmi = button8;	
	myBar = (SeekBar)findViewById(R.id.seekBar1);
	myBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){}
			public void onStartTrackingTouch(SeekBar seekBar){}
			public void onStopTrackingTouch(SeekBar seekBar){
				int val = seekBar.getProgress();
				Log.i(TAG,"seekbar set vol "+val);
				setVolume(seekBar.getContext(),AudioManager.STREAM_VOICE_CALL,val);
			}
		});
        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setTypeface(null,2);
        registerReceiver();

        Log.i(TAG,"deviceID:"+((TelephonyManager)mContext.getSystemService(TELEPHONY_SERVICE)).getDeviceId());
	//(new MyAudio()).start();
	//finish();
	//new JniI2c().testJni();
        mAudioManager = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
	Log.i(TAG,"onCreate extra "+getIntent().getStringExtra("test")+ " boolen: force "+getIntent().getBooleanExtra("force_pip",false));

    }
    protected void onNewIntent(Intent intent) {
	super.onNewIntent(intent);
	Log.i(TAG,"onNewIntent extra "+intent.getStringExtra("test")+ " boolen: force "+intent.getBooleanExtra("force_pip",false));
    }
    protected void onResume() {	
	super.onResume();
	Log.i(TAG,"onResume extra "+getIntent().getStringExtra("test")+ " boolen: force "+getIntent().getBooleanExtra("force_pip",false));
    }
    public void write2sd(){
	Log.i(TAG,"write to external.");
	File test = new File(Environment.getExternalStorageDirectory().getPath()+"/test.txt/");
	writeStr(test,new String("i write to external sd card !!!"));

	writeStr(new File("/mnt/sdcard/sdtest.txt"),new String("i write to sd card !!!"));
    }

    public void writeBytes(File file, byte[] bytes){
	   
        boolean success= true;
  	OutputStream out = null;
   	try {	  
	  file.createNewFile();	  
   	  out = new BufferedOutputStream(new FileOutputStream(file));
	  out. write (bytes, 0, bytes.length);	  
	}catch(Exception e){
          success= false;
	  Log.e(TAG,"wirte bytes failed.");
	  e.printStackTrace();
	}
   	finally {
   	 try{	 
          if (out != null)  out.close();
	  }catch(IOException e){}
    	 
	}  
	if(success)Log.i(TAG,"write "+ file.getPath()+" success!");
    }
 

    public void writeStr(File file,String str){
	FileWriter writer =null;

	try{
	   file.createNewFile();
	   writer = new FileWriter(file);
	   writer.write(str,0,str.length());
	}catch(Exception e){	   
	   Log.e(TAG,"write string failed.");
	   e.printStackTrace();
	}finally{
	   try{
	   if(writer!=null)		
		writer.close();
	   }catch(IOException e){}
	}
	Log.i(TAG,"write "+ file.getPath()+" success!");
    }

    private OnClickListener mListener = new OnClickListener() {
        public void onClick(View v) {
		int type=0;
		switch(v.getId()){
		case R.id.button1:type= AudioManager.STREAM_VOICE_CALL;
			break;
		case R.id.button2:type= AudioManager.STREAM_NOTIFICATION;
			break;
		case R.id.button3:
                //type= AudioManager.STREAM_ALARM;
                    startMapPip();
			break;
		case R.id.button4:
                    //type= AudioManager.STREAM_SYSTEM;
                    //makecall();
                    removePip();
		    //sendBroadcast();
			break;
                case R.id.button5:
                        if(saving) {
			    logbt.setText(R.string.bt5_start);
                            saving = false;
                            stoplog();
                            //stopSave();
                        }else{
			    logbt.setText(R.string.bt5_stop);
                            saving = true;
                            savelog();
                            //runSave();
                        }
			break;  
                case R.id.button6:clearLogcat();
		    break;
                case R.id.button7:saveDmesg();
                    break;
                case R.id.button8:
                    if(hdmion) {
                        hdmion = false;
                        setHdmi(hdmion);
                    }else{
                        hdmion = true;
                        setHdmi(hdmion);
                    }
                    hdmi.setText(hdmion?"on":"off");
                    break;
		default:break;
		}

                if(type!=0) {
                    setVolume(v.getContext(),type);
                    write2sd();
                }
        }
    };

    public void setVolume(Context ctx,int type){
	// AudioManager.STREAM_NOTIFICATION;//STREAM_MUSIC STREAM_VOICE_CALL
	AudioManager am = (AudioManager)ctx.getSystemService(Context.AUDIO_SERVICE);
	am.setStreamVolume(type,am.getStreamMaxVolume(type),0);
	//am.setStreamMute(type,false);
    }
    public void playSound(int type){
	int minBuffSize = AudioTrack.getMinBufferSize(44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_8BIT);
	AudioTrack track = new AudioTrack(type,44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_8BIT,
                2 * minBuffSize, AudioTrack.MODE_STATIC);
        byte data[] = new byte[minBuffSize];
        track.write(data, 0, data.length);
	track.play();
        track.release();
    }	

//seekbar
    public void setVolume(Context ctx,int type,int vol){
	AudioManager am = (AudioManager)ctx.getSystemService(Context.AUDIO_SERVICE);
	am.setStreamVolume(type,vol,0);
    }
 


// logcat
    public void savelog(){
        Thread thread = new Thread(){
                public void run() {
                    mylog = new LogSave();
                    mylog.startSave();
                }
            };
        try{
            thread.start();
        }catch (IllegalThreadStateException e){
            e.printStackTrace();
        }
    }
    public void stoplog(){
        if(mylog!=null) {
            mylog.stopSave();
        }
    }
    public void clearLogcat(){
        new LogSave().clearLog();
    }
    public void saveDmesg(){
        new LogSave().dmesg();
    }
    public void runSave(){
        Thread thread = new Thread(){
                public void run() {
                    try{
                        rtprocess = Runtime.getRuntime().exec("logcat -v time > /sdcard/rt_logcat.txt");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            };
        try{
            thread.start();
        }catch (IllegalThreadStateException e){
            e.printStackTrace();
        }
    }
    public void stopSave(){
        if(rtprocess!=null) {
            rtprocess.destroy();
        }
    }
	
    public void setHdmi(boolean on){
        if(mAudioManager==null) {
            return ;
        }
        //AudioManager.DEVICE_OUT_HDMI
	mAudioManager.setWiredDeviceConnectionState(AudioManager.DEVICE_IN_BUILTIN_MIC,on?1:0,"fyt5011","fyt");
        /*mAudioManager.setWiredDeviceConnectionState(AudioManager.DEVICE_IN_WIRED_HEADSET,on?1:0,"fyt5011","fyt");
        mAudioManager.setWiredDeviceConnectionState(AudioManager.DEVICE_OUT_WIRED_HEADSET,on?1:0,"fyt5011","fyt");*/
    }
    void makecall(){
		startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+10086)));
	}
    void startMapPip(){
        SystemProperties.set("sys.lsec.force_pip","true");
        Intent intent = new Intent();
        //ComponentName cn = new ComponentName("com.autonavi.amapauto","com.autonavi.auto.remote.fill.UsbFillActivity"); 
        ComponentName cn = new ComponentName("ru.yandex.yandexnavi","ru.yandex.yandexnavi.core.NavigatorActivity"); 

        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("force_pip",true);
        startActivity(intent);
    }
    void removePip(){
        IActivityManager mActivityManager = ActivityManager.getService();
        try{
            mActivityManager.removeStack(PINNED_STACK_ID);
        }catch (RemoteException e){
            Log.i(TAG,"err",e);
        }
    }
    void sendBroadcast(){
		mContext.sendBroadcast(new Intent().setAction("com.test.test.abcd"));
		Log.i(TAG,"send abcd");
	}
    void registerReceiver(){
        BroadcastReceiver receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
             Log.i(TAG,"receive "+intent);
            }   
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.test.test.abcde");
        mContext.registerReceiver(receiver, filter);
    }
    public void click(){
        //new JniI2c().testJni();
        LsecIfly ifly = new LsecIfly();
	Log.i(TAG,"ifly versioin:"+ifly.getVer());//+" mode:"+ifly.getMicMode()+" func:"+ifly.getMicFunc());
    }
}
