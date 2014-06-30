/**
 * TODO
 * 上午01:07:45
 */
package cn.fuego.dms.util.file.property;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrator
 * 
 */
public class PropertyReader
{

    private static final Log log = LogFactory.getLog(PropertyReader.class);

    

    private static final String CONFIG_PATH = "Config.properties";
    private static PropertyReader instance;
    private Properties prop;

    private PropertyReader()
    {

        try
        {
            /* 而采用类加载器的话，能够更具有通用性 */
            /* 使用文件的读写的方式，文件的路径的相对路径确定了，不能修改 */
            prop = new Properties();
            InputStream inStream = PropertyReader.class.getClassLoader()
                    .getResourceAsStream(CONFIG_PATH);
            prop.load(inStream);
            
            log.info(prop.toString());

        } catch (Exception e)
        {
            throw new ExceptionInInitializerError(e);
        }
    }
    
	public static synchronized PropertyReader getInstance()
	{
		if (null == instance)
		{
			instance = new PropertyReader();
		}
		return instance;
	}

	public String getPropertyByName(String name)
	{
		 prop = new Properties();
         InputStream inStream = PropertyReader.class.getClassLoader()
                 .getResourceAsStream(CONFIG_PATH);
         try
		{
			prop.load(inStream);
			return prop.getProperty(name);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
         
	
	}


    
 

}
