package cn.fuego.dms.service;

import cn.fuego.dms.service.model.Collection;

public interface DataCollectorService
{
	public void modifyPeriod(int period);

	public void start();

	public Collection getCurCollection();

	public void stop();

	public int getSignalInfo();

	public String getServerName();

}
