/**   
* @Title: GPRSCmdConst.java 
* @Package cn.fuego.dms.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-26 上午09:30:44 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.protocol.gprs.impl;

/** 
 * @ClassName: GPRSCmdConst 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 上午09:30:44 
 *  
 */

public class GPRSCmdConst
{
	public static final String AT = "AT";
	
	public static final String CHECK_SINGAL = "AT+CSQ";
	public static final String CHECK_NET_INFO = "AT+COPS?";
	
	
	public static final String OPEN_GRPS_NETWORK = "AT+CGATT=1";
	
	public static final String SET_PROFILE_MODE = "AT^SICS=0,conType,GPRS0";
	public static final String SET_PROFILE_NAME = "AT^SICS=0,apn,cmnet";
	public static final String SET_PROFILE_SER_TYPE = "AT^SISS=0,srvType,Socket";
	
	public static final String SET_SERVER_IP = "AT^SISS=0,address,socktcp://";
	public static final String SET_PROFILE_CONN_NUM = "AT^SISS=0,conId,0";
	public static final String OPEN_PROFILE_CONN = "AT^SISO=0";
	public static final String CLOSE_PROFILE_CONN = "AT^SISC=0";
	
	public static final String OPEN_IPEN_TRANS = "AT^IPENTRANS=0";
	public static final String SET_IPEN_TRANS_PARA = "AT^IPCFL=10,1";
	public static final String CLOSE_IPEN_TRANS = "+++";

	
	public static final String SEND_DATA_CMD = "AT^SISW=0,";
	public static final String READ_DATA_CMD = "AT^SISR=0,";

	
	public static final String READ_DATA_OK_FLAG = "OK";
	
	public static final String CMD_SEND_END_FLAG = "\r";

	public static final String END_FLAG = "\r\n";


}
