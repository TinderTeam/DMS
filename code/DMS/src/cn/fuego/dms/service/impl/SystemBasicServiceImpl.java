package cn.fuego.dms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import cn.fuego.dms.service.SystemBasicService;
import cn.fuego.dms.test.Stub;

import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public class SystemBasicServiceImpl implements SystemBasicService
{

	public DefaultTreeModel LoadBaseSiteTree()
	{
		DefaultMutableTreeNode a=	new DefaultMutableTreeNode("地方官");
		DefaultTreeModel treeModel=new DefaultTreeModel(a);
		
	
		DefaultMutableTreeNode b=	new DefaultMutableTreeNode("地的双方方官");
		
		DefaultMutableTreeNode main =new DefaultMutableTreeNode("root");
		a.add(b);
		a.add(main);
		
		return treeModel;
			
	}

	@Override
	public  Map<Integer,Integer> loadIndicatorTypeMap()
	{
		return Stub.getIndicatorType();
		
	}
	public List<MonitorValueGroup> loadMonitorList(int id)
	{
		List<MonitorValueGroup>  list  =Stub.getMap();
		List<MonitorValueGroup> typeList = new ArrayList<MonitorValueGroup>();
		Map<Integer,Integer> map=loadIndicatorTypeMap();
		
		for(MonitorValueGroup mvg:list){
			if(map.get(mvg.getMonitorID())==id){
				typeList.add(mvg);
			}
		}
		return typeList;
	}




}
