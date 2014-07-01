package cn.fuego.dms.dao;

import cn.fuego.dms.dao.impl.BaseSiteDaoImpl;
import cn.fuego.dms.dao.impl.DataFormatDaoImpl;
import cn.fuego.dms.dao.impl.IndicatorGroupDaoImpl;
import cn.fuego.dms.dao.impl.IndicatorInfoDaoImpl;


/**
 * 
* @ClassName: DaoContext 
* @Description: TODO
* @author Nan Bowen
* @date 2014-3-23 下午11:27:41 
* 
 */
public class DaoContext
{
	private static DaoContext instance;
	private BaseSiteDao bseSiteDao=null;
	private DataFormatDao dataFormatDao=null;
	private IndicatorGroupDao indicatorGroupDao=null;
	private IndicatorInfoDao indicatorInfoDao=null;


	

	private DaoContext()
	{

	}

	public static synchronized DaoContext getInstance()
	{
		if (null == instance)
		{
			instance = new DaoContext();
		}
		return instance;
	}

	public synchronized BaseSiteDao getBaseSiteDao() 
	{
		if (null == bseSiteDao)
		{
			bseSiteDao = new BaseSiteDaoImpl();
		}
		return bseSiteDao;
	}
	public synchronized DataFormatDao getDataFormatDao() 
	{
		if (null == dataFormatDao)
		{
			dataFormatDao = new DataFormatDaoImpl();
		}
		return dataFormatDao;
	}
	public synchronized IndicatorGroupDao getIndicatorGroupDao() 
	{
		if (null == indicatorGroupDao)
		{
			indicatorGroupDao = new IndicatorGroupDaoImpl();
		}
		return indicatorGroupDao;
	}
	
	public synchronized IndicatorInfoDao getIndicatorInfoDao() 
	{
		if (null == indicatorInfoDao)
		{
			indicatorInfoDao = new IndicatorInfoDaoImpl();
		}
		return indicatorInfoDao;
	}
	
}
