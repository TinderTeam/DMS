package cn.fuego.dms.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;

import cn.fuego.dms.dao.DataFormatDao;
import cn.fuego.dms.dao.constant.XMLPathConstant;
import cn.fuego.dms.dao.impl.visitor.DataFormatVisitor;
import cn.fuego.dms.domain.po.DataFormat;
import cn.fuego.dms.util.file.dom4j.XMLReader;

public class DataFormatDaoImpl implements DataFormatDao
{
	private Log log = LogFactory.getLog(DataFormatDaoImpl.class);
	private static final String FILE_PATH =XMLPathConstant.configPath+"dataFormat.xml";

	@Override
	public List<DataFormat> getAll()
	{

		DataFormatVisitor v = new DataFormatVisitor();
		try
		{
			new XMLReader(FILE_PATH, v);
		}
		catch (DocumentException e)
		{
			log.error("xml reader exception.",e);
			throw new RuntimeException();
		}
		return v.getList();

	}

}
