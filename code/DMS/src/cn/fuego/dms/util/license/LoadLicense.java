/**   
 * @Title: LoadLicense.java 
 * @Package cn.melina.license 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-7-8 上午10:08:13 
 * @version V1.0   
 */
package cn.fuego.dms.util.license;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.util.SystemConfigInfo;
import cn.fuego.dms.util.file.property.PropertyReader;
import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * @ClassName: LoadLicense
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-7-8 上午10:08:13
 * 
 */

public class LoadLicense
{
	private final Log log = LogFactory.getLog(PropertyReader.class);

	private String PUBLICALIAS = "";
	private String STOREPWD = "";
	private String SUBJECT = "";
	private String licPath = "";
	private String pubPath = "";

	private static final String LICENSE_ROOT_PATH = SystemConfigInfo.getSystemRootPath() + "license";

	private static final String PARAM_PATH = File.separator + "param.properties";

	private LicenseManager licenseManager;

	private static LoadLicense instance;

	private static final String LICENSE_IS_NOT_EXIST = "证书不存在！";
	private static final String LICENSE_IS_WRONG = "证书有误！";
	private static final String LICENSE_IS_EXPIRED = "证书已经过期！";

	private LoadLicense()
	{
		readParamFile();
	}

	public static synchronized LoadLicense getInstance()
	{
		if (null == instance)
		{
			instance = new LoadLicense();
		}
		return instance;
	}

	private void readParamFile()
	{
		Properties prop;
		prop = new Properties();
		try
		{
			InputStream inStream = new FileInputStream(LICENSE_ROOT_PATH + PARAM_PATH);
			prop.load(inStream);
		}
		catch (IOException e)
		{
			log.error("can not read licese parameter file", e);
			throw new RuntimeException();
		}

		log.info(prop.toString());

		PUBLICALIAS = prop.getProperty("PUBLICALIAS");
		STOREPWD = prop.getProperty("STOREPWD");
		SUBJECT = prop.getProperty("SUBJECT");
		licPath = LICENSE_ROOT_PATH + File.separator + prop.getProperty("licPath");
		pubPath = prop.getProperty("pubPath");

	}

	private void install()
	{
		File file = new File(licPath);
		if (file.exists())
		{
			log.info("file is exist. the path is :" + file.getAbsolutePath());
		}
		else
		{
			log.error("file is not exist.");
			throw new RuntimeException(LICENSE_IS_NOT_EXIST);
		}
		licenseManager = LicenseManagerHolder.getLicenseManager(getLicenseParams());
		try
		{

			licenseManager.install(file);
		}
		catch (Exception e)
		{
			log.error("install license failed", e);
			throw new RuntimeException(LICENSE_IS_WRONG);
		}
	}

	public boolean verify()
	{
		install();

		try
		{
			licenseManager.verify();
		}
		catch (Exception e)
		{
			log.error("license verify failed", e);
			throw new RuntimeException(LICENSE_IS_EXPIRED);
		}

		log.info("license is right");

		return true;
	}

	public LicenseParam getLicenseParams()
	{

		Preferences preference = Preferences.userRoot();
		CipherParam cipherParam = new DefaultCipherParam(STOREPWD);

		KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(LoadLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
		LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT, preference, privateStoreParam, cipherParam);
		return licenseParams;
	}
}
