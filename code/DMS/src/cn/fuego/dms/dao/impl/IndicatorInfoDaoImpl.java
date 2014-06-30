package cn.fuego.dms.dao.impl;

import java.util.List;

import cn.fuego.dms.dao.IndicatorInfoDao;
import cn.fuego.dms.dao.constant.XMLPathConstant;
import cn.fuego.dms.dao.impl.visitor.IndicatorInfoVisitor;
import cn.fuego.dms.domain.po.IndicatorInfo;
import cn.fuego.dms.util.file.dom4j.XMLReader;

public class IndicatorInfoDaoImpl implements IndicatorInfoDao
{
	
	@Override
	public List<IndicatorInfo> getAll()
	{
	
		try
		{
			IndicatorInfoVisitor v=new IndicatorInfoVisitor();
			new XMLReader(XMLPathConstant.INDICATE_CONFIG_PATH,v);
			return v.getList();
			
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
