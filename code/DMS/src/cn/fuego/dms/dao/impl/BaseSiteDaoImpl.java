package cn.fuego.dms.dao.impl;

import java.util.List;

import cn.fuego.dms.dao.BaseSiteDao;
import cn.fuego.dms.dao.constant.XMLPathConstant;
import cn.fuego.dms.dao.impl.visitor.BaseSiteVisitor;
import cn.fuego.dms.domain.po.BaseSite;
import cn.fuego.dms.util.file.dom4j.XMLReader;

public class BaseSiteDaoImpl implements BaseSiteDao
{
	@Override
	public List<BaseSite> getAll()
	{
	
		try
		{
			BaseSiteVisitor v=new BaseSiteVisitor();
			new XMLReader(XMLPathConstant.BASESIT_CONFIG_PATH,v);
			return v.getList();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	

}
