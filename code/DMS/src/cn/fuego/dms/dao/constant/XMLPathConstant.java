package cn.fuego.dms.dao.constant;

import java.io.File;

import cn.fuego.dms.util.SystemConfigInfo;


public class XMLPathConstant
{

	public static String configPath=SystemConfigInfo.getSystemRootPath()+ "resource"+File.separator + "config" + File.separator;
	public static final String BASESIT_CONFIG_PATH =configPath+"baseSite.xml";
	public static final String INDICATE_CONFIG_PATH =configPath+"indicate.xml";
	public static final String INDICATE_GROUP_CONFIG_PATH =configPath+"indicateGroup.xml";
	
}


