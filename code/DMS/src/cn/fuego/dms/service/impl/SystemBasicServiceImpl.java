package cn.fuego.dms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import cn.fuego.dms.dao.BaseSiteDao;
import cn.fuego.dms.dao.DaoContext;
import cn.fuego.dms.dao.IndicatorGroupDao;
import cn.fuego.dms.dao.IndicatorInfoDao;
import cn.fuego.dms.domain.po.BaseSite;
import cn.fuego.dms.domain.po.IndicatorGroup;
import cn.fuego.dms.domain.po.IndicatorInfo;
import cn.fuego.dms.service.SystemBasicService;
import cn.fuego.dms.ui.model.BaseSiteTreeItem;
import cn.fuego.dms.ui.model.MonitorValueGroup;

public class SystemBasicServiceImpl implements SystemBasicService
{
	IndicatorInfoDao indicatorDao = DaoContext.getInstance().getIndicatorInfoDao();
	BaseSiteDao baseSiteDao = DaoContext.getInstance().getBaseSiteDao();
	IndicatorGroupDao indicatorGroupDao= DaoContext.getInstance().getIndicatorGroupDao();
	
	public DefaultTreeModel LoadBaseSiteTree()
	{
		baseSiteDao.getAll();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("基站列表");
		for (BaseSite b : baseSiteDao.getAll())
		{
			DefaultMutableTreeNode node = new BaseSiteTreeItem(b);
			root.add(node);
		}
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		return treeModel;

	}

	public List<MonitorValueGroup> loadMonitorList(int id)
	{
		List<IndicatorInfo> indicatorList = indicatorDao.getAll();
		List<MonitorValueGroup> list = new ArrayList<MonitorValueGroup>();

		for (IndicatorInfo i : indicatorList)
		{
			if (i.getIndicateGroupID() == id)
			{
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

	@Override
	public List<IndicatorGroup> loadMonitorGroupList()
	{
		
		return indicatorGroupDao.getAll();
	}
}
