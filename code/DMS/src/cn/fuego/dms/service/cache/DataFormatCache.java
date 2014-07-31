/**   
* @Title: DataFormatCache.java 
* @Package cn.fuego.dms.service.cache 
* @Description: TODO
* @author Tang Jun   
* @date 2014-7-1 上午10:03:54 
* @version V1.0   
*/ 
package cn.fuego.dms.service.cache;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.dao.DaoContext;
import cn.fuego.dms.domain.po.DataFormat;

/** 
 * @ClassName: DataFormatCache 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-7-1 上午10:03:54 
 *  
 */

public class DataFormatCache
{
	
	private Log log = LogFactory.getLog(DataFormatCache.class);

	private static DataFormatCache instance;
	private List<DataFormat> cache;
 
	private DataFormatCache()
	{
		this.reload();

	}
	public List<DataFormat> getAll()
	{
		return this.cache;
	}
 
	
	public int getAllDataLength()
	{
		int sumLength = 0;
		for(DataFormat format : cache)
		{
			sumLength += format.getDataLength();
		}
		return sumLength;
	}
	public static synchronized DataFormatCache getInstance()
	{
		if (null == instance)
		{
			instance = new DataFormatCache();
		}
		return instance;
	}
	public void reload()
	{
		// there is no parent menu when parent id is 0
		cache = DaoContext.getInstance().getDataFormatDao().getAll();
		log.info("loaded all menu list" + cache);

	}
}
