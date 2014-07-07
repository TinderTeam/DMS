package cn.fuego.dms.util;

import cn.fuego.dms.util.file.property.PropertyItemNameConst;
import cn.fuego.dms.util.file.property.PropertyReader;

public class SystemConfigInfo
{
	public static String getProductName()
	{
		return PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.PRODUCT_NAME);
	} 
}
