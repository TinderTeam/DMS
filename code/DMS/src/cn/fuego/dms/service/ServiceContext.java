package cn.fuego.dms.service;

import cn.fuego.dms.service.collector.DataCollectorImpl;

public class ServiceContext
{
	private static ServiceContext instance;
    private DataCollectorService colletor;
	  
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
	public synchronized  DataCollectorService getCollectorService() {
		if (null == colletor)
		{
			colletor = new DataCollectorImpl();
		}
		return colletor;
	}

 
}
