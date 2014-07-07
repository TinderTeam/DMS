package cn.fuego.dms.service;

import java.util.List;

import javax.swing.tree.DefaultTreeModel;

import cn.fuego.dms.ui.model.MonitorValueGroup;

public interface SystemBasicService
{

	DefaultTreeModel  LoadBaseSiteTree();

	List<MonitorValueGroup> loadMonitorList(int basicInfoId);

}
