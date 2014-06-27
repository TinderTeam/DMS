package cn.fuego.dms.ui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.impl.DataCollectionServiceImpl;
import cn.fuego.dms.ui.frame.AboutUsDialog;
import cn.fuego.dms.ui.frame.MainJFrame;

public class MenuActionListener implements  ActionListener

{
	DataCollectorService dataCollectionServcie = new DataCollectionServiceImpl();	
	static MenuActionListener listner;
	MainJFrame frame;
	
	public MenuActionListener(MainJFrame jfm){
		frame=jfm;
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem item=(JMenuItem) e.getSource();
		System.out.println(item.getActionCommand());
		
		if(item.getActionCommand().equals("退出")){
			System.exit(0);
		}else if(item.getActionCommand().equals("刷新")){
			frame.updateData(
					dataCollectionServcie.getDataByBaseSiteName(frame.getSelectedBaseSite())
					);
		}else if(item.getActionCommand().equals("关于DMS...")){
			AboutUsDialog dlg=new AboutUsDialog();
			dlg.show();
		}else {
			;
		}
	}


}
