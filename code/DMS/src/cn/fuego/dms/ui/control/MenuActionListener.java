package cn.fuego.dms.ui.control;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import cn.fuego.dms.service.DataCollectionService;
import cn.fuego.dms.service.impt.DataCollectionServiceImpl;
import cn.fuego.dms.ui.frame.AboutUsDialog;
import cn.fuego.dms.ui.frame.MainJFrame;

public class MenuActionListener implements  ActionListener

{
	DataCollectionService dataCollectionServcie = new DataCollectionServiceImpl();	
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
