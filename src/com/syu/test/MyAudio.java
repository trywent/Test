package com.syu.test;

import android.app.Activity;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.util.Log;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.AudioFormat;
import android.media.AudioManager;

import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.IOException;


public class MyAudio extends Thread
{
    private static final String LOG_TAG = "TEST";

    private AudioRecord mRecorder = null;
    private AudioTrack mTrack = null;

    public void run(){	
	//startRecording();
	//stopRecording();	
	startPlaying();
	stopPlaying();
    }
    private void startRecording() {	
	int i =3000;
	byte[] buffer= new byte[512];
	int bufSize = AudioRecord.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO,
			AudioFormat.ENCODING_PCM_16BIT);
        mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC,44100,AudioFormat.CHANNEL_IN_MONO,
		AudioFormat.ENCODING_PCM_16BIT,bufSize);	
        try {
	    Log.i(LOG_TAG, "start record");
            mRecorder.startRecording();
	    while(i--!=0){
	    mRecorder.read(buffer,0,512);
	    }	    
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, "record failed");
        }
    }

    private void stopRecording() {
	try{
	Log.i(LOG_TAG, "stop record");
        mRecorder.stop();
	mRecorder.release();
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, "stop record failed");
        }
        mRecorder = null;
    }

    private void startPlaying() {	
	int i =3000;
	byte[] buffer= new byte[512];
	for(int j=512;j>0;j--)
		buffer[j-1]=0;
	
	int bufSize = AudioTrack.getMinBufferSize(44100, AudioFormat.CHANNEL_IN_MONO,
			AudioFormat.ENCODING_PCM_16BIT);
        mTrack = new AudioTrack(AudioManager.STREAM_MUSIC ,44100,AudioFormat.CHANNEL_OUT_MONO,
		AudioFormat.ENCODING_PCM_16BIT,bufSize,AudioTrack.MODE_STREAM);	
        try {
	    Log.i(LOG_TAG, "start play");
            mTrack.play();
	    while(i--!=0){
	    mTrack.write(buffer,0,512);
	    }	    
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, "play failed");
        }
    }

    private void stopPlaying() {
	try{
	Log.i(LOG_TAG, "stop play");
        mTrack.stop();
	mTrack.release();
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, "stop play failed");
        }
        mTrack = null;
    }
}

