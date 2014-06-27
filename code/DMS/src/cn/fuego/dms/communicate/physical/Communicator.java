/**   
* @Title: Communicator.java 
* @Package cn.fuego.bse.communicate 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-24 下午05:24:30 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.physical;

import java.util.List;

/** 
 * @ClassName: Communicator 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-24 下午05:24:30 
 *  
 */

public interface Communicator
{
	public void init(String portName);
	public List<String> getPortList();

	public void sendData(String data);
	
	/**
	 * read data by length
	 * @param length
	 * @return
	 */
	public String readData(int length);
	
	/**
	 * read data by flag 
	 * @param end
	 * @param timeout
	 * @return
	 */
	public String readData(String end);
	
	public void close();
	
}
