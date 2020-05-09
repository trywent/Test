LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
    test.cpp\
    curl_test.cpp

LOCAL_C_INCLUDES += \
    $(JNI_H_INCLUDE) \

LOCAL_SHARED_LIBRARIES := \
    libandroid_runtime \
    libnativehelper \
    libcutils \
    libutils \
    liblog \
    libcurl
#LOCAL_CFLAGS += -O0 -g

LOCAL_MODULE := libtest_jni
LOCAL_MODULE_TAGS := optional

include $(BUILD_SHARED_LIBRARY)
