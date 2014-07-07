/**   
 * @Title: UartCommunicatorImpl.java 
 * @Package cn.fuego.bse.communicate 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-6-25 下午03:14:33 
 * @version V1.0   
 */
package cn.fuego.dms.communicate.physical.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.communicate.exception.CommunicateException;
import cn.fuego.dms.communicate.physical.PhysicalChannel;

/**
 * @ClassName: UartCommunicatorImpl
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午03:14:33
 * 
 */

public class UartCommunicatorImpl  implements PhysicalChannel, SerialPortEventListener
{
	private Log log = LogFactory.getLog(UartCommunicatorImpl.class);

	private String appName = "uart communicator";
	private int timeout = 20;// open 端口时的等待时间

	private CommPortIdentifier commPort;
	private SerialPort serialPort;
	private InputStream inputStream;
	private OutputStream outputStream;

	private int baudRate = 115200;

	private boolean isOpen = false;

	private StringBuffer messageBuffer = new StringBuffer();
 

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.bse.communicate.Communicator#init()
	 */
	@Override
	public void init(String portName)
	{
		setPort(portName);
		try
		{
			serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		}
		catch (UnsupportedCommOperationException e)
		{
			log.error("set uart parameter. baudRate is " + baudRate, e);
			throw new CommunicateException(CommunicateException.PORT_OPERATE_FAILED, e);
		}
		
		startRead();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.bse.communicate.Communicator#getPortList()
	 */
	@Override
	public List<String> getPortList()
	{
		List<String> portNameList = new ArrayList<String>();
		CommPortIdentifier cpid;
		Enumeration en = CommPortIdentifier.getPortIdentifiers();

		while (en.hasMoreElements())
		{
			cpid = (CommPortIdentifier) en.nextElement();
			if (cpid.getPortType() == CommPortIdentifier.PORT_SERIAL)
			{
				portNameList.add(cpid.getName());
			}
		}
		return portNameList;
	}

 
	private void setPort(String portName)
	{
		log.info("set uart port with " + portName);
		try
		{
			commPort = CommPortIdentifier.getPortIdentifier(portName);
		}
		catch (NoSuchPortException e)
		{
			log.error("can not find the port by " + portName, e);
			throw new CommunicateException(CommunicateException.PORT_CAN_NOT_FIND, e);
		}

		try
		{
			serialPort = (SerialPort) commPort.open(this.appName, this.timeout);
		}
		catch (PortInUseException e)
		{
			log.error("the port used by others", e);
			throw new CommunicateException(CommunicateException.PORT_IN_USED, e);
		}

	 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.bse.communicate.Communicator#sendData(java.lang.String)
	 */
	@Override
	public void sendData(String data)
	{
		if (null == data)
		{
			log.warn("the data is null, not need to write");
			return;
		}
		try
		{
			outputStream = new BufferedOutputStream(serialPort.getOutputStream());

		}
		catch (IOException e)
		{
			log.error("get output stream failed. data is " + data, e);
			throw new CommunicateException(CommunicateException.SEND_DATA_FAILED, e);
		}

		try
		{
			outputStream.write(data.getBytes());
		}
		catch (IOException e)
		{
			log.error("send data failed. the data is " + data, e);
			throw new CommunicateException(CommunicateException.SEND_DATA_FAILED, e);
		}
		finally
		{
			if (null != outputStream)
			{
				try
				{
					outputStream.close();
				}
				catch (Exception e)
				{
					log.error("close output stream failed", e);
				}
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.bse.communicate.Communicator#readData(java.lang.String, int)
	 */
	@Override
	public String readData(String end)
	{
		String readMessage = "";
		//messageBuffer.setLength(0);
		long nowTime = System.currentTimeMillis();
		while (true)
		{ 
			   readMessage = messageBuffer.toString();
			   int index = this.messageBuffer.indexOf(end);
			   
			   if(index >= 0)
			   {
				   readMessage = messageBuffer.substring(0,index+end.length());
				   messageBuffer.delete(0, readMessage.length());
				   break;
			   }

			   
			   if ((System.currentTimeMillis() - nowTime) / 1000 > timeout)
			   {
				   log.warn("read data time out. time out is " + timeout);
				   log.warn("now the buffer data is " + readMessage);
				   break;
			   }
 		}
		
 
		log.info("the uart read message is :"+readMessage);
		return readMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.fuego.bse.communicate.Communicator#readData(int)
	 */
	@Override
	public String readData(int length)
	{
		String readMessage ="";
		 
		long nowTime = System.currentTimeMillis();
		while (true)
		{ 
			   readMessage = messageBuffer.toString();
			   if(readMessage.length() >= length)
			   {
				   readMessage = messageBuffer.substring(0,length);
				   messageBuffer.delete(0, readMessage.length());
				   break;
			   }
			   if ((System.currentTimeMillis() - nowTime) / 1000 > timeout)
			   {
				   log.warn("read data time out. time out is " + timeout);
				   break;
			   }
 		}
		
 
		return readMessage;

	}

	private void startRead()
	{

		try
		{
			inputStream = new BufferedInputStream(serialPort.getInputStream());
		}
		catch (IOException e)
		{
			throw new CommunicateException(CommunicateException.PORT_OPERATE_FAILED, e);
		}

		try
		{
			serialPort.addEventListener(this);
		}
		catch (TooManyListenersException e)
		{
			throw new CommunicateException(e.getMessage(), e);
		}

		serialPort.notifyOnDataAvailable(true);

	}
    public void close()
    {
    	
    	if(null != this.inputStream)
    	{
    		try
			{
				this.inputStream.close();
			}
			catch (IOException e)
			{
				log.error("close input stream error",e);
			}
    	}
    	this.serialPort.removeEventListener();
        if(null != serialPort)
        {
        	serialPort.close();
        }
        this.serialPort = null;
        this.commPort = null;
        this.messageBuffer.setLength(0);
        
    }
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.comm.SerialPortEventListener#serialEvent(javax.comm.SerialPortEvent)
	 */
	@Override
	public void serialEvent(SerialPortEvent arg0)
	{
		switch (arg0.getEventType())
		{
		case SerialPortEvent.BI:/* Break interrupt,通讯中断 */
		case SerialPortEvent.OE:/* Overrun error，溢位错误 */
		case SerialPortEvent.FE:/* Framing error，传帧错误 */
		case SerialPortEvent.PE:/* Parity error，校验错误 */
		case SerialPortEvent.CD:/* Carrier detect，载波检测 */
		case SerialPortEvent.CTS:/* Clear to send，清除发送 */
		case SerialPortEvent.DSR:/* Data set ready，数据设备就绪 */
		case SerialPortEvent.RI:/* Ring indicator，响铃指示 */
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:/* Output buffer is empty，输出缓冲区清空 */
			break;
		case SerialPortEvent.DATA_AVAILABLE:/* Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端 */
			try
			{
				int ch;
				while((ch=inputStream.read()) > 0) 
				{   
					messageBuffer.append((char)ch);
					//log.info("reading from port," + messageBuffer.toString());	
				}   
				
			}
			catch (IOException e)
			{
			}
		}

	}
 
}
