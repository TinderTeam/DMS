package cn.fuego.dms.dao.impl.visitor;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import cn.fuego.dms.domain.po.DataFormat;

public class DataFormatVisitor extends VisitorSupport
{
	private List<DataFormat> list = new ArrayList<DataFormat>();
 
	public void visit(Element element)
	{
		DataFormat b = new DataFormat();
 		getList().add(b);
	}

	public void visit(Attribute attr)
	{
		if (attr.getName().equals("seq"))
		{
			list.get(list.size() - 1).setSeq(Integer.valueOf(attr.getValue()));
		}
		else if (attr.getName().equals("indicatorID"))
		{
			list.get(list.size() - 1).setIndicatorID(Integer.valueOf(attr.getValue()));
		}
		else if (attr.getName().equals("dataLength"))
		{
			list.get(list.size() - 1).setDataLength(Integer.valueOf(attr.getValue()));
		}

	}
	public List<DataFormat> getList()
	{
		return list;
	}

	public void setList(List<DataFormat> list)
	{
		this.list = list;
	}
}