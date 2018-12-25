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

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileReader;
import java.io.FileWriter;

import android.app.Activity;
import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogSave {
    private static final String TAG="TEST";
    Process process;
    boolean save= false;
    InputStream in;
    OutputStream out;

   /* static{
       java.lang.System.loadLibrary("test_jni");
    }*/
    private native int savekmesg();
    private native int test();
    public void startSave(){
        new SaveThread().start();
    }
    public class SaveThread extends Thread {
        public void run(){
            try {
                Log.i(TAG,"startSave");
                process = new ProcessBuilder()
                          .command("/system/bin/logcat", "-v","time")
                          .redirectErrorStream(true)
                          .start();
                in = process.getInputStream();
                out = process.getOutputStream();
                save = true;
                saveToFile(new File("/mnt/sdcard/logcat.txt"),in);
            } catch (Exception e) {
                e.printStackTrace();
                save = false;
            } finally {
                if (process!=null)
                    process.destroy();
            }
        }
    }
    public void stopSave(){
        Log.i(TAG,"topSave");
        save = false;
        /*if(process!=null) {
            process.destroy();
        }*/
    }
    public void clearLog(){
        new ClearThread().start();
    }
    public class ClearThread extends Thread {
        public void run(){
            try {
                Log.i(TAG,"clear logcat");
                process = new ProcessBuilder()
                          .command("/system/bin/logcat", "-c")
                          .redirectErrorStream(true)
                          .start();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process!=null)
                    process.destroy();
            }
        }
    }

    public void saveToFile(File file, InputStream is){

        boolean success= true;
        OutputStream out = null;
        byte[] buff;
        int num;
        try {
            Log.i(TAG,"create"+file.getPath());
            file.delete();
            file.createNewFile();   
            out = new BufferedOutputStream(new FileOutputStream(file));
            buff = new byte[512];
            while (save) {
                num=is.read(buff,0,512);
                if (num!=-1) {
                    out.write(buff,0,num);
                }
            }
        } catch (Exception e) {
            success= false;
            Log.e(TAG,"wirte bytes failed.");
            e.printStackTrace();
        } finally {
            try {
                if (out != null)  out.close();
                if (in !=null)in.close();
                process.destroy();
            } catch (IOException e) {
            }

        }  
        Log.i(TAG,"write "+ file.getPath()+(success?" success!":" failed!"));
    }

    public void dmesg(){
        new Thread(){

            public void run() {
               int ret= savekmesg();
               Log.i(TAG,"write "+(ret==0?" success!":"failed"));
               /*try{
                   Runtime.getRuntime().exec("cat /proc/kmesg > /sdcard/kmesg.txt");
                   Log.i(TAG,"write "+" success!");
               }catch (IOException e){
                   e.printStackTrace();
               }*/
            }
        }.start();
    }


}
