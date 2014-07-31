/**   
 * @Title: DataConvert.java 
 * @Package cn.fuego.misp.util.format 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-6-17 上午11:42:16 
 * @version V1.0   
 */
package cn.fuego.dms.util.format;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DataConvert
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-17 上午11:42:16
 * 
 */

public class DataTypeConvert
{
	public static List objectToList(Object str)
	{
		List strList = new ArrayList<Object>();
		if (null != str)
		{
			strList.add(str);
		}

		return strList;
	}

	public static List<String> toHexStringList(String str)
	{
		List<String> hexList = new ArrayList<String>();
		if (null != str)
		{
			for (int i = 0; i < str.length(); i++)
			{
				hexList.add(Integer.toHexString(str.charAt(i)));
			}
		}
		return hexList;
	}

	public static List<Integer> toIntList(String str)
	{
		List<Integer> hexList = new ArrayList<Integer>();
		if (null != str)
		{
			for (int i = 0; i < str.length(); i++)
			{
				int intValue = str.charAt(i);
				hexList.add(intValue);
			}
		}
		return hexList;
	}

	public static byte[] toAsciiBytes(String str)
	{
		byte[] bytes;
		if (null != str)
		{
			bytes = new byte[str.length()];
			for (int i = 0; i < str.length(); i++)
			{
				bytes[i] = (byte) str.charAt(i);
			}
		}
		else
		{
			bytes = new byte[1];
		}
		return bytes;
	}
}
