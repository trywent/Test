/************************************************************
  Copyright (C), 2012-2017, FYT Tech. Co., Ltd.
  FileName: touchnative.java
  Author: wusheng     Version: v1.0     Date:2015-11-5
  Description:    
  Version:       v1.0   
  Function List:   
    
   
 
***********************************************************/

package com.syu.jni;

public class TouchNative
{
	static 
	{
		System.loadLibrary("sqltouch");
	}
	public	static    native    int     native_touch_open();//操作入口
	public	static    native    int     native_get_resolutions(int[] arrScreenRes,int[] arrTouchRes);
	public	static    native    int     native_get_points(int[] arrXcoor,int[] arrYcoor);
	public	static    native    void    native_touch_close(int fd);//操作退出
	public	static    native    int     native_get_coordinate(int[] arrXcoor,int[] arrYcoor);
	public	static    native    int     native_calibrate_touch(int[] arrXcoor,int[] arrYcoor);
	
//keyType : 0: areakey   1: special key 
//arrKeyCfgs[0]:keyValue, 
//arrKeyCfgs[1]:keyX, arrKeyCfgs[2]:keyY,
//arrKeyCfgs[3]:keyX_delta, arrKeyCfgs[4]:keyY_delta,
	public	static    native    int     native_set_key(int keyType,int index, int[] arrKeyCfgs);
	public	static    native    int     native_get_key(int keyType,int index, int[] arrKeyCfgs);
	public	static    native    int     native_save_config( );

	//get x2y,    left right,top bottom border data
	//arrTconfigs[0]: isNeedX2Y
	//arrTconfigs[1]: left_border
	//arrTconfigs[2]: right_border
	//arrTconfigs[3]: top_border
	//arrTconfigs[4]: bottom_border	
	public	static    native    int      native_get_tconfig(int[] arrTconfigs);
	public	static    native    int      native_set_tconfig( int[] arrTconfigs);

   //get & set
   //type 0: calibrate mode set  &get
   //       1: calibrate status set &get
   //       2: x delta value   //common areakey use
   //       3: y delta value   //common areakey use

	public	static    native    int      native_set_control(int type, int mode);
	public	static    native    int      native_get_control( int type );
   
/*
	

		{ "native_set_control",             	"(II)I",       (void *)android_set_control},	
		{ "native_get_control",             	"(I)I",       (void *)android_get_control},

*/
	

	public static final int TOUCH_CAL_MODE 	= 	0xc1;
	public static final int  TOCH_STUDY_MODE = 	0xc2; 
	
	public static final int STATUS_UNKNOW 		=  	0;
	public static final int STATUS_CALIBRATED  	=   	1; 
	public static final int STATUS_KEY_STUDYED   	= 	2;

	//STEP1 OPEN native_touch_open int
	//STEP2 native_get_points 返回三个点的位置，显示位置
	//STEP3 native_get_resolutions 返回显示的分辨率（X1，Y1SCREEN）(X1，Y2TOUCH),显示出来
	//step4 native_get_coordinate,获取触摸坐标（x1,y1）（x2,y2）（x3,y3）
	//step5 native_calibrate_touch (x1,x2,x3)(y1,y2,y3) 0:OK -1:重新学习 -2：触摸屏参数异常，联系触摸屏 -6:传值问题 1：坐标可能偏
	//step6 native_touch_close(int)

}
