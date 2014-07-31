/**   
 * @Title: Test.java 
 * @Package cn.fuego.dms.main 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-6-28 上午01:08:02 
 * @version V1.0   
 */
package cn.fuego.dms.main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.util.algorithm.FormulaParser;

/**
 * @ClassName: Test
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-28 上午01:08:02
 * 
 */

public class Test
{
	private static Log log = LogFactory.getLog(Test.class);

	public static void main(String[] args)
	{
		FormulaParser parser = new FormulaParser("2*(3-4)+7/3");
		double a = parser.getResult();

		System.out.println("the value is " + a);

		getStrings();
		// LoadLicense.getInstance().verify();
	}

	private static void getStrings()
	{
		String str = "index{3}*256+index{2}";
		Pattern p = Pattern.compile("index\\{(.*?)\\}");
		Matcher m = p.matcher(str);
 		String formula = str;
		while (m.find())
		{
			String indexFormula = m.group(0);
			String index = m.group(1);
			String value = index;
			formula = formula.replace(indexFormula, value);
		}
		
		System.out.println(formula);
		 
	}
}
