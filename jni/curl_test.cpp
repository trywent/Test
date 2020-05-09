//
// Created by humor's macbook pro on 2020-04-23.
//
//#include "com_txznet_overseasdkdemo_jni_CurlTest.h"
#include <android/log.h>
#include <pthread.h>
#include "curl/curl.h"
#include <string>
#include <thread>
#include <unistd.h>
#include <jni.h>

#define TAG "TXZ-JNI"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型*/
bool b_stop = false;

size_t my_write_func(void *ptr, size_t size, size_t nmemb, void* userdata)
{
  std::string* s = (std::string*)userdata;
  size_t writeSize = size*nmemb;
  s->append((char*)ptr, writeSize);
  return writeSize;
}

void* runLoop(void* args) {
  LOGD("loop thread run");
  while (true)
  {
    if (b_stop)
    {
      LOGD("loop thread stop");
      break;
    }
    CURL *curl = NULL;
    curl_global_init(CURL_GLOBAL_ALL);
    curl = curl_easy_init();

    do {

      if (curl == NULL)
      {
        break;
      }

      curl_easy_setopt(curl, CURLOPT_URL, "https://baidu.com");
      curl_easy_setopt(curl, CURLOPT_POST, 1);//设置为非0表示本次操作为POST

//    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, jsonParam.c_str());
//    curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, jsonParam.size());//设置上传json串长度,这个设置可以忽略

      std::string respBody;
      std::string respHeader;

//      curl_easy_setopt(curl, CURLOPT_WRITEDATA, &respBody);
//    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, my_write_func);
//      curl_easy_setopt(curl, CURLOPT_HEADERDATA, &respHeader);
//    curl_easy_setopt(curl, CURLOPT_HEADERFUNCTION, my_write_func);
//    curl_easy_setopt(curl, CURLOPT_NOPROGRESS, 0L);
      curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, false);
      curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, false);
      CURLcode res = curl_easy_perform(curl);
//    curl_slist_free_all(headers);
      if (res  != CURLE_OK)
      {
        LOGD("curl_easy_perform error:%d errstr:%s\n", res,curl_easy_strerror(res));
        break;
      }

      //获取响应码
      long responseCode = -1;
      res = curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &responseCode);

      if (responseCode != 200)
      {
        LOGD("responseCode:%ld", responseCode);
      }
      LOGD("resp body : %s\n", respBody.c_str());
    } while (false);

    if (curl != NULL){
      curl_easy_cleanup(curl);
    }
    curl_global_cleanup();
    break;
//    usleep(10000);
  }
  return nullptr;
}




extern "C" JNIEXPORT jint JNICALL Java_com_syu_test_TestActivity_loopCurl(JNIEnv *env, jclass jnicom)
{
  LOGD("start loop curl");
  b_stop = false;
  pthread_t thread_1;
  pthread_create( &thread_1, nullptr, runLoop, nullptr);
  pthread_detach(thread_1);
  return (jint)1;
}

extern "C" JNIEXPORT jint JNICALL Java_com_syu_test_TestActivity_stopLoopCurl(JNIEnv *env, jclass jnicom)
{
  LOGD("stop loop curl");
  b_stop = true;
  return (jint)1;
}


