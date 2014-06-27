/**   
* @Title: CollectProtocol.java 
* @Package cn.fuego.bse.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:17:27 
* @version V1.0   
*/ 
package cn.fuego.dms.service.collector;

import java.util.List;

import cn.fuego.dms.service.model.Collection;

/** 
 * @ClassName: CollectProtocol 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:17:27 
 *  
 */

public class ApplicationProtocol
{
	private static final String PACKET_HEAD = "whut";
	private static final String PACKET_END = "fuego";
	
	public boolean isValid(String data)
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
	
	public List<Collection> decode(String data)
	{
		return null;
	}
	
	public String encode()
	{
		return null;
	}
	

}
