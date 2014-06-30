/**   
* @Title: Collection.java 
* @Package cn.fuego.bse.service.model 
* @Description: TODO
* @author Tang Jun   
* @date 2014-6-25 下午11:39:14 
* @version V1.0   
*/ 
package cn.fuego.dms.service.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** 
 * @ClassName: Collection 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-6-25 下午11:39:14 
 *  
 */

public class Collection
{
	private List<Resource> resourceList = new ArrayList<Resource>();
	private Calendar collectTime;
	public List<Resource> getResourceList()
	{
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList)
	{
		this.resourceList = resourceList;
	}
	public Calendar getCollectTime()
	{
		return collectTime;
	}
	public void setCollectTime(Calendar collectTime)
	{
		this.collectTime = collectTime;
	}
	@Override
	public String toString()
	{
		return "Collection [resourceList=" + resourceList + ", collectTime=" + collectTime + "]";
	}
	
	
	
}
