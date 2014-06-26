package cn.fuego.dms.service.impt;

import java.util.List;

import cn.fuego.dms.service.DataCollectionService;
import cn.fuego.dms.test.Stub;
import cn.fuego.dms.ui.model.MonitorValueGroup;

public class DataCollectionServiceImpl implements DataCollectionService
{
	@Override
	public List<MonitorValueGroup> getDataByBaseSiteName(String baseSiteName)
	{
		
		return Stub.getRadomData(baseSiteName);
	}

}
