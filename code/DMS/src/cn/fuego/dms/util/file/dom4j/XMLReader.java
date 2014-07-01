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

	public XMLReader(String Path, Visitor vst) throws DocumentException
	{
		if (vst == null)
		{

		}
		visitor = vst;
		SAXReader reader = new SAXReader();

		Document document = reader.read(new File(Path));
		Element root = document.getRootElement();

		for (Iterator<?> i = root.elementIterator(); i.hasNext();)
		{
			Element element = (Element) i.next();
			element.accept(visitor);
		}

	}
}
