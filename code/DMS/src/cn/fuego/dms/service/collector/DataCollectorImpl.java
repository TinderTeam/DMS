/**   
* @Title: DataCollector.java 
* @Package cn.fuego.bse.communicate 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-24 下午05:20:29 
* @version V1.0   
*/ 
package cn.fuego.dms.service.collector;

import java.util.Calendar;
import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.communicate.Communicator;
import cn.fuego.dms.communicate.CommunicatorFactory;
import cn.fuego.dms.communicate.exception.CommunicateException;
import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.util.SystemConfigInfo;

/** 
 * @ClassName: DataCollector 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-24 下午05:20:29 
 *  
 */

public class DataCollectorImpl implements DataCollectorService
{
	private Log log = LogFactory.getLog(DataCollectorImpl.class);

	/* data collector period*/
	private static final int DEFUALT_PERIOD = 15;
	private int period = DEFUALT_PERIOD; //unit is second
	
	private Timer timer;
	
	private static final int MILLIS_NUM_OF_SEC = 1000;
	
	private CollectTask collectorTask = new CollectTask();
	private Communicator comunicator = CommunicatorFactory.getInstance().getCommunicator();
 
	public  DataCollectorImpl()
	{
		
		try
		{
			String strValue = SystemConfigInfo.getCollPeriod();
			this.period = Integer.valueOf(strValue);
		}
		catch(Exception e)
		{
			log.error("the config collect period is wrong.");
		}
		if(this.period < DEFUALT_PERIOD)
		{
			this.period = DEFUALT_PERIOD;
		}
		log.info("the collect period is :" + this.period); 
	}
	public void modifyPeriod(int period)
	{
		this.period = period;
		initTimer();
		
	}
	
	public void start()
	{   

		try
		{
			comunicator.open(SystemConfigInfo.getServerIP(), SystemConfigInfo.getServerPort(), SystemConfigInfo.getCommPort());
			
			initTimer();
			
		}
		catch(Exception e)
		{
			log.error("start collect failed",e);
			try
			{
				stop();
			}
			catch(Exception e1)
			{
				log.error("stop failed",e1);
			}
			throw e;
		}
 
	}
	
	
	private void initTimer()
	{
		if(null == timer)
		{
			timer = new Timer();
	        Calendar date = Calendar.getInstance();
	 
			long delayTime = this.period - date.get(Calendar.SECOND)%this.period;
			timer.schedule(collectorTask,delayTime*MILLIS_NUM_OF_SEC,this.period*MILLIS_NUM_OF_SEC);
		}
	}
	
	public Collection getCurCollection()
	{
		Collection collection = collectorTask.getCurCollection();
		log.info(collection);
		return collection;
	}
	public void stop()
	{
		if(null != timer)
		{
			timer.cancel();
			timer = null;
		}
		this.comunicator.close();
	}

	@Override
	public int getSignalInfo()
	{
		return this.comunicator.getSignalInfo();
	}

	@Override
	public String getServerName()
	{
		// TODO Auto-generated method stub
		return this.comunicator.getServerName();
	}
 
}
