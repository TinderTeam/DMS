package cn.fuego.dms.service.impl;

import java.util.List;

import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.test.Stub;
import cn.fuego.dms.ui.model.MonitorValueGroup;

public class DataCollectionServiceImpl implements DataCollectorService
{
	@Override
	public List<MonitorValueGroup> getDataByBaseSiteName(String baseSiteName)
	{
		
		return Stub.getRadomData(baseSiteName);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.DataCollectorService#modifyPeriod(int)
	 */
	@Override
	public void modifyPeriod(int period)
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.DataCollectorService#start()
	 */
	@Override
	public void start()
	{
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.DataCollectorService#getCurCollection()
	 */
	@Override
	public Collection getCurCollection()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.DataCollectorService#stop()
	 */
	@Override
	public void stop()
	{
		// TODO Auto-generated method stub
		
	}

}
