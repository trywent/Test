#define LOG_TAG "TEST"
#include "JNIHelp.h"
#include "jni.h"
//#include "test.h"
#include "android_runtime/AndroidRuntime.h"
#include "android_runtime/Log.h"
#include "utils/Log.h"
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

jint com_syu_test_LogSave_savekmesg(JNIEnv *env, jobject object){
   return system("cat /proc/kmsg > /sdcard/dmesg.txt");
   /*int input;
   int output;
   char buf[255];
   int num;
   input = open("/proc/kmsg",O_RDONLY);
   if (input==-1) {
      ALOGE("open kmsg failed. %s",strerror(errno));
   }
   output = open("/sdcard/kmsg.txt",O_CREAT|O_WRONLY|O_TRUNC,0777);
   if (output==-1) {
      ALOGE("create kmesg.txt.");
   }
   if (input!=-1&&output!=-1) {
      while ((num=read(input,buf,255))>=0) {
         write(output,buf,num);
         if (num==0) {
            break;
         }
      }
      close(input);
      close(output);
      sync();
      return 0;
   }
   return -1;*/
}

static JNINativeMethod sMethods[] = {
    {"savekmesg", "()I", (void*)com_syu_test_LogSave_savekmesg},
};

int register_test_jni(JNIEnv* env)
{
    return jniRegisterNativeMethods(env, "com/syu/test/LogSave",
                                    sMethods, NELEM(sMethods));
}

jint JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    JNIEnv *e;
    int status;

    // Check JNI version
    if (jvm->GetEnv((void **)&e, JNI_VERSION_1_6)) {
        ALOGE("JNI version mismatch error");
        return JNI_ERR;
    }
    if ((status = register_test_jni(e)) < 0) {
        ALOGE("jni adapter service registration failure, status: %d", status);
        return JNI_ERR;
    }
    return JNI_VERSION_1_6;
}


