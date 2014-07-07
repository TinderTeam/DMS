package cn.fuego.dms.ui.control;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.ServiceContext;
import cn.fuego.dms.ui.frame.MainJFrame;

public class UIController extends Thread
{
	private Log log = LogFactory.getLog(UIController.class);
	DataCollectorService dataCollectionServcie = ServiceContext.getInstance().getCollectorService();

	int REFRASH_RATE = 10000;
	private MainJFrame frame;

	public UIController(MainJFrame mainFrame) 
	{
		super();
 		frame = mainFrame;

	}

	public void start()
	{

		log.info("start");
		super.start();

	}

	@Override
	public void run()
	{
		while (true)
		{
			log.info("refresh the data.the time is "+Calendar.getInstance().getTime().toLocaleString());
		 
			try
			{
				frame.updateData();
				UIController.sleep(REFRASH_RATE);
			}
			catch (InterruptedException e)
			{
				log.error("the sleep interrupt.",e);

			}
		}

	}
	
	 
	public void stopThread()
	{
		try
		{
			dataCollectionServcie.stop();
		}
		catch(Exception e)
		{
			log.error("stop colletor failed",e);
		}
		this.stop();
	}
}
