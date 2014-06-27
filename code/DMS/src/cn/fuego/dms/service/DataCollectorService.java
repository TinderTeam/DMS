package cn.fuego.dms.service;

import java.util.List;

import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.ui.model.MonitorValueGroup;

public interface DataCollectorService
{
	public void modifyPeriod(int period);
	public void start();
	public Collection getCurCollection();
	
	public void stop();

	public List<MonitorValueGroup> getDataByBaseSiteName(String baseSiteName);
}	
