package cn.fuego.dms.service;

import java.util.List;

import cn.fuego.dms.ui.model.MonitorValueGroup;

public interface DataCollectionService
{
	public List<MonitorValueGroup> getDataByBaseSiteName(String baseSiteName);
}	
