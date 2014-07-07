package cn.fuego.dms.ui.model;

import javax.swing.tree.DefaultMutableTreeNode;

import cn.fuego.dms.domain.po.BaseSite;

public class BaseSiteTreeItem extends DefaultMutableTreeNode
{
	private String id;
	public BaseSiteTreeItem(BaseSite b)
	{
		super();
		this.setUserObject(b.getBaseSiteName());
		this.id=b.getResourceID();
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
}
