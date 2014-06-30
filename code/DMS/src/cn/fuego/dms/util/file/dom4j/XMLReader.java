package cn.fuego.dms.util.file.dom4j;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Visitor;
import org.dom4j.io.SAXReader;

import cn.fuego.dms.dao.constant.XMLPathConstant;

public class XMLReader
{
	
	Visitor visitor;
	public XMLReader(String Path,Visitor vst) throws Exception{
		if(vst==null){
			throw new Exception("visitor is null");
		}
		visitor=vst;
		SAXReader reader = new SAXReader();
	    try
		{
			Document document = reader.read(new File(Path));
			Element root=document.getRootElement();
			
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			     Element element = (Element) i.next();
			     element.accept(visitor);
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		}
	     
	}
}
