package cn.fuego.dms.main;

import java.awt.EventQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.service.collector.CollectTask;
import cn.fuego.dms.ui.frame.MainJFrame;

public class Main
{
	private static Log log = LogFactory.getLog(CollectTask.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		 
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		log.info("now exist system.");
	}
}
