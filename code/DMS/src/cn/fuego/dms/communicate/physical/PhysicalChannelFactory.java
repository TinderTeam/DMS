/**   
* @Title: CommunicatorFactory.java 
* @Package cn.fuego.dms.communicate.physical 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-26 上午10:52:00 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.physical;

import cn.fuego.dms.communicate.physical.impl.UartCommunicatorImpl;

/** 
 * @ClassName: CommunicatorFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 上午10:52:00 
 *  
 */

public class PhysicalChannelFactory
{
	private static PhysicalChannelFactory instance;
	private PhysicalChannel communicator; 
	private PhysicalChannelFactory()
	{
		
	}
	
	public static synchronized PhysicalChannelFactory getInstance()
	{
		if (null == instance)
		{
			instance = new PhysicalChannelFactory();
		}
		return instance;
	}
	
	public synchronized PhysicalChannel getCommunicator()
	{
		if(null == communicator)
		{
			communicator = new UartCommunicatorImpl();
		}
		
		return communicator;
	}
}
