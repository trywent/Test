package com.syu.jni;
import android.util.Log;

public class JniI2c{
	private native int open(String path);
	private native int readRk(int addr,int reg);
	static{
        java.lang.System.loadLibrary("jin_i2c");
	}

	public void testJni(){
		Log.i("FYTTEST","test jni");
		open("/dev/i2c1");
     		readRk(0x40,0x40);   
	}

}
