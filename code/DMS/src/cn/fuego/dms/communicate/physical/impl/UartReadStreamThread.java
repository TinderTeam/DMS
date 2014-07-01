/**   
* @Title: ReadStreamThread.java 
* @Package cn.fuego.dmsp.server 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-27 下午07:00:11 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.physical.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @ClassName: ReadStreamThread 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-27 下午07:00:11 
 *  
 */

public class UartReadStreamThread extends Thread
{
	private Log log = LogFactory.getLog(UartReadStreamThread.class);

    private InputStream inputStream;
    private Queue<String> messageBuffer;

    public UartReadStreamThread(InputStream inputStream,Queue<String>  messageBuffer)
    {
    	this.inputStream = inputStream;
    	this.messageBuffer = messageBuffer;
    }
    @Override
    public void run()
    {

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  

		String line;
    	try
		{
    		
    			while((line=br.readLine()) != null)
				{	 
					
    				synchronized(messageBuffer)
    	    		{
					   log.info("read line " + line);
 					   messageBuffer.add(line);
					   messageBuffer.notify();
    	    		}
				}
    		
			
		}
		catch (IOException e)
		{
			log.error("read data error",e);
		}
    }
}
