package com.syu.jni;

public class SyuJniNative {
	private static final SyuJniNative INSTANCE = new SyuJniNative();
	static {
		System.loadLibrary("syu_jni");
	}
	
	private SyuJniNative() {
	}
	
	public static SyuJniNative getInstance() {
		return INSTANCE;
	}
	
	public native int syu_jni_command(int type, Object inparam, Object outparam);
	
	// 参数部分
	public static final int JNI_EXE_CMD_0_TEST 	 			= 0;	// 测试接口
	public static final int JNI_EXE_CMD_1_BACKCAR_MIRROR 	= 1;	// 倒车镜像
	public static final int JNI_EXE_CMD_2_SOUND_MIX		 	= 2;	// GPS混音，8288用到
	public static final int JNI_EXE_CMD_3_ENCARPTION_RESULT	= 3;	// 请求加密IC验证结果
	public static final int JNI_EXE_CMD_4_AUDIO_STATE		= 4;	// 请求ARM是否有声音输出
	public static final int JNI_EXE_CMD_5_TURNOFF_LCDC		= 5;	// 关闭屏CLOCK
	public static final int JNI_EXE_CMD_6_MUTE_AMP			= 6;	// 静音功放
	public static final int JNI_EXE_CMD_7_AMP_MUTE_STATE	= 7;	// 获取功放静音状态
	public static final int JNI_EXE_CMD_8_RESET_GPS			= 8;	// 复位GPS
	public static final int JNI_EXE_CMD_9_POWERON_SCREEN	= 9;	// 开关屏幕背光
	public static final int JNI_EXE_CMD_10_LITTLE_HOM		= 10;	// 开关小喇叭
	public static final int JNI_EXE_CMD_11_VIDEO_NTSC_PAL	= 11;	// NP制通知底层 0：N制 1：P制
	public static final int JNI_EXE_CMD_14_SET_BACKCAR_TPYE	= 14;	// 设置倒车类型 0：cvbs 1：usb （后视镜用的）2：360 全景（现在加的）
	public static final int JNI_EXE_CMD_15_GET_BACKCAR_TPYE	= 15;	// 设置倒车类型
	public static final int JNI_EXE_CMD_16_SET_LED_COLORS 	= 16;	//设置三色灯接口：设置三色灯的颜色
	public static final int JNI_EXE_CMD_17_GET_LED_COLORS	= 17;	//获取三色灯颜色接口：获取三色灯的颜色
	public static final int JNI_EXE_CMD_19_SET_AIRPLANE_MODE = 19;	// 设置飞行模式接口
	public static final int JNI_EXE_CMD_22_SET_VIDEO_SYS_MODE= 22;	// 设置1是逐行，0是隔行
	public static final int JNI_EXE_CMD_23_GET_VIDEO_SYS_MODE= 23;	// 1是逐行，0是隔行
	public static final int JNI_EXE_CMD_24_RESET_8288A		= 24;	// 复位8288A
	public static final int JNI_EXE_CMD_25_GET_VIDEO_MODE 	= 25;	// 获取NP制式 0：N制 1：P制
	public static final int JNI_EXE_CMD_26_GET_VIDEO_SIGNAL_ON=26;  // 获取信号状态
	public static final int JNI_EXE_CMD_29_ACC_STATE_TO_BSP	= 29;	// ACC状态通知BSP
	public static final int JNI_EXE_CMD_31_FAN_EN  			= 31;	// 控制风扇开关
	public static final int JNI_EXE_CMD_32_GET_BOOT_REVERSE_STATUS= 32;	// BSP倒车状态
	public static final int JNI_EXE_CMD_33_RESET_VIDEOIC	= 33;	// 复位视频IC
	public static final int JNI_EXE_CMD_35_SET_AUDIO_SAMPLE_RATE= 35;// 48KHZ（非ARM通道） 44.1KHZ（ARM通道）
	public static final int JNI_EXE_CMD_50_FM_ANT_EN   		= 50;	// 控制FM天线电源开关
	
	public static final int JNI_EXE_CMD_101_PARAMA_READ		= 101;
	public static final int JNI_EXE_CMD_104_WRITE_GAMMA 	= 104;	// 保存屏幕校准数据
	
	public static final int JNI_EXE_CMD_147_CH7026_CONTROL = 147;
	
	public static final int JNI_EXE_CMD_148_READ_APPDATE 	= 148;
	public static final int JNI_EXE_CMD_149_WRITE_APPDATE 	= 149;
	public static final int JNI_EXE_CMD_150_GET_WRITE_FLASH_PROCESS_PID = 150;
	public static final int JNI_EXE_CMD_151_SET_WRITE_FLASH_PROCESS_PID_FLAG = 151;
	public static final int JNI_EXE_CMD_153_GSENSOR_POWER_ONOFF = 153;
	public static final int JNI_EXE_CMD_251_Normal_IO_Set = 251; // 通用IO配置输入或输出和电平，看函数使用说明，2018.07.21 Add by XiaoHuiZhan
	public static final int JNI_EXE_CMD_252_Normal_IO_Get = 252; // 通用IO电平控制，看函数使用说明，2018.07.21 Add by XiaoHuiZhan
	//public static final int JNI_EXE_CMD_253_USB_Set = 253; //7731平台设置使能USB端口
	public static final int JNI_EXE_CMD_254_USB_Get = 254; //7731平台读取使能USB端口
	public static final int JNI_EXE_CMD_255_USB_Set = 255; //7731平台设置使能USB端口
	
	public static final int CH7026_CMD_READALL   	=1;
	public static final int CH7026_CMD_W_VPOSITION  =2;
	public static final int CH7026_CMD_W_HPOSITION  =3;
	public static final int CH7026_CMD_W_VSYNC	    =4;
	public static final int CH7026_CMD_W_HSYNC      =5;
	public static final int CH7026_CMD_CONFIRM      =6;
	public static final int CH7026_CMD_OUTPUTON     =7;
	public static final int CH7026_CMD_OUTPUTOFF    =8;
	public static final int CH7026_CMD_RESET_DEFAULT =9;
	public static final int CH7026_CMD_ROTATE   		=10;
	
	/*	 * 
	251、   设置GPIO
		常量值：private static final int JNI_EXE_CMD_251_SET_GPIOS	= 251;
		参数：en=inparam.putInt("param0");outparam=null;
		返回参数说明：
		返回值：操作结果-1失败，>=0成功
	
	 输入参数 ：int格式
	char gpio_output = ((int)(*buf) >> 16) & 0xff;// 第16-24位代表GPIO的输入输出功能  1为输出  0为输入
	char gpio_app_num = ((int)(*buf) >> 8) & 0xff;// 第8-15位 代表GPIO的号，看宏定义的值
	char gpio_value = (int)(*buf) & 0xff;// 低8位代表需要设置的GPIO输出的值 1或者0
	 */
	//public native int jni_exe_cmd_set_gpios(int type, Object inparam, Object outparam);
	
	/*
	252、   获取GPIO的值
		常量值：private static final int JNI_EXE_CMD_252_GET_GPIOS	= 252;
		参数：en=inparam.putInt("param0");outparam=null;
		返回参数说明：
		返回值：操作结果-1失败，>=0成功
	
	输入参数 ：int格式
		char gpio_app_num = ((int)(*buf) >> 8) & 0xff;// 第8-15位 代表需要查看的GPIO号，看宏定义的值
	输出参数 char格式  值为 0 或者 1
	*/
	//public native int jni_exe_cmd_get_gpios(int type, Object inparam, Object outparam);
	
	/*
	 *gpio功能以及宏定义值 
	 *通用型IO控制功能编号
	 *范围:0-255
	 */
	public static final int GPIO_INDEX_SOUND_EFFECT_IC_RESET 				=0;
	public static final int GPIO_INDEX_HUB_ENABLE 							=1;
	public static final int GPIO_INDEX_AUDIO_MUTE_ENABLE 					=2;
	public static final int GPIO_INDEX_BT_POWER 								=3;
	public static final int GPIO_INDEX_ZLINK_POWER							= 4;
	public static final int GPIO_TURN_L_DET 									= 5;//817转向检测
	public static final int GPIO_TURN_R_DET 									= 6;//817转向检测
	public static final int GPIO_HIS_POWER 									= 7;//817 360盒子复位脚
	public static final int GPIO_HANDBRAKE_DET								= 8;
	public static final int GPIO_AMP_SW_CTRL									= 9;
	public static final int GPIO_AVDD_CTRL1									= 10;
	public static final int GPIO_AVDD_CTRL2									= 11;
	
	//817 
	public static final int GPIO_SPECIAL_6911C								= 64;
}
