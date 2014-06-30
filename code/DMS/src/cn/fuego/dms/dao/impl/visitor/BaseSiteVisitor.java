package cn.fuego.dms.dao.impl.visitor;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import cn.fuego.dms.domain.po.BaseSite;


public class BaseSiteVisitor extends VisitorSupport {
	private List<BaseSite> list = new ArrayList<BaseSite>();
    public void visit(Element element){
    	BaseSite b= new BaseSite();
    	b.setBaseSiteName(element.getStringValue());
    	getList().add(b);
    }
    public void visit(Attribute attr){   
    	getList().get(getList().size()-1).setResourceID(attr.getValue());;
    }
	public List<BaseSite> getList()
	{
		return list;
	}
	public void setList(List<BaseSite> list)
	{
		this.list = list;
	}
 }