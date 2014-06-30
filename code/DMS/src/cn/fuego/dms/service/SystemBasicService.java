package cn.fuego.dms.service;

import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultTreeModel;

import cn.fuego.dms.ui.model.MonitorValueGroup;

public interface SystemBasicService
{

	DefaultTreeModel  LoadBaseSiteTree();

	Map<Integer, Integer> loadIndicatorTypeMap();

	List<MonitorValueGroup> loadMonitorList(int basicInfoId);

}