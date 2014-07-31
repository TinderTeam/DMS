package cn.fuego.dms.service;

import cn.fuego.dms.service.collector.DataCollectorImpl;
import cn.fuego.dms.service.impl.SystemBasicServiceImpl;

public class ServiceContext
{
	private static ServiceContext instance;
	private DataCollectorService colletor;
	private SystemBasicService systemBasicService;

	private ServiceContext()
	{

	}

	public static synchronized ServiceContext getInstance()
	{
		if (null == instance)
		{
			instance = new ServiceContext();
		}
		return instance;
	}

	public synchronized DataCollectorService getCollectorService()
	{
		if (null == colletor)
		{
			colletor = new DataCollectorImpl();
		}
		return colletor;
	}

	public synchronized SystemBasicService getSystemBasicService()
	{
		if (null == systemBasicService)
		{
			systemBasicService = new SystemBasicServiceImpl();
		}
		return systemBasicService;
	}
}
