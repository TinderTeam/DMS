package cn.fuego.dms.service.impt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import cn.fuego.dms.service.ContextService;
import cn.fuego.dms.test.Stub;

import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public class ContextServiceImpl implements ContextService
{

	public void LoadBaseSiteTree(Tree treeBaseSite)
	{
		TreeItem iItem, jItem, kItem;  
		for (int i=0; i<3; i++) {  
            iItem = new TreeItem (treeBaseSite, 0);  
            
            iItem.setText ("基站 A" + i);  
            for (int j=0; j<3; j++) {  
                jItem = new TreeItem (iItem, 0);  
                jItem.setText ("基站 B -" + j);  
                for (int k=0; k<3; k++) {  
                    kItem = new TreeItem (jItem, 0);  
                    kItem.setText ("基站 C -" + k);  
                }  
            }  
        }
		
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
