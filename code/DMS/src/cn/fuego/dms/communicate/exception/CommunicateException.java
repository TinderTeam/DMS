/**   
* @Title: CommunicateException.java 
* @Package cn.fuego.bse.communicate.exception 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午03:28:26 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.exception;

/** 
 * @ClassName: CommunicateException 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午03:28:26 
 *  
 */

public class CommunicateException extends RuntimeException
{
	public static final String PORT_OPERATE_FAILED = "port operate failed";

	public static final String PORT_CAN_NOT_FIND = "can not find the port";
	public static final String SEND_DATA_FAILED = "send data to port failed";
	public static final String READ_DATA_FAILED = "read data to port failed";

	
	public static final String PORT_IN_USED = "the port is used";
	
	public static final String GRPR_CAN_NOT_WORK ="the gprs can not work";
	
	public static final String GRPR_CMD_FAIL ="the gprs command failed";

	public static final String GRPR_SEND_DATA_FAIL ="the gprs send failed";
	public static final String GRPR_READ_DATA_FAIL ="the gprs read failed";




	/**
	 * 
	 */
	public CommunicateException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CommunicateException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CommunicateException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CommunicateException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	 
}
