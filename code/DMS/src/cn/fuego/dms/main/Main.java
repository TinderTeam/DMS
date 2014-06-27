package cn.fuego.dms.main;

import java.awt.EventQueue;

import cn.fuego.dms.ui.frame.MainJFrame;

public class Main
{
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
	}
}
