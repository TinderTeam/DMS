package cn.fuego.dms.ui.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.communicate.protocol.gprs.GPRSFactory;
import cn.fuego.dms.communicate.protocol.gprs.GPRSOperator;
import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.ServiceContext;
import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.ui.frame.MainJFrame;
import cn.fuego.dms.util.file.property.PropertyItemNameConst;
import cn.fuego.dms.util.file.property.PropertyReader;

public class UIController extends Thread
{
	private Log log = LogFactory.getLog(UIController.class);
	DataCollectorService dataCollectionServcie = ServiceContext.getInstance().getCollectorService();

	int REFRASH_RATE = 10000;
	private MainJFrame frame;

	public UIController(MainJFrame mainFrame)
	{
		super();
		log.info("start");
		frame = mainFrame;
		initGPRS();

		this.start();
	}

	private void initGPRS()
	{
		GPRSOperator gprsOperator = GPRSFactory.getInstance().getGPRSOperator();

		try
		{
			gprsOperator.initGPRS(PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.SERVER_IP), PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.SERVER_PORT),
					PropertyReader.getInstance().getPropertyByName(PropertyItemNameConst.CONMMUNICATOR_PORT));
		}
		catch (Exception ex)
		{
			log.error("GPRS初始化失败");
		}
		dataCollectionServcie.start();

	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				Collection collection = dataCollectionServcie.getCurCollection();
				if (null != collection)
				{
					frame.updateData(collection.getResourceByResID(frame.getSelectedBaseSite()));
				}
				UIController.sleep(REFRASH_RATE);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
