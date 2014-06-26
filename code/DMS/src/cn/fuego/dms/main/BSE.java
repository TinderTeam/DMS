package cn.fuego.dms.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Display;

import cn.fuego.dms.ui.UIContext;
import cn.fuego.dms.ui.control.UIController;
import cn.fuego.dms.ui.frame.MainFrame;

public class BSE
{
	static Log log = LogFactory.getLog(BSE.class);
	
	public static void main(String[] arg0){
		try
		{
			//UIController uiController =new UIController();
			MainFrame  window= UIContext.getInstance().getMainFrame();
			
			Display.getCurrent().dispose();
		
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
