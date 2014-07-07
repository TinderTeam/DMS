/**   
* @Title: Huawei2GGPRSOperateImpl.java 
* @Package cn.fuego.dms.service.collector.gprs 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-26 上午10:16:39 
* @version V1.0   
*/ 
package cn.fuego.dms.communicate.protocol.gprs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.communicate.exception.CommunicateException;
import cn.fuego.dms.communicate.physical.PhysicalChannel;
import cn.fuego.dms.communicate.physical.PhysicalChannelFactory;
import cn.fuego.dms.communicate.protocol.gprs.GPRSOperator;
import cn.fuego.dms.util.validate.ValidatorUtil;

/** 
 * @ClassName: Huawei2GGPRSOperateImpl 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-26 上午10:16:39 
 *  
 */

public class Huawei2GGPRSOperatorImpl implements GPRSOperator
{
	private Log log = LogFactory.getLog(Huawei2GGPRSOperatorImpl.class);

	private PhysicalChannel communicator = PhysicalChannelFactory.getInstance().getCommunicator();
	
	private boolean IS_OPEN_IPEN_MODE = false; 
	
	private int sigStrength = 0;
	private String connNetName = "";
	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.collector.gprs.GPRSOperate#initGPRS()
	 */
	
	private String addEndFlag(String cmd)
	{
		return cmd+GPRSCmdConst.CMD_SEND_END_FLAG;
	}
	
	private String sendCmd(String cmd)
	{
		return sendCmd(cmd ,1);
	}

	private String sendCmd(String cmd,int flagNum)
	{
		log.info("the cmd is :" + cmd);
		communicator.sendData(addEndFlag(cmd));
		//read send command echo 
		String cmdMessage = communicator.readData(GPRSCmdConst.CMD_SEND_END_FLAG);
		
 
		for(int i=0;i<flagNum;i++)
		{	
			String temp = communicator.readData(GPRSCmdConst.END_FLAG);
			if(!GPRSCmdConst.END_FLAG.equals(temp))
			{
				log.error("the cmd is :" + cmd);
				log.error("the result is :" + temp);
				throw new CommunicateException(CommunicateException.GRPR_CAN_NOT_WORK);
			}
		}
 
		//read operate result
		String result = communicator.readData(GPRSCmdConst.END_FLAG);
		result = removeEndFlag(result);
		log.info("the result is :" + result);
		if(!GPRSCmdConst.READ_DATA_OK_FLAG.equals(result))
		{
			log.error("the cmd is :" + cmd);
			log.error("the result is :" + result);
			throw new CommunicateException(CommunicateException.GRPR_CAN_NOT_WORK);
		}
		return result;

	}
	private String readDataByCmd(String cmd)
	{
		communicator.sendData(addEndFlag(cmd));
		//read send command echo 
		String cmdMessage = communicator.readData(GPRSCmdConst.END_FLAG);
		log.info("cmd message "+cmdMessage);
		//read query data
		String message = communicator.readData(GPRSCmdConst.END_FLAG);
		message = removeEndFlag(message);
		log.info("the read data is :" + message);
		
		//read operate result
		communicator.readData(GPRSCmdConst.END_FLAG);
		String result = communicator.readData(GPRSCmdConst.END_FLAG);
		result = removeEndFlag(result);
		log.info("the result is :" + result);
		if(!GPRSCmdConst.READ_DATA_OK_FLAG.equals(result))
		{
			log.error("the result is :" + result);
			throw new CommunicateException(CommunicateException.GRPR_CAN_NOT_WORK);
		}
		
		return message;

	}
	private String removeEndFlag(String result)
	{
		int index = result.indexOf(GPRSCmdConst.END_FLAG);
		String temp = "";
		if(index>=0)
		{
			temp = result.substring(0, index);
		}
		return temp;
	}
	@Override
	public void initGPRS(String serverIP,String serverPort,String communicatorPort)
	{
		communicator.init(communicatorPort);
		try
		{
			sendCmd(GPRSCmdConst.CLOSE_PROFILE_CONN,2);
		}
		catch(CommunicateException e)
		{
			log.error("close GPRS failed",e);
		}
 
		sendCmd(GPRSCmdConst.AT);
 
		sendCmd(GPRSCmdConst.OPEN_GRPS_NETWORK);
 
		if(!isGPRSOK())
		{
			log.error("the GPRS can not work");
			throw new CommunicateException(CommunicateException.GRPR_CAN_NOT_WORK);
		}
		
		//first close the connection
 
		for(int i=0;i<10;i++)
		{
			boolean setSuccess=false;
			try
			{
				sendCmd(GPRSCmdConst.SET_PROFILE_MODE,2);
				setSuccess=true;
			}
			catch(CommunicateException e)
			{
				log.error("set GPRS failed,because the seivice have not been done.");
				log.error("now trying to set again. we have tried " + i + " times");
				setSuccess=false;
				try
				{
					Thread.sleep(3000);
				}
				catch (InterruptedException e1)
				{
					log.error("sleep failed",e);
				}
			}
			if(setSuccess)
			{
				log.info("set success");
				break;
			}
		}
		
		
		sendCmd(GPRSCmdConst.SET_PROFILE_NAME,2);
		sendCmd(GPRSCmdConst.SET_PROFILE_SER_TYPE,2);
		
		String cmd=GPRSCmdConst.SET_SERVER_IP + serverIP + ":" + serverPort;
		sendCmd(cmd,2);
		
		sendCmd(GPRSCmdConst.SET_PROFILE_CONN_NUM,2);
		
		//open the connection
		sendCmd(GPRSCmdConst.OPEN_PROFILE_CONN,2);
		
		//for the open command,there are some message after OK message
		communicator.readData(GPRSCmdConst.END_FLAG);
		communicator.readData(GPRSCmdConst.END_FLAG);

		this.openIpenTrans();
		
	}
	private boolean isGPRSOK()
	{
		loadConnectInfo();
		boolean isOK = true;
		
		try
		{
			if(0==getSignalInfo())
			{
				log.error("the GPRS can not work, the signal strength is zero");
				return false;
			}
			if(ValidatorUtil.isEmpty(getConnNetName()))
			{
				log.error("the GPRS can not work ,the connect network name is empty");
				return false;
			}
		}
		catch(CommunicateException e)
		{
			log.error("can not communicate with GRPS",e);
			return false;
		}

		
		log.info("the GPRS is ok");
		return isOK;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.collector.gprs.GPRSOperate#getSingalInfo()
	 */
	@Override
	public int getSignalInfo()
	{
		return sigStrength;
	}

	public void loadConnectInfo()
	{
		// TODO Auto-generated method stub
		String signalInfo = this.readDataByCmd(GPRSCmdConst.CHECK_SINGAL);
		
		int sigStrength = 0;
		
		String startFlag = "+CSQ:";
		int sIndex = signalInfo.indexOf(startFlag);
		int eIndex = signalInfo.indexOf(",");
		
		try
		{
			String temp = signalInfo.substring(sIndex+startFlag.length(), eIndex);
			sigStrength = Integer.valueOf(temp.trim());
		}
		catch(Exception e)
		{
			log.error("can not get the signal strength. + signalInfo is " + signalInfo);
		}
		
		log.info("the signal is " + sigStrength);
		
		this.sigStrength = sigStrength;
	 
		//load connect network information
		String netInfo = this.readDataByCmd(GPRSCmdConst.CHECK_NET_INFO);

		String splitFlag ="\"";
		
		String netName = "";
		String[] temp = netInfo.split(splitFlag);
		if(null != temp && temp.length == 2)
		{
			netName = temp[1];
		}
		else
		{
			log.error("can not get connect net information. netInfo is : "  + netInfo);
		}
		this.connNetName = netName;
	}
 
	/* (non-Javadoc)
	 * @see cn.fuego.dms.service.collector.gprs.GPRSOperate#getNeworkInfo()
	 */
	@Override
	public String getConnNetName()
	{
		return this.connNetName;
	}
 
	public void sendData(String message)
	{
		log.info("send message is :"+message);

		if(this.IS_OPEN_IPEN_MODE)
		{
			this.sendDataIpenMode(message);
		}
		else
		{
			this.sendDataATMode(message);
		}
	}
 
	private void sendDataATMode(String message)
	{
		if(null == message || message.length() == 0)
		{
			log.warn("the message is empty, no need to send");
			return;
		}
		String sendCmd = GPRSCmdConst.SEND_DATA_CMD+message.length();
		this.communicator.sendData(this.addEndFlag(sendCmd));
		
		String result="";
		
		//read send command
		this.communicator.readData(GPRSCmdConst.CMD_SEND_END_FLAG);
		
		//read ^SISW: 0,5,5
		this.communicator.readData(GPRSCmdConst.END_FLAG);
		this.communicator.readData(GPRSCmdConst.END_FLAG);
		
		//send data
		this.communicator.sendData(message);
		
		readOKFlag();
		
		//read ^SISW: 0,1
		this.communicator.readData(GPRSCmdConst.END_FLAG);
		result = this.communicator.readData(GPRSCmdConst.END_FLAG);

	}

	private void readOKFlag()
	{
		String result;
		this.communicator.readData(GPRSCmdConst.END_FLAG);
		result = this.communicator.readData(GPRSCmdConst.END_FLAG);
		result = this.removeEndFlag(result);
		if(!GPRSCmdConst.READ_DATA_OK_FLAG.equals(result))
		{
			log.error("can not read ok flag.the result is :" + result);
			throw new CommunicateException(CommunicateException.GRPR_CMD_FAIL);
		}
	}

	public String readData(int length)
	{
		if(this.IS_OPEN_IPEN_MODE)
		{
			return this.readDataIpenMode(length);
		}
		else
		{	
			return this.readDataATMode(length);
		}
	}
 
	public String readData(String end)
	{
		if(this.IS_OPEN_IPEN_MODE)
		{
			return this.readDataIpenMode(end);
		}
		log.error("can not read by end flag, with AT mode");
		return null;
	}
	
	public String readDataATMode(int length)
	{
		log.info("waiting to read data now. length is " + length);
		String result = "";
		result = this.communicator.readData(GPRSCmdConst.END_FLAG);
		if(!GPRSCmdConst.END_FLAG.equals(result))
		{
			log.error("read data failed.the result is :" + result);
			throw new CommunicateException(CommunicateException.READ_DATA_FAILED);
		}
		//read data ready flag ^SISR=0,1  
		String readFlag = "^SISR:";
		result = this.communicator.readData(GPRSCmdConst.END_FLAG);
		result = this.removeEndFlag(result);
		int index = result.indexOf(readFlag);
		if(index < 0)
		{
			log.error("read data failed.the result is :" + result);
			throw new CommunicateException(CommunicateException.READ_DATA_FAILED);
		}
		//String temp = result.substring(index,);
		
		//send read command
		String readCmd = GPRSCmdConst.READ_DATA_CMD+length;
		readCmd = this.addEndFlag(readCmd);
		this.communicator.sendData(readCmd);
		
		//read send command 
		this.communicator.readData(GPRSCmdConst.CMD_SEND_END_FLAG);
		
		//read data ready flag AT^SISR=0,4
		this.communicator.readData(GPRSCmdConst.END_FLAG);
		this.communicator.readData(GPRSCmdConst.END_FLAG);
		
		//read data
		result = this.communicator.readData(GPRSCmdConst.END_FLAG);
		String readMessage = this.removeEndFlag(result);

		//read ok flag
		this.readOKFlag();
		
		
		return readMessage;
	}

	/* (non-Javadoc)
	 * @see cn.fuego.dms.communicate.protocol.gprs.GPRSOperator#closeGPRS()
	 */
	@Override
	public void closeGPRS()
	{
		if(this.IS_OPEN_IPEN_MODE)
		{
			this.closeIpenTrans();
		}
		
		try
		{
			sendCmd(GPRSCmdConst.CLOSE_PROFILE_CONN,2);
		}
		catch(CommunicateException e)
		{
			log.error("close grps failed.");
			throw e;
		}
		finally
		{
			this.communicator.close();
		}
 
	}

 
	public void openIpenTrans()
	{
		sendCmd(GPRSCmdConst.SET_IPEN_TRANS_PARA);
		sendCmd(GPRSCmdConst.OPEN_IPEN_TRANS);

		this.IS_OPEN_IPEN_MODE = true;
		log.info("set the data transmit mode is ipen trans mode.");

	}
	
	public void closeIpenTrans()
	{
		communicator.sendData(GPRSCmdConst.CLOSE_IPEN_TRANS);
		try
		{
			log.info("wait 1.5s for close ipen trans.");
			Thread.sleep(1500);
		}
		catch (InterruptedException e)
		{
			log.error("sleep interruted exception.",e);
		}
		this.communicator.readData(GPRSCmdConst.END_FLAG);
 		this.readOKFlag();
		this.IS_OPEN_IPEN_MODE = false;

	}
	private void sendDataIpenMode(String message)
	{
		communicator.sendData(message);
	}
	private String readDataIpenMode(String end)
	{
		String message = this.communicator.readData(end);
		return message;
	}
	private String readDataIpenMode(int length)
	{
		String message = this.communicator.readData(length);
		return message;
	}


}
