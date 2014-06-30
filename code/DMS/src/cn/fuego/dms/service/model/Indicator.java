/**   
* @Title: Indicator.java 
* @Package cn.fuego.bse.service.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:23:50 
* @version V1.0   
*/ 
package cn.fuego.dms.service.model;

/** 
 * @ClassName: Indicator 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:23:50 
 *  
 */

public class Indicator
{
	private int indicatorID;
	private String name;
	private String unit;
	private int dataType;
	private String value;
	public int getIndicatorID()
	{
		return indicatorID;
	}
	public void setIndicatorID(int indicatorID)
	{
		this.indicatorID = indicatorID;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public int getDataType()
	{
		return dataType;
	}
	public void setDataType(int dataType)
	{
		this.dataType = dataType;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
	@Override
	public String toString()
	{
		return "Indicator [indicatorID=" + indicatorID + ", name=" + name + ", unit=" + unit + ", dataType=" + dataType + ", value=" + value + "]";
	}
	
	

}
