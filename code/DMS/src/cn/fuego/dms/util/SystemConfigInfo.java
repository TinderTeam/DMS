package cn.fuego.dms.util;

import cn.fuego.dms.util.file.property.PropertyItemNameConst;
import cn.fuego.dms.util.file.property.PropertyReader;

public class SystemConfigInfo
{
	public static String getProductName()
	{
		return PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.PRODUCT_NAME);
	} 
	
	public static String getServerIP()
	{
		return PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.SERVER_IP);
	}
	
	public static String getServerPort()
	{
		return PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.SERVER_PORT);
	}
	
	public static String getCommPort()
	{
		return PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.CONMMUNICATOR_PORT);
	}
	 
}
