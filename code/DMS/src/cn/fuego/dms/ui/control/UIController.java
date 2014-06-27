package cn.fuego.dms.ui.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.impl.DataCollectionServiceImpl;
import cn.fuego.dms.ui.frame.MainJFrame;


 
public class UIController extends Thread
{
	private Log log = LogFactory.getLog(UIController.class);
	DataCollectorService dataCollectionServcie = new DataCollectionServiceImpl();
	
	int REFRASH_RATE=1000;
	private MainJFrame frame;
	public UIController(MainJFrame mainFrame){
		super();
		log.info("start");
		frame=mainFrame;
		this.start();
	}
	
	
	@Override
	public void run(){
		
		while(true){
			
			try
			{
				
				    	log.info("刷新数据"); 
				    	
						frame.updateData(
								dataCollectionServcie.getDataByBaseSiteName(frame.getSelectedBaseSite())
								);
				
				
				
				UIController.sleep(REFRASH_RATE);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
