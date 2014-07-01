/**   
* @Title: Test.java 
* @Package cn.fuego.dms.main 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-28 上午01:08:02 
* @version V1.0   
*/ 
package cn.fuego.dms.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.communicate.protocol.gprs.GPRSFactory;
import cn.fuego.dms.domain.po.DataFormat;
import cn.fuego.dms.service.ServiceContext;
import cn.fuego.dms.service.cache.DataFormatCache;

/** 
 * @ClassName: Test 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-28 上午01:08:02 
 *  
 */

public class Test
{
	private static Log log = LogFactory.getLog(Test.class);	

	public static void main(String[] args)
	{
		try
		{
			DataFormat data = DataFormatCache.getInstance().getDataFormatBySeq(1);
			log.info(data);
			String result = "";
			while(true)
			{
				if(null == result)
				{
					break;
				}
			}
				
			GPRSFactory.getInstance().getGPRSOperator().initGPRS("59.36.72.2", "8600", "COM7");
			//result = GPRSFactory.getInstance().getGPRSOperator().getConnNetName();
			log.info("the net name "+result);

			GPRSFactory.getInstance().getGPRSOperator().sendData("whutw111335567491fuego");
			GPRSFactory.getInstance().getGPRSOperator().sendData("whutrfuego");
  

			//Thread.sleep(20000);
			log.info("stat reading");
		    result = GPRSFactory.getInstance().getGPRSOperator().readData("fuego");
			log.info("the result is  "+result);

	 

			//GPRSFactory.getInstance().getGPRSOperator().sendData("whutrfuego\r\n");
			// result = GPRSFactory.getInstance().getGPRSOperator().readData(22);
			log.info("the net name "+result);
	 		ServiceContext.getInstance().getCollectorService().start();
			
//			String  sendMessage = ApplicationProtocol.CMD_READ_DATA;
//			
//			sendMessage = ApplicationProtocol.encode(sendMessage) + ApplicationProtocol.DATA_END_FLAG;
//			
//			GPRSFactory.getInstance().getGPRSOperator().sendData(sendMessage);
//
//			String readMessage = GPRSFactory.getInstance().getGPRSOperator().readData(ApplicationProtocol.CMD_LENGTH+ApplicationProtocol.DATA_LENGTH);
//			
//			log.info("read message is :"  + readMessage);
//			
//	        sendMessage = ApplicationProtocol.encode(ApplicationProtocol.CMD_READ_DATA) + ApplicationProtocol.DATA_END_FLAG;
//			
//			GPRSFactory.getInstance().getGPRSOperator().sendData(sendMessage);
//			readMessage = GPRSFactory.getInstance().getGPRSOperator().readData(ApplicationProtocol.CMD_LENGTH+ApplicationProtocol.DATA_LENGTH);
//			log.info("read message is :"  + readMessage);

			while(true)
			{
				Thread.sleep(1000);
			}
	
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		    GPRSFactory.getInstance().getGPRSOperator().closeGPRS();
			
			GPRSFactory.getInstance().getGPRSOperator().closePhysicalPort();
		}
		
		log.info("system exist");

		System.exit(0);
	}
}
