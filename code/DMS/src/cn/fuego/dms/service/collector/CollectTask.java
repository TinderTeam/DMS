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
import cn.fuego.dms.domain.po.DataFormat;
import cn.fuego.dms.service.cache.DataFormatCache;
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
		
		sendMessage = ApplicationProtocol.encode(sendMessage);
		
		GPRSFactory.getInstance().getGPRSOperator().sendData(sendMessage);

		String readMessage = GPRSFactory.getInstance().getGPRSOperator().readData(ApplicationProtocol.PACKET_END);
		
		Collection collection = new Collection();
		if(ApplicationProtocol.isValid(readMessage))
		{
			String decodeMessage = ApplicationProtocol.decode(readMessage);
			
			List<String> dataMessageList = ApplicationProtocol.getDataMessageList(decodeMessage);
			Resource resource;
			for(String dataMessage : dataMessageList)
			{
				resource = parseData(dataMessage);
				collection.getResourceList().add(resource);
			}
			collection.setCollectTime(Calendar.getInstance());
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
		if(!this.dataCache.isEmpty())
		{
			return this.dataCache.get(this.dataCache.size()-1);
		}
		log.warn("the data collection cache is empty.");
		return null; 
	}
	
	private Resource parseData(String dataMessage)
	{
		log.info(dataMessage);
 
		//first two byte is resource id (base station id)
		String resID = ApplicationProtocol.getResID(dataMessage);
		Resource resource = new Resource();
		resource.setResID(resID);
		
		//convert indicator value;
		byte[] dataBytes = dataMessage.getBytes();
		if(dataBytes.length == (DataFormatCache.getInstance().getAllDataLength()+ApplicationProtocol.RES_ID_LENGTH))
		{
		
			List<DataFormat> forMatList = DataFormatCache.getInstance().getAll();
			
			int dataIndex = ApplicationProtocol.RES_ID_LENGTH;
			for(DataFormat format : forMatList)
			{	
				Indicator indicator = new Indicator();
				indicator.setIndicatorID(format.getIndicatorID());
				
				int value =0;

				int byteValue = 1;
				
				for(int i=format.getDataLength();i>0;i--)
				{
					value += dataBytes[dataIndex+i-1]*byteValue;
					byteValue *= 256; 
				}
				dataIndex += format.getDataLength();
				indicator.setValue(String.valueOf(value));
				resource.getIndicatorList().add(indicator);
			}
		}
		else
		{
			log.error("the data length is wrong. data is :"+dataMessage);
		}
 
		return resource;
	}

}
