package cn.fuego.dms.service;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Tree;

import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public interface ContextService
{

	void LoadBaseSiteTree(Tree treeBaseSite);

	Map<Integer, Integer> loadIndicatorTypeMap();

	List<MonitorValueGroup> loadMonitorList(int basicInfoId);

}
