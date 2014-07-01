package cn.fuego.dms.dao.impl.visitor;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import cn.fuego.dms.domain.po.IndicatorGroup;

public class IndicatorGroupVisitor extends VisitorSupport
{
	private List<IndicatorGroup> list = new ArrayList<IndicatorGroup>();

	public void visit(Element element)
	{
		IndicatorGroup e = new IndicatorGroup();
		e.setIndicateGroupName(element.getStringValue());
		list.add(e);
	}

	public void visit(Attribute attr)
	{
		getList().get(getList().size() - 1).setIndicateGroupID(Integer.valueOf(attr.getValue()));
	}

	public List<IndicatorGroup> getList()
	{
		return list;
	}

	public void setList(List<IndicatorGroup> list)
	{
		this.list = list;
	}

}