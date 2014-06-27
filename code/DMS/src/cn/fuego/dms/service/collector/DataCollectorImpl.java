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
import java.util.List;
import java.util.Timer;

import cn.fuego.dms.service.model.Collection;

/** 
 * @ClassName: DataCollector 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-24 下午05:20:29 
 *  
 */

public class DataCollectorImpl
{
	/* data collector period*/
	private int period = 10; //unit is second
	
	private Timer timer; 
	
	private static final int MILLIS_NUM_OF_SEC = 1000;
	
	private CollectTask collectorTask = new CollectTask();
	
	public void modifyPeriod(int period)
	{
		
	}
	
	public void start()
	{   
		timer = new Timer();
 		Calendar date = Calendar.getInstance();
		
		long delayTime = this.period - date.get(Calendar.SECOND)%this.period;
		timer.schedule(collectorTask,delayTime*MILLIS_NUM_OF_SEC,this.period*MILLIS_NUM_OF_SEC);
		
	}
	
	public Collection getCurCollection()
	{
		return collectorTask.getCurCollection();
	}
	public void stop()
	{
		timer.cancel();
	}

}
