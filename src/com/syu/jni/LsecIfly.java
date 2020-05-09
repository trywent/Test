package com.syu.jni;
import android.util.Log;

public class LsecIfly{
        //mode
        public static final int WORK_MODE_DEF = 0x0;	
	public static final int WORK_MODE_PERIPHERAL = 0x1;
	public static final int WORK_MODE_TOPLIGHT = 0x2;
	public static final int WORK_MODE_HOST = 0x3;
	public static final int WORK_MODE_PAD = 0x4;
	public static final int WORK_MODE_NB = 0x5;
	public static final int WORK_MODE_WB = 0x6;
	public static final int WORK_MODE_BT_NB = 0x7;
	public static final int WORK_MODE_BT_WB = 0x8;
	public static final int WORK_MODE_EAR = 0x9;
	public static final int WORK_MODE_NR_24K = 0x10;
        //function
    	public static final int FUNC_MODE_PASSBY = 0x0;	//所有功能关闭直接录音
	public static final int FUNC_MODE_NOISECLEAN = 0x1;	//降噪功能
	public static final int FUNC_MODE_PHONE = 0x2;	//通话回声消除功能
	public static final int FUNC_MODE_WAKEUP = 0x3;	//唤醒回声消除功能

	private native int setFunc(int func);
        private native int getFunc();
        private native int setMode(int mode);
        private native int getMode();
        private native int getVersion();

	/*static{
        java.lang.System.loadLibrary("lsec_ifly");
	}*/
        /*return、command[0]
                        - 2  二版（仅降噪）
                        - 3  三版（带唤醒、回声消除）
                        - 4  四版，当前主流版本  
                        - 0  未知，或I2C读取失败
                  command[1] - build号，例如10156，11022，14087等
        */ 
        public int getVer(){
            return getVersion();
        }

        public int setMicMode(int mode){
            return setMode(mode);
        }
        public int getMicMode(){
            return getMode();
        }
        /*return- succeed : 0; 
                  failed  : error code < 0 
        */ 
	public int setMicFunc(int mode){
            return setFunc(mode);
	}
        public int getMicFunc(){
            return getMode();
        }

}
