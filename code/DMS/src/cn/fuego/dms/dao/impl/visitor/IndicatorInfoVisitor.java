package cn.fuego.dms.dao.impl.visitor;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import cn.fuego.dms.domain.po.IndicatorInfo;

public class IndicatorInfoVisitor extends VisitorSupport
{
	private List<IndicatorInfo> list = new ArrayList<IndicatorInfo>();

	public void visit(Element element)
	{
		IndicatorInfo b = new IndicatorInfo();
		b.setIndicateName(element.getStringValue());
		list.add(b);
	}

	public void visit(Attribute attr)
	{
		if (attr.getName().equals("id"))
		{
			list.get(list.size() - 1).setIndicateID(Integer.valueOf(attr.getValue()));
		}
		else if (attr.getName().equals("groupid"))
		{
			list.get(list.size() - 1).setIndicateGroupID(Integer.valueOf(attr.getValue()));
		}
		else if (attr.getName().equals("unit"))
		{
			list.get(list.size() - 1).setUnit(attr.getValue());
		}

	}

	/**
	 * @return the list
	 */
	public List<IndicatorInfo> getList()
	{
		return list;
	}

	/**
	 * @param list
	 *            the list to set
	 */
	public void setList(List<IndicatorInfo> list)
	{
		this.list = list;
	}
}