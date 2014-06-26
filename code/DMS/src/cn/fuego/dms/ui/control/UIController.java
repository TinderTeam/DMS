package cn.fuego.dms.ui.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Display;

import cn.fuego.dms.service.DataCollectionService;
import cn.fuego.dms.service.impt.DataCollectionServiceImpl;
import cn.fuego.dms.ui.frame.MainFrame;


 
public class UIController extends Thread
{
	private Log log = LogFactory.getLog(UIController.class);
	DataCollectionService dataCollectionServcie = new DataCollectionServiceImpl();
	
	int REFRASH_RATE=1000;
	private MainFrame frame;
	public UIController(MainFrame mainFrame){
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
				Display.getDefault().asyncExec(new Runnable(){
				    @Override
				    public void run() {
				    	
				    	log.info("刷新数据"); 
				    	
						frame.updateData(
								dataCollectionServcie.getDataByBaseSiteName(frame.getBaseID())
								);
				    }
				   });
				
				
				UIController.sleep(REFRASH_RATE);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
