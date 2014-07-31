package cn.fuego.dms.ui.constant;

import java.awt.Toolkit;
import java.io.File;

import cn.fuego.dms.util.SystemConfigInfo;

public class UIConstant
{

	public static final int BASIC_INFO_ID = 1;
	public static final int CITY_ELECTRONIC_ID = 2;
	public static final int ENTRANCE_ID = 3;
	public static final int BATTERY_ID = 4;
	public static final int SENSER_ID = 5;
	public static final double SCR_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final double SCR_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final String BASESIT_INFO = "基站信息";
	public static final String MONITOR_VALUE = "监控指标";
	public static final String NO_SIGNAL = "无信号";
	public static final String NO_SERVER = "无服务商";

	public static final String LOW_SIGNAL = "弱";
	public static final String MID_SIGNAL = "中";
	public static final String HIGH_SIGNAL = "强";

	public static final String PICTURE_PATH = SystemConfigInfo.getSystemRootPath() + "resource" + File.separator + "ui" + File.separator + "picture" + File.separator;

	public static final String ABOUTUS_PATH = PICTURE_PATH + "aboutus.png";
	public static final String EXIT_PATH = PICTURE_PATH + "exit.png";
	public static final String ICON_PATH = PICTURE_PATH + "icon.png";
	public static final String REFRESH_PATH = PICTURE_PATH + "refresh.png";
	public static final String INIT_PATH = PICTURE_PATH + "init.png";

}
