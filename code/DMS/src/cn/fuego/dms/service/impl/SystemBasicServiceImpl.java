package cn.fuego.dms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import cn.fuego.dms.dao.BaseSiteDao;
import cn.fuego.dms.dao.IndicatorInfoDao;
import cn.fuego.dms.dao.impl.BaseSiteDaoImpl;
import cn.fuego.dms.dao.impl.IndicatorInfoDaoImpl;
import cn.fuego.dms.domain.po.BaseSite;
import cn.fuego.dms.domain.po.IndicatorInfo;
import cn.fuego.dms.service.SystemBasicService;
import cn.fuego.dms.test.Stub;
import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public class SystemBasicServiceImpl implements SystemBasicService
{
	IndicatorInfoDao indicateDao = new IndicatorInfoDaoImpl();
	BaseSiteDao baseSiteDao = new BaseSiteDaoImpl();
	public DefaultTreeModel LoadBaseSiteTree()
	{
		baseSiteDao.getAll();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("基站列表");
		for( BaseSite b:baseSiteDao.getAll()){
			DefaultMutableTreeNode node=new DefaultMutableTreeNode(	String.valueOf(b.getResourceID()));
			root.add(node);
		}
		DefaultTreeModel treeModel=new DefaultTreeModel(root);
		return treeModel;
			
	}


	public List<MonitorValueGroup> loadMonitorList(int id)
	{
		List<IndicatorInfo> ilist= indicateDao.getAll();
		List<MonitorValueGroup>  list = new  ArrayList<MonitorValueGroup>();

		for(IndicatorInfo i:indicateDao.getAll()){
			if(i.getIndicateGroupID()==id){
				MonitorValueGroup mvg = new MonitorValueGroup();
				mvg.setMonitorID(i.getIndicateID());
				mvg.setMonitorName(i.getIndicateName());
				mvg.setMonitorUnit(i.getUnit());
				mvg.setMonitorValue("");
				list.add(mvg);
			}
		}
		

		return list;
	}




}
