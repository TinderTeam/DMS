package cn.fuego.dms.util.license;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * 
 * @ClassName: LicenseManagerHolder
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-7-6 下午04:05:40
 * 
 */
public class LicenseManagerHolder
{

	private static LicenseManager licenseManager;

	public static synchronized LicenseManager getLicenseManager(LicenseParam licenseParams)
	{
		if (licenseManager == null)
		{
			licenseManager = new LicenseManager(licenseParams);
		}
		return licenseManager;
	}
}