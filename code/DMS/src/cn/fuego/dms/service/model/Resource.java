/**   
* @Title: Resource.java 
* @Package cn.fuego.bse.service.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:30:03 
* @version V1.0   
*/ 
package cn.fuego.dms.service.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * @ClassName: Resource 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:30:03 
 *  
 */

public class Resource
{
	private String resID;
	private String resName;
	private String resType;
	private List<Indicator> indicatorList = new ArrayList<Indicator>();
	public String getResID()
	{
		return resID;
	}
	public void setResID(String resID)
	{
		this.resID = resID;
	}
	public String getResName()
	{
		return resName;
	}
	public void setResName(String resName)
	{
		this.resName = resName;
	}
	public String getResType()
	{
		return resType;
	}
	public void setResType(String resType)
	{
		this.resType = resType;
	}
	public List<Indicator> getIndicatorList()
	{
		return indicatorList;
	}
	public void setIndicatorList(List<Indicator> indicatorList)
	{
		this.indicatorList = indicatorList;
	}
	@Override
	public String toString()
	{
		return "Resource [resID=" + resID + ", resName=" + resName + ", resType=" + resType + ", indicatorList=" + indicatorList + "]";
	}
	
	public Indicator getIndicatorByID(int indicatorID)
	{
		for(Indicator indicator:indicatorList)
		{
			if(indicatorID == indicator.getIndicatorID())
			{
				return indicator;
			}
		}
		return null;
	}
 	

}
