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

import cn.fuego.dms.communicate.protocol.gprs.GPRSFactory;
import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.service.model.Indicator;
import cn.fuego.dms.service.model.Resource;

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
		
		Collection nextCollection = null;
		try
		{
		   nextCollection = this.getNextCollection();
		}
		catch(Exception e)
		{
			log.error("collect data error ",e);
		}
		if(null != nextCollection)
		{
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
	

	}
	private Collection getNextCollection()
	{
		String  sendMessage = ApplicationProtocol.CMD_READ_DATA;
		
		sendMessage = ApplicationProtocol.encode(sendMessage) + ApplicationProtocol.DATA_END_FLAG;
		
		GPRSFactory.getInstance().getGPRSOperator().sendData(sendMessage);

		String readMessage = GPRSFactory.getInstance().getGPRSOperator().readData(ApplicationProtocol.CMD_LENGTH+ApplicationProtocol.DATA_LENGTH);
		
		Collection collection = null;
		if(ApplicationProtocol.isValid(readMessage))
		{
			String decodeMessage = ApplicationProtocol.decode(readMessage);
			collection = parseData(ApplicationProtocol.getData(decodeMessage));
		}
		else
		{	
			log.error("the read message is invalid. read message is :"+readMessage);
		}
		log.info("now collection is :" + collection);
		
		return collection;
	}
	
	public Collection getCurCollection()
	{
		return this.dataCache.get(this.dataCache.size());
	}
	
	private Collection parseData(String readMessage)
	{
		log.info(readMessage);
		Collection collection = new Collection();
		
		//first two byte is resource id (base station id)
		String resID = ApplicationProtocol.getResID(readMessage);
		Resource resource = new Resource();
		resource.setResID(resID);
		
		//convert indicator value;
		byte[] dataBytes = readMessage.getBytes();
		if(dataBytes.length>1)
		{
			for(int i=2;i<dataBytes.length;i = i +2)
			{
				Indicator indicator = new Indicator();
				indicator.setIndicatorID(dataBytes[i]);
				indicator.setValue(String.valueOf(dataBytes[i+1]));
				resource.getIndicatorList().add(indicator);
			}
		}
		collection.setCollectTime(Calendar.getInstance());
		collection.getResourceList().add(resource);
		return collection;
	}

}
