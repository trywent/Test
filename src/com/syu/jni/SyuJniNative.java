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
	
	// ��������
	public static final int JNI_EXE_CMD_0_TEST 	 			= 0;	// ���Խӿ�
	public static final int JNI_EXE_CMD_1_BACKCAR_MIRROR 	= 1;	// ��������
	public static final int JNI_EXE_CMD_2_SOUND_MIX		 	= 2;	// GPS������8288�õ�
	public static final int JNI_EXE_CMD_3_ENCARPTION_RESULT	= 3;	// �������IC��֤���
	public static final int JNI_EXE_CMD_4_AUDIO_STATE		= 4;	// ����ARM�Ƿ����������
	public static final int JNI_EXE_CMD_5_TURNOFF_LCDC		= 5;	// �ر���CLOCK
	public static final int JNI_EXE_CMD_6_MUTE_AMP			= 6;	// ��������
	public static final int JNI_EXE_CMD_7_AMP_MUTE_STATE	= 7;	// ��ȡ���ž���״̬
	public static final int JNI_EXE_CMD_8_RESET_GPS			= 8;	// ��λGPS
	public static final int JNI_EXE_CMD_9_POWERON_SCREEN	= 9;	// ������Ļ����
	public static final int JNI_EXE_CMD_10_LITTLE_HOM		= 10;	// ����С����
	public static final int JNI_EXE_CMD_11_VIDEO_NTSC_PAL	= 11;	// NP��֪ͨ�ײ� 0��N�� 1��P��
	public static final int JNI_EXE_CMD_14_SET_BACKCAR_TPYE	= 14;	// ���õ������� 0��cvbs 1��usb �����Ӿ��õģ�2��360 ȫ�������ڼӵģ�
	public static final int JNI_EXE_CMD_15_GET_BACKCAR_TPYE	= 15;	// ���õ�������
	public static final int JNI_EXE_CMD_16_SET_LED_COLORS 	= 16;	//������ɫ�ƽӿڣ�������ɫ�Ƶ���ɫ
	public static final int JNI_EXE_CMD_17_GET_LED_COLORS	= 17;	//��ȡ��ɫ����ɫ�ӿڣ���ȡ��ɫ�Ƶ���ɫ
	public static final int JNI_EXE_CMD_19_SET_AIRPLANE_MODE = 19;	// ���÷���ģʽ�ӿ�
	public static final int JNI_EXE_CMD_22_SET_VIDEO_SYS_MODE= 22;	// ����1�����У�0�Ǹ���
	public static final int JNI_EXE_CMD_23_GET_VIDEO_SYS_MODE= 23;	// 1�����У�0�Ǹ���
	public static final int JNI_EXE_CMD_24_RESET_8288A		= 24;	// ��λ8288A
	public static final int JNI_EXE_CMD_25_GET_VIDEO_MODE 	= 25;	// ��ȡNP��ʽ 0��N�� 1��P��
	public static final int JNI_EXE_CMD_26_GET_VIDEO_SIGNAL_ON=26;  // ��ȡ�ź�״̬
	public static final int JNI_EXE_CMD_29_ACC_STATE_TO_BSP	= 29;	// ACC״̬֪ͨBSP
	public static final int JNI_EXE_CMD_31_FAN_EN  			= 31;	// ���Ʒ��ȿ���
	public static final int JNI_EXE_CMD_32_GET_BOOT_REVERSE_STATUS= 32;	// BSP����״̬
	public static final int JNI_EXE_CMD_33_RESET_VIDEOIC	= 33;	// ��λ��ƵIC
	public static final int JNI_EXE_CMD_35_SET_AUDIO_SAMPLE_RATE= 35;// 48KHZ����ARMͨ���� 44.1KHZ��ARMͨ����
	public static final int JNI_EXE_CMD_50_FM_ANT_EN   		= 50;	// ����FM���ߵ�Դ����
	
	public static final int JNI_EXE_CMD_101_PARAMA_READ		= 101;
	public static final int JNI_EXE_CMD_104_WRITE_GAMMA 	= 104;	// ������ĻУ׼����
	
	public static final int JNI_EXE_CMD_147_CH7026_CONTROL = 147;
	
	public static final int JNI_EXE_CMD_148_READ_APPDATE 	= 148;
	public static final int JNI_EXE_CMD_149_WRITE_APPDATE 	= 149;
	public static final int JNI_EXE_CMD_150_GET_WRITE_FLASH_PROCESS_PID = 150;
	public static final int JNI_EXE_CMD_151_SET_WRITE_FLASH_PROCESS_PID_FLAG = 151;
	public static final int JNI_EXE_CMD_153_GSENSOR_POWER_ONOFF = 153;
	public static final int JNI_EXE_CMD_251_Normal_IO_Set = 251; // ͨ��IO�������������͵�ƽ��������ʹ��˵����2018.07.21 Add by XiaoHuiZhan
	public static final int JNI_EXE_CMD_252_Normal_IO_Get = 252; // ͨ��IO��ƽ���ƣ�������ʹ��˵����2018.07.21 Add by XiaoHuiZhan
	//public static final int JNI_EXE_CMD_253_USB_Set = 253; //7731ƽ̨����ʹ��USB�˿�
	public static final int JNI_EXE_CMD_254_USB_Get = 254; //7731ƽ̨��ȡʹ��USB�˿�
	public static final int JNI_EXE_CMD_255_USB_Set = 255; //7731ƽ̨����ʹ��USB�˿�
	
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
	251��   ����GPIO
		����ֵ��private static final int JNI_EXE_CMD_251_SET_GPIOS	= 251;
		������en=inparam.putInt("param0");outparam=null;
		���ز���˵����
		����ֵ���������-1ʧ�ܣ�>=0�ɹ�
	
	 ������� ��int��ʽ
	char gpio_output = ((int)(*buf) >> 16) & 0xff;// ��16-24λ����GPIO�������������  1Ϊ���  0Ϊ����
	char gpio_app_num = ((int)(*buf) >> 8) & 0xff;// ��8-15λ ����GPIO�ĺţ����궨���ֵ
	char gpio_value = (int)(*buf) & 0xff;// ��8λ������Ҫ���õ�GPIO�����ֵ 1����0
	 */
	//public native int jni_exe_cmd_set_gpios(int type, Object inparam, Object outparam);
	
	/*
	252��   ��ȡGPIO��ֵ
		����ֵ��private static final int JNI_EXE_CMD_252_GET_GPIOS	= 252;
		������en=inparam.putInt("param0");outparam=null;
		���ز���˵����
		����ֵ���������-1ʧ�ܣ�>=0�ɹ�
	
	������� ��int��ʽ
		char gpio_app_num = ((int)(*buf) >> 8) & 0xff;// ��8-15λ ������Ҫ�鿴��GPIO�ţ����궨���ֵ
	������� char��ʽ  ֵΪ 0 ���� 1
	*/
	//public native int jni_exe_cmd_get_gpios(int type, Object inparam, Object outparam);
	
	/*
	 *gpio�����Լ��궨��ֵ 
	 *ͨ����IO���ƹ��ܱ��
	 *��Χ:0-255
	 */
	public static final int GPIO_INDEX_SOUND_EFFECT_IC_RESET 				=0;
	public static final int GPIO_INDEX_HUB_ENABLE 							=1;
	public static final int GPIO_INDEX_AUDIO_MUTE_ENABLE 					=2;
	public static final int GPIO_INDEX_BT_POWER 								=3;
	public static final int GPIO_INDEX_ZLINK_POWER							= 4;
	public static final int GPIO_TURN_L_DET 									= 5;//817ת����
	public static final int GPIO_TURN_R_DET 									= 6;//817ת����
	public static final int GPIO_HIS_POWER 									= 7;//817 360���Ӹ�λ��
	public static final int GPIO_HANDBRAKE_DET								= 8;
	public static final int GPIO_AMP_SW_CTRL									= 9;
	public static final int GPIO_AVDD_CTRL1									= 10;
	public static final int GPIO_AVDD_CTRL2									= 11;
	
	//817 
	public static final int GPIO_SPECIAL_6911C								= 64;
}
