package cn.fuego.dms.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.ui.frame.MainJFrame;
import cn.fuego.dms.ui.frame.WarningDialog;
import cn.fuego.dms.util.license.LoadLicense;

public class Main
{
	private static Log log = LogFactory.getLog(Main.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		log.info("starting system");

		boolean licenseOK = false;
		try
		{
			log.info("verifying license");
			licenseOK = LoadLicense.getInstance().verify();
		}
		catch (Exception e)
		{
			log.error("license is not ok.", e);
			WarningDialog dailog = new WarningDialog(e.getMessage());
			dailog.setVisible(true);
		}

		if (licenseOK)
		{
			log.info("license is ok");
			MainJFrame frame = new MainJFrame();
			frame.setVisible(true);
		}

	}
}
