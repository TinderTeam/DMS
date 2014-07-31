package cn.fuego.dms.util.algorithm;

import java.util.Vector;

public class FormulaParser
{
	private int leftBracket = 0;// 左括号个数
	private int rightBracket = 0;// 右括号个数
	private int startL = 0;
	private int startR = 0;
	private double answer = 0;
	private String strValue = "";
	private String leftNumber = "0";
	private String rightNumber = "0";
	public String Msg = "";
	private String formula = "";
	private int[] sym = new int[4];
	private Vector list = new Vector();

	public FormulaParser(String calRule)
	{
		this.setFormula(calRule);
	}

	private int getLeftBracket(String calRule)
	{
		leftBracket = 0;
		startL = calRule.indexOf("(");
		if (startL != -1)
		{
			calRule = calRule.substring(startL + 1, calRule.length());
		}
		while (startL != -1)
		{
			leftBracket++;
			startL = calRule.indexOf("(");
			calRule = calRule.substring(startL + 1, calRule.length());
		}
		return leftBracket;
	}

	private void setLeftBracket(int leftBracket)
	{
		this.leftBracket = leftBracket;
	}

	private void setFormula(String calRule)
	{
		formula = replaceSubtration(calRule.trim());
		formula = "(" + formula + ")";
	}

	/*
	 * /*为了使公式中支持负数，使用“`”表示减号，使用“-”表示负号
	 */
	private String replaceSubtration(String vstr)
	{
		String tmp = "";
		String result = "";
		int startS = vstr.indexOf("-");
		if (startS != -1)
		{
			if (startS > 0)
			{
				tmp = vstr.substring(startS - 1, startS);
				if (!"+".equals(tmp) && !"-".equals(tmp) && !"*".equals(tmp) && !"/".equals(tmp) && !"(".equals(tmp))
				{
					result = result + vstr.substring(0, startS) + "`";
				}
				else
					result = result + vstr.substring(0, startS + 1);
			}
			else
				result = result + vstr.substring(0, startS + 1);
			vstr = vstr.substring(startS + 1);
		}
		while (startS != -1)
		{
			startS = vstr.indexOf("-");
			if (startS > 0)
			{
				tmp = vstr.substring(startS - 1, startS);
				if (!"+".equals(tmp) && !"-".equals(tmp) && !"*".equals(tmp) && !"/".equals(tmp) && !"(".equals(tmp))
					result = result + vstr.substring(0, startS) + "`";
				else
					result = result + vstr.substring(0, startS + 1);
			}
			else
				result = result + vstr.substring(0, startS + 1);
			vstr = vstr.substring(startS + 1);
		}
		result += vstr;
		return result;
	}
 
	private int getRightBracket(String calRule)
	{
		rightBracket = 0;
		startR = calRule.indexOf(")");
		if (startR != -1)
		{
			calRule = calRule.substring(startR + 1, calRule.length());
		}
		while (startR != -1)
		{
			rightBracket++;
			startR = calRule.indexOf(")");
			calRule = calRule.substring(startR + 1, calRule.length());
		}
		return rightBracket;
	}

	private void setRightBracket(int rightBracket)
	{
		this.rightBracket = rightBracket;
	}

	/*
	 * /*对比左右括号个数
	 */
	private boolean compareToLR()
	{
		int lb = getLeftBracket(formula);
		int rb = getRightBracket(formula);
		boolean CTLR = false;
		if (lb == rb)
		{
			Msg = "";
			CTLR = true;
		}
		else if (lb > rb)
		{
			Msg = "左括弧的个数多于右括弧，请检查！";
			CTLR = false;
		}
		else
		{
			Msg = "左括弧的个数少于右括弧，请检查！";
			CTLR = false;
		}
		return CTLR;
	}

	/*
	 * /*检查公式中是否存在非法字符如(+、-)等
	 */
	private boolean checkFormula()
	{
		boolean isOk = true;
		String[] bracket = new String[2];
		String[] sign = new String[4];
		bracket[0] = "(";
		bracket[1] = ")";
		sign[0] = "+";
		sign[1] = "`";
		sign[2] = "*";
		sign[3] = "/";
		String vstr = "";
		for (int i = 0; i < bracket.length; i++)
		{
			for (int j = 0; j < sign.length; j++)
			{
				if (i == 0)
					vstr = bracket[i] + sign[j];
				else
					vstr = sign[j] + bracket[i];
				if (formula.indexOf(vstr) > 0)
				{
					Msg = "公式中存在非法字符" + vstr;
					isOk = false;
					return isOk;
				}
			}
		}
		for (int i = 0; i < sign.length; i++)
		{
			for (int j = 0; j < sign.length; j++)
			{
				vstr = sign[i] + sign[j];
				if (formula.indexOf(vstr) > 0)
				{
					Msg = "公式中存在非法字符" + vstr;
					isOk = false;
					return isOk;
				}
			}
		}
		if (formula.indexOf("()") > 0)
		{
			Msg = "公式中存在非法字符()";
			isOk = false;
		}
		return isOk;
	}

	public boolean checkValid()
	{
		if ((formula == null) || (formula.trim().length() <= 0))
		{
			Msg = "请设置属性calRule!";
			return false;
		}
		return (compareToLR() && checkFormula());
	}

	/*
	 * /*返回公式执行结果 return double
	 */
	public double getResult()
	{
		String formulaStr = "", calRule = "";
		double value = 0.0;
		calRule = this.formula;
		if (checkValid())
		{
			for (int i = 0; i < leftBracket; i++)
			{
				int iStart = calRule.lastIndexOf("(") + 1;
				formulaStr = calRule.substring(iStart, iStart + calRule.substring(iStart).indexOf(")")).trim();
				symbolParse(formulaStr);
				value = parseString();
				iStart = calRule.lastIndexOf("(");
				int iEnd = calRule.substring(iStart).indexOf(")") + 1;
				calRule = calRule.substring(0, iStart).trim() + value + calRule.substring(iStart + iEnd, calRule.length()).trim();
			}
		}
 		double tmp = Math.pow(10, 10);
		value = Math.round(value * tmp) / tmp;
		return value;
	}

 

	/*
	 * /*抽取最终括号内数据到List
	 */
	private void symbolParse(String str)
	{
		list.clear();
		for (int i = 0; i < 4; i++)
		{
			compareMin(str);
			while (sym[i] != -1)
			{
				String insStr = str.substring(0, sym[i]).trim();
				list.add(insStr);
				insStr = str.substring(sym[i], sym[i] + 1).trim();
				list.add(insStr);
				str = str.substring(sym[i] + 1, str.length()).trim();
				compareMin(str);
			}
		}
		if (sym[0] == -1 && sym[1] == -1 && sym[2] == -1 & sym[3] == -1)
		{
			list.add(str);
		}
	}

	/*
	 * /*循环比较赋SubString起始值
	 */
	private void compareMin(String str)
	{
		int sps = str.indexOf("`");// 减法subtration
		sym[0] = sps;
		int spa = str.indexOf("+");// 加法addition
		sym[1] = spa;
		int spd = str.indexOf("/");// 除法division
		sym[2] = spd;
		int spm = str.indexOf("*");// 乘法multiplication
		sym[3] = spm;
		for (int i = 1; i < sym.length; i++)
		{
			for (int j = 0; j < sym.length - i; j++)
				if (sym[j] > sym[j + 1])
				{
					int temp = sym[j];
					sym[j] = sym[j + 1];
					sym[j + 1] = temp;
				}
		}
	}

	private double parseString()  
	{
		 calculate();
         return answer;
	}

	private void calculate()
	{
		/*
		 * /*处理除法
		 */
		int spd = list.indexOf("/");
		while (spd != -1)
		{
			leftNumber = list.get(spd - 1).toString();
			rightNumber = list.get(spd + 1).toString();
			list.remove(spd - 1);
			list.remove(spd - 1);
			list.remove(spd - 1);
			double ln = Double.valueOf(leftNumber).doubleValue();
			double rn = Double.valueOf(rightNumber).doubleValue();
			double answer = ln / rn;
			list.add(spd - 1, String.valueOf(answer));
			spd = list.indexOf("/");
		}
		/*
		 * /*处理乘法
		 */
		int spm = list.indexOf("*");
		while (spm != -1)
		{
			leftNumber = list.get(spm - 1).toString();
			rightNumber = list.get(spm + 1).toString();
			list.remove(spm - 1);
			list.remove(spm - 1);
			list.remove(spm - 1);
			double ln = Double.valueOf(leftNumber).doubleValue();
			double rn = Double.valueOf(rightNumber).doubleValue();
			double answer = ln * rn;
			list.add(spm - 1, String.valueOf(answer));
			spm = list.indexOf("*");
		}
		/*
		 * /*处理减法
		 */
		int sps = list.indexOf("`");
		while (sps != -1)
		{
			leftNumber = list.get(sps - 1).toString();
			rightNumber = list.get(sps + 1).toString();
			list.remove(sps - 1);
			list.remove(sps - 1);
			list.remove(sps - 1);
			double ln = Double.valueOf(leftNumber).doubleValue();
			double rn = Double.valueOf(rightNumber).doubleValue();
			double answer = ln - rn;
			list.add(sps - 1, String.valueOf(answer));
			sps = list.indexOf("`");
		}
		/*
		 * /*处理加法
		 */
		int spa = list.indexOf("+");
		while (spa != -1)
		{
			leftNumber = list.get(spa - 1).toString();
			rightNumber = list.get(spa + 1).toString();
			list.remove(spa - 1);
			list.remove(spa - 1);
			list.remove(spa - 1);
			double ln = Double.valueOf(leftNumber).doubleValue();
			double rn = Double.valueOf(rightNumber).doubleValue();
			double answer = ln + rn;
			list.add(spa - 1, String.valueOf(answer));
			spa = list.indexOf("+");
		}
		if (list.size() != 0)
		{
			String result = list.get(0).toString();
			if (result == null || result.length() == 0)
				result = "0";
			answer = Double.parseDouble(list.get(0).toString());
		}
	}
}