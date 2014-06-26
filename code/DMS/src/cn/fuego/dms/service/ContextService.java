package cn.fuego.dms.service;

import java.util.List;
import java.util.Map;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;



import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public interface ContextService
{

	DefaultTreeModel  LoadBaseSiteTree();

	Map<Integer, Integer> loadIndicatorTypeMap();

	List<MonitorValueGroup> loadMonitorList(int basicInfoId);

}
