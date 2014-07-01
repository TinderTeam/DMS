/**   
* @Title: CollectProtocol.java 
* @Package cn.fuego.bse.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:17:27 
* @version V1.0   
*/ 
package cn.fuego.dms.service.collector;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.service.cache.DataFormatCache;

/** 
 * @ClassName: CollectProtocol 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:17:27 
 *  
 */

public class ApplicationProtocol
{
	private static Log log = LogFactory.getLog(ApplicationProtocol.class);

	public static final String PACKET_HEAD = "whut";
	public static final String PACKET_END = "fuego";
	
	public static final String CMD_QUIT = "q";
	public static final String CMD_WRITE_DATA = "w";
	public static final String CMD_READ_DATA = "r";
 
	
	public static final int CMD_LENGTH = 1;

	public static final int RES_ID_LENGTH = 2;

	public static boolean isValid(String data)
	{
		if(!data.startsWith(PACKET_HEAD))
		{
			return false;
		}
		if(!data.endsWith(PACKET_END))
		{
			return false;
		}
		return true;
	}
	
	public static String decode(String data)
	{
		String message = null;
		if(!isValid(data))
		{
			return message;
		}
		message = data.substring(PACKET_HEAD.length(),data.length()-PACKET_END.length());
		return message;
	}
	
	public static String getCommand(String message)
	{
		return message.substring(0,CMD_LENGTH);
	}
	public static String getDataMessage(String message)
	{
		return message.substring(CMD_LENGTH,message.length());
	}
	
	public static List<String> getDataMessageList(String message)
	{
		String dataMessage = getDataMessage(message);
		int dataLength = DataFormatCache.getInstance().getAllDataLength() + ApplicationProtocol.RES_ID_LENGTH;
		List<String> messageList = new ArrayList<String>();
		for(int i=dataLength;i<=dataMessage.length();i=i+dataLength)
		{
			String data = dataMessage.substring(i-dataLength,i);
			messageList.add(data);
		}
		log.info("the data message list size is " + messageList.size());
		return messageList;
	}
	
	public static String getResID(String data)
	{
		String resID = "";
		int resNum = 0;
		byte[] dataBytes = data.getBytes();
		
		if(dataBytes.length >= RES_ID_LENGTH)
		{
			int byteValue = 1;
			for(int i=RES_ID_LENGTH;i>0;i--)
			{
				resNum += dataBytes[i-1]*byteValue;
				byteValue *= 256; 
			}
		}
		else
		{	
			log.warn("the data length is not right.data is " + data);
		}

		

		resID = String.valueOf(resNum);
		return resID;
	}
	
	public static String encode(String encode)
	{
		return PACKET_HEAD+encode+PACKET_END;
	}
	

}
