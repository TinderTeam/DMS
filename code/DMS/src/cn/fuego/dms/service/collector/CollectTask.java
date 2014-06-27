/**   
* @Title: CollectTask.java 
* @Package cn.fuego.bse.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午10:13:44 
* @version V1.0   
*/ 
package cn.fuego.dms.service.collector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.communicate.physical.impl.UartCommunicatorImpl;
import cn.fuego.dms.service.model.Collection;

/** 
 * @ClassName: CollectTask 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午10:13:44 
 *  
 */

public class CollectTask extends TimerTask 
{	
	private Log log = LogFactory.getLog(CollectTask.class);
	private int cacheLenth = 60;
	private List<Collection> dataCache = new ArrayList<Collection>();

	
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run()
	{
		
		log.info("now collector time is " + Calendar.getInstance().getTime().toLocaleString());
		Collection nextCollection = this.getNextCollection();
		if(cacheLenth == dataCache.size())
		{
			this.dataCache.remove(0);
			this.dataCache.add(nextCollection);
		}
		else
		{
			this.dataCache.add(nextCollection);
		}
	}
	private Collection getNextCollection()
	{
		
		return null;
	}
	
	public Collection getCurCollection()
	{
		return this.dataCache.get(this.dataCache.size());
	}

}
