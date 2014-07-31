/**   
 * @Title: DataParser.java 
 * @Package cn.fuego.dms.service.collector 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2014-7-9 下午11:05:41 
 * @version V1.0   
 */
package cn.fuego.dms.service.collector;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.domain.po.DataFormat;
import cn.fuego.dms.util.algorithm.FormulaParser;
import cn.fuego.dms.util.format.DataTypeConvert;
import cn.fuego.dms.util.validate.ValidatorUtil;

/**
 * @ClassName: DataParser
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-7-9 下午11:05:41
 * 
 */

public class DataParser
{

	private static final String FARMULAR_ENUM_SPLIT = ":";
	private static final String FARMULAR_VALUE_PATTERN = "value\\{(.*?)\\}";

	private static final String FLOAT_TYPE = "float";
	private static final String INT_TYPE = "int";
	private static final String ENUM_TYPE = "enum";

	private static Log log = LogFactory.getLog(DataParser.class);

	public static String calcWithFormula(DataFormat format, List<Integer> dataBytes)
	{

		String disValue = "";

		if (!ValidatorUtil.isEmpty(format.getFormula()))
		{
			try
			{
				double calVaule = 0;

				calVaule = calcValueWithFormu(format.getFormula(), dataBytes);
				disValue = formatDisValue(calVaule,format.getDataType());
			}
			catch (Exception e)
			{
				log.error("calculate value failed", e);
				log.error("the formular is :" + format.getFormula());
			}
		}
		else
		{
			log.warn("the formaula is empty");
		}

		return disValue;
	}

	private static String formatDisValue(double calVaule,String dataType )
	{
		String disValue = "";
		if (dataType.startsWith(ENUM_TYPE))
		{
			String[] enumValueList = dataType.split(FARMULAR_ENUM_SPLIT);
			int index = (int) calVaule+1;
			if(index > 0 && index < enumValueList.length)
			{
				disValue = enumValueList[index];
			}
		}
		else if(dataType.startsWith(INT_TYPE))
		{
			disValue = String.valueOf((int)calVaule);
		}
		else
		{
			disValue = String.valueOf(calVaule);
		}
		return disValue;
	}

	private static double calcValueWithFormu(String formula, List<Integer> dataBytes)
	{
		double calVaule = 0;
 
		formula = handleFormula(formula, dataBytes);

		FormulaParser parser = new FormulaParser(formula);
		if (parser.checkValid())
		{
			calVaule = parser.getResult();
		}
		else
		{
			log.error("the formular is invalid.");
			log.error("the formular is :" + formula);
		}

		return calVaule;
	}

	private static String handleFormula(String originalFormula, List<Integer> dataBytes)
	{
		Pattern p = Pattern.compile(FARMULAR_VALUE_PATTERN);
		Matcher m = p.matcher(originalFormula);
		String formula = originalFormula;
		while (m.find())
		{
			String indexFormula = m.group(0);
			int index = Integer.valueOf(m.group(1));
			int indeValue = getValueByIndex(index, dataBytes);

			formula = formula.replace(indexFormula, String.valueOf(indeValue));
		}
		return formula;
	}

	private static int getValueByIndex(int index, List<Integer> dataBytes)
	{
		if (index < 0 || index >= dataBytes.size())
		{
			log.error("the formula index is not right range.the index is :" + index);
			throw new RuntimeException();
		}
		return dataBytes.get(index);
	}

}
