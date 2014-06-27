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

public class DailogActionListener implements  ActionListener

{
	DataCollectionService dataCollectionServcie = new DataCollectionServiceImpl();	

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
