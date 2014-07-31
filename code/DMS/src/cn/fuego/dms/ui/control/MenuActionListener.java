package cn.fuego.dms.ui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.service.collector.CollectTask;
import cn.fuego.dms.ui.frame.AboutUsDialog;
import cn.fuego.dms.ui.frame.MainJFrame;

public class MenuActionListener implements ActionListener

{
	private Log log = LogFactory.getLog(CollectTask.class);

	static MenuActionListener listner;
	MainJFrame frame;

	public MenuActionListener(MainJFrame jfm)
	{
		frame = jfm;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem item = (JMenuItem) e.getSource();
		log.info("the command is :" + item.getActionCommand());

 		if (item.getActionCommand().equals("退出"))
		{

		}
		else if (item.getActionCommand().equals("刷新"))
		{

		}
		else if (item.getActionCommand().equals("关于DMS..."))
		{
			AboutUsDialog dlg = new AboutUsDialog();
			dlg.setVisible(true);
		}
		else if (item.getActionCommand().equals("连接服务器"))
		{
			frame.startController();
		}
		else
		{
			;
		}
	}

}
