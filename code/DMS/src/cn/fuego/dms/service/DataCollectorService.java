package cn.fuego.dms.service;

import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.service.model.Resource;

public interface DataCollectorService
{
	public void modifyPeriod(int period);
	public void start();
	public Collection getCurCollection();
	public Resource getRefreshResource(String baseSiteID);
	public void stop();


}	
