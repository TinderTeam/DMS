/**   
* @Title: CommunicatorFactory.java 
* @Package cn.fuego.dms.communicate.physical 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-26 上午10:52:00 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate;

import cn.fuego.dms.communicate.physical.impl.UartCommunicatorImpl;

/** 
 * @ClassName: CommunicatorFactory 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 上午10:52:00 
 *  
 */

public class CommunicatorFactory
{
	private static CommunicatorFactory instance;
	private Communicator communicator; 
	private CommunicatorFactory()
	{
		
	}
	
	public static synchronized CommunicatorFactory getInstance()
	{
		if (null == instance)
		{
			instance = new CommunicatorFactory();
		}
		return instance;
	}
	
	public synchronized Communicator getCommunicator()
	{
		if(null == communicator)
		{
			communicator = new GPRSCommunicatorimpl();
		}
		
		return communicator;
	}
}
