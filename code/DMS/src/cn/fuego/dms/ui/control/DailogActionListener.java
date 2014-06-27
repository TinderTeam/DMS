package cn.fuego.dms.ui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.impl.DataCollectionServiceImpl;
import cn.fuego.dms.ui.frame.AboutUsDialog;

public class DailogActionListener implements  ActionListener

{
	DataCollectorService dataCollectionServcie = new DataCollectionServiceImpl();	

	AboutUsDialog dlg;
	
	public DailogActionListener(AboutUsDialog d){
		dlg=d;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		dlg.dispose();
	}


}
