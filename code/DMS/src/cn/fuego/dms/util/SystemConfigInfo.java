package cn.fuego.dms.util;

import cn.fuego.dms.util.file.property.PropertyReader;

public class SystemConfigInfo
{

	public static String getSystemRootPath()
	{
		return SystemConfigInfo.class.getResource("/").getPath();
	}

	public static String getProductName()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.PRODUCT_NAME);
	}

	public static String getServerIP()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.SERVER_IP);
	}

	public static String getServerPort()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.SERVER_PORT);
	}

	public static String getCommPort()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.CONMMUNICATOR_PORT);
	}

	public static String getCollPeriod()
	{
		return PropertyReader.getInstance().getPropertyByName(SystemConfigNameConst.COLLECT_PERIOD);

	}
}
