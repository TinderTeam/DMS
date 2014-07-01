package cn.fuego.dms.ui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cn.fuego.dms.communicate.protocol.gprs.GPRSFactory;
import cn.fuego.dms.communicate.protocol.gprs.GPRSOperator;
import cn.fuego.dms.ui.frame.AboutUsDialog;
import cn.fuego.dms.ui.frame.MainJFrame;

public class MenuActionListener implements  ActionListener

{
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
			GPRSOperator gprsOperator =GPRSFactory.getInstance().getGPRSOperator(); 
			gprsOperator.closeGPRS();
			System.exit(0);
		}else if(item.getActionCommand().equals("刷新")){
			
		}else if(item.getActionCommand().equals("关于DMS...")){
			AboutUsDialog dlg=new AboutUsDialog();
			dlg.show();
		}else {
			;
		}
	}


}
