/**   
* @Title: GPRSCommunicator.java 
* @Package cn.fuego.dms.communicate 
* @Description: TODO
* @author Tang Jun   
* @date 2014-7-7 上午11:07:57 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate;

import cn.fuego.dms.communicate.protocol.gprs.GPRSFactory;
import cn.fuego.dms.communicate.protocol.gprs.GPRSOperator;

/** 
 * @ClassName: GPRSCommunicator 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-7-7 上午11:07:57 
 *  
 */

public class GPRSCommunicatorimpl implements Communicator
{
	private boolean isOpen = true;
	GPRSOperator gprs = GPRSFactory.getInstance().getGPRSOperator();

 

	/* (non-Javadoc)
	 * @see cn.fuego.dms.communicate.Communicator#open(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void open(String serverIP, String serverPort, String commPort)
	{
		if(!isOpen)
		{
			gprs.initGPRS(serverIP, serverPort, commPort);
			this.isOpen = true;
		}
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.communicate.Communicator#sendData(java.lang.String)
	 */
	@Override
	public void sendData(String data)
	{
		gprs.sendData(data);
		
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.communicate.Communicator#readData(int)
	 */
	@Override
	public String readData(int length)
	{
		// TODO Auto-generated method stub
		return gprs.readData(length);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.communicate.Communicator#readData(java.lang.String)
	 */
	@Override
	public String readData(String end)
	{
		// TODO Auto-generated method stub
		return gprs.readData(end);
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.communicate.Communicator#close()
	 */
	@Override
	public void close()
	{
		this.isOpen = false;
		gprs.closeGPRS();
		
	}

}
