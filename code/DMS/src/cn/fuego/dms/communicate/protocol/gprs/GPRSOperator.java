/**   
* @Title: GPRSOperate.java 
* @Package cn.fuego.dms.service.collector 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-26 上午09:51:49 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.protocol.gprs;

/** 
 * @ClassName: GPRSOperate 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 上午09:51:49 
 *  
 */

public interface GPRSOperator
{
 
	public void initGPRS(String serverIP,String serverPort,String communicatorPort);
	
	public void closeGPRS();
	
	public void closePhysicalPort();
	
	public int getSignalInfo();
	
	public String getConnNetName();
	
	public void sendData(String message);
	
	public String readData(int length);
	public String readData(String end);	

}
