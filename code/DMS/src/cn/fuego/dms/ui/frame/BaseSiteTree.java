/**   
* @Title: BaseSiteTree.java 
* @Package cn.fuego.dms.ui.frame 
* @Description: TODO
* @author Tang Jun   
* @date 2014-7-7 下午06:43:12 
* @version V1.0   
*/ 
package cn.fuego.dms.ui.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTree;

import cn.fuego.dms.service.ServiceContext;

/** 
 * @ClassName: BaseSiteTree 
 * @Description: TODO
 * @author Tang Jun
 * @date 2014-7-7 下午06:43:12 
 *  
 */

public class BaseSiteTree extends JTree
{
	public BaseSiteTree()
	{
		setModel(ServiceContext.getInstance().getSystemBasicService().LoadBaseSiteTree());
		setSelectionRow(1);
		setFont(new Font("宋体", Font.PLAIN, 13));
		setForeground(SystemColor.control);
		setBackground(Color.WHITE);
	}
	
}
