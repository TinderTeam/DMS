/**   
 * @Title: GPRSFactory.java 
 * @Package cn.fuego.dms.service.collector.gprs 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-6-26 上午10:24:30 
 * @version V1.0   
 */
package cn.fuego.dms.communicate.protocol.gprs;

import cn.fuego.dms.communicate.protocol.gprs.impl.Huawei2GGPRSOperatorImpl;

/**
 * @ClassName: GPRSFactory
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 上午10:24:30
 * 
 */

public class GPRSFactory
{
	private static GPRSFactory instance;
	private GPRSOperator gprs;

	private GPRSFactory()
	{

	}

	public static synchronized GPRSFactory getInstance()
	{
		if (null == instance)
		{
			instance = new GPRSFactory();
		}
		return instance;
	}

	public synchronized GPRSOperator getGPRSOperator()
	{
		if (null == gprs)
		{
			gprs = new Huawei2GGPRSOperatorImpl();
		}

		return gprs;
	}

}
