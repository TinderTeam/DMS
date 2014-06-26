package cn.fuego.dms.ui;


import java.awt.BorderLayout;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;

import cn.fuego.dms.test.Stub;
import cn.fuego.dms.ui.constant.UIConstant;
import cn.fuego.dms.ui.frame.MainFrame;
import cn.fuego.dms.ui.group.MonitoringParametersGroup;




public class UIContext
{
	private Log log = LogFactory.getLog(UIContext.class);
	private static UIContext instance;
	
	 
	private MainFrame window;
	private Tree treeBaseSite;
	private Group groupBaseSite;
	private Group groupEquipment;

	
	private Group groupbasicInfo;
	private Group groupCityElctronic;
	private Group groupEntrance;
	private Group groupBattery;
	private Group groupSensor;
	
	private Label lblSignalValue;
	private Label lblServiceValue;

	
	private UIContext()
	{

	}
	
	public static synchronized UIContext getInstance()
	{
		if (null == instance)
		{
			instance = new UIContext();
		}
		return instance;
	}	
	
	//MainFrame
	public synchronized MainFrame getMainFrame() 
	{
		if (null == window)
		{
			window = new MainFrame();
			window.setBlockOnOpen(true);				
			window.open();
			
			
		}
		return window;
	}
	
	
	//groupBaseSite
		public synchronized Group getGroupBaseSite(Composite parent, int style) 
		{
			if (null == groupBaseSite)
			{
				groupBaseSite = new Group(parent, style);
				groupBaseSite.setText("基站名称");
				groupBaseSite.setLayoutData(BorderLayout.WEST);

			}
			return groupBaseSite;
		}
	
		//groupBaseSite
				public synchronized Group getGroupEquipment(Composite parent, int style) 
				{
					if (null == groupEquipment)
					{
						groupEquipment = new Group(parent, style);
						groupEquipment.setLayoutData(BorderLayout.CENTER);
						groupEquipment.setText("设备参数");
						groupEquipment.setLayout(new GridLayout(1, false));
					}
					return groupEquipment;
				}	
	
		//groupbasicInfo
				public synchronized Group getGroupbasicInfo(Composite parent,FormToolkit ftk) 
				{
					if (null == groupbasicInfo)
					{
						groupbasicInfo = new MonitoringParametersGroup(parent, ftk,UIConstant.BASIC_INFO, Stub.getMap());
						
					}
					return groupbasicInfo;
				}	
	

				//groupCityElctronic
				public synchronized Group getGroupCityElctronic(Composite parent,FormToolkit ftk) 
				{
					if (null == groupCityElctronic)
					{
						groupCityElctronic = new MonitoringParametersGroup(parent, ftk,UIConstant.CITY_ELECTRONIC,  Stub.getMap());
						
					}
					return groupCityElctronic;
				}
				
				
				//groupbasicInfo
				public synchronized Group getGroupEntranceo(Composite parent,FormToolkit ftk) 
				{
					if (null == groupEntrance)
					{
						groupEntrance = new MonitoringParametersGroup(parent, ftk,UIConstant.ENTRANCE,  Stub.getMap());
						
					}
					return groupEntrance;
				}
				
				
				//groupbasicInfo
				public synchronized Group getGroupBattery(Composite parent,FormToolkit ftk) 
				{
					if (null == groupBattery)
					{
						groupBattery = new MonitoringParametersGroup(parent, ftk,UIConstant.BATTERY,  Stub.getMap());
						
					}
					return groupBattery;
				}
				
				
				//groupbasicInfo
				public synchronized Group getGroupSensor(Composite parent,FormToolkit ftk) 
				{
					if (null == groupSensor)
					{
						groupSensor = new MonitoringParametersGroup(parent, ftk,UIConstant.SENSER, Stub.getMap());
						
					}
					return groupSensor;
				}
				
				
				
	//TreeViewer
		public synchronized Tree getTreeBaseSite(Composite parent, int style) 
		{
			if (null == treeBaseSite)
			{
				treeBaseSite = new Tree(parent, style);
			
				/*
				 * test Code
				 */
				TreeItem iItem, jItem, kItem;  
				for (int i=0; i<3; i++) {  
		            iItem = new TreeItem (treeBaseSite, 0);  
		            iItem.setText ("基站 (0) -" + i);  
		            for (int j=0; j<3; j++) {  
		                jItem = new TreeItem (iItem, 0);  
		                jItem.setText ("基站 (1) -" + j);  
		                for (int k=0; k<3; k++) {  
		                    kItem = new TreeItem (jItem, 0);  
		                    kItem.setText ("基站 (2) -" + k);  
		                }  
		            }  
		        }
				
				/*
				 * test Code
				 */
				
				treeBaseSite.setBounds(10, 20, 200, 650);
			}
			return treeBaseSite;
		}
		
		
		//lblSignalValue
				public synchronized Label getLblSignalValue(Composite parent, int style, FormToolkit formToolkit) 
				{
					if (null == lblSignalValue)
					{
						lblSignalValue =new Label(parent, SWT.NONE);
						lblSignalValue.setText("无信号");		
						formToolkit.adapt(lblSignalValue, true, true);

					}
					return lblSignalValue;
				}
				
				public synchronized Label getLblSignalValue() 
				{
					if (null == lblSignalValue)
					{
						return null;

					}
					return lblSignalValue;
				}
				//lblSignalValue
				public synchronized Label getLblSServiceValue(Composite parent, int style, FormToolkit formToolkit) 
				{
					if (null == lblServiceValue)
					{
						lblServiceValue =new Label(parent, SWT.NONE);
						lblServiceValue.setText("无信号");		
						formToolkit.adapt(lblServiceValue, true, true);

					}
					return lblSignalValue;
				}	

}
