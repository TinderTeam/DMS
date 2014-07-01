package cn.fuego.dms.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.dao.BaseSiteDao;
import cn.fuego.dms.dao.constant.XMLPathConstant;
import cn.fuego.dms.dao.impl.visitor.BaseSiteVisitor;
import cn.fuego.dms.domain.po.BaseSite;
import cn.fuego.dms.util.file.dom4j.XMLReader;

public class BaseSiteDaoImpl implements BaseSiteDao
{
	private Log log = LogFactory.getLog(BaseSiteDaoImpl.class);
	private static final String BASESIT_CONFIG_PATH =XMLPathConstant.configPath+"baseSite.xml";

	@Override
	public List<BaseSite> getAll()
	{
		BaseSiteVisitor v=new BaseSiteVisitor();

		try
		{
			new XMLReader(BASESIT_CONFIG_PATH,v);
		} catch (Exception e)
		{
			log.error("xml reader exception.",e);
			throw new RuntimeException();
		}
		return v.getList();		
	}
	

}
