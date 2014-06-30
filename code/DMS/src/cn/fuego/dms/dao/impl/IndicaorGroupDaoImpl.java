package cn.fuego.dms.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.fuego.dms.dao.IndicateGroupDao;
import cn.fuego.dms.dao.constant.XMLPathConstant;
import cn.fuego.dms.dao.impl.visitor.IndicatorGroupVisitor;
import cn.fuego.dms.domain.po.IndicatorGroup;
import cn.fuego.dms.ui.control.UIController;
import cn.fuego.dms.util.file.dom4j.XMLReader;

public class IndicaorGroupDaoImpl implements IndicateGroupDao
{
	private Log log = LogFactory.getLog(UIController.class);
	public List<IndicatorGroup> getAll()
	{
	
		try
		{
			IndicatorGroupVisitor v=new IndicatorGroupVisitor();
			new XMLReader(XMLPathConstant.INDICATE_GROUP_CONFIG_PATH,v);
			return v.getList();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
}
