package cn.fuego.dms.ui.frame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import swing2swt.layout.BorderLayout;
import swing2swt.layout.FlowLayout;
import cn.fuego.dms.service.ContextService;
import cn.fuego.dms.service.impt.ContextServiceImpl;
import cn.fuego.dms.test.Stub;
import cn.fuego.dms.ui.UIContext;
import cn.fuego.dms.ui.constant.UIConstant;
import cn.fuego.dms.ui.control.UIController;
import cn.fuego.dms.ui.control.menu.MenuAction;
import cn.fuego.dms.ui.group.MonitoringParametersGroup;
import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public class MainFrame extends ApplicationWindow
{
	
	private Log log = LogFactory.getLog(MainFrame.class);

	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

	/*
	 * Component
	 */
	
		/*
		 * fixed  Component
		 */
		private Group groupBaseSite;
		private Group groupEquipment;
		private Label lblSignalIntensity;	//SignalIntensity Label
		private Label lblSignalService;		//SignalService Label
		private MonitoringParametersGroup groupbasicInfo ;					
		private MonitoringParametersGroup groupCityElctronic;
		private MonitoringParametersGroup groupEntrance;
		private MonitoringParametersGroup groupBattery;
		private MonitoringParametersGroup groupSensor;
		/*
		 * dynamic  Component
		 */
		
		private Tree treeBaseSite;
		private Label lblSignaIntensitlValue;	//SignalIntensity Value
		private Label lblServiceValue;			//ServiceValue Value
		private Map<Integer,MonitorView> monitorMap;

		private String BaseID;
		
		/*
		 * Service
		 */
		
		ContextService  contextService = new ContextServiceImpl();
	/**
	 * Create the application window.
	 */
	public MainFrame()
	{
		super(null);
		setShellStyle(SWT.BORDER | SWT.TITLE | SWT.APPLICATION_MODAL);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
		
		//Load Controller
		UIController uic=new UIController(this);
		log.debug("Controller Started");
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent)
	{
		
		Composite container = new Composite(parent, SWT.BORDER);
		container.setLayout(new BorderLayout(0, 0));
		
		//load Block
		initBaseSitTreeBlock(container);
		initEquipmentMonitorBlock(container);
		
		
		return container;
	}

	private void initEquipmentMonitorBlock(Composite container)
	{
		//groupEquipment
				groupEquipment =UIContext.getInstance().getGroupEquipment(container, SWT.BORDER);
				formToolkit.adapt(groupEquipment);
				formToolkit.paintBordersFor(groupEquipment);
				
				Composite composite = new Composite(container, SWT.NONE);
				composite.setEnabled(true);
				composite.setLayoutData(BorderLayout.SOUTH);	
				formToolkit.adapt(composite);
				formToolkit.paintBordersFor(composite);
				composite.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

				// load signalMonitorBlock
				initSignalMonitorBlock(composite);
				
				//load MonitorParaBlock
				initMonitorParaBlock(groupEquipment);
				

	}

	private void initMonitorParaBlock(Group eq)
	{	
		groupbasicInfo = new MonitoringParametersGroup(eq, formToolkit,UIConstant.BASIC_INFO, contextService.loadMonitorList(UIConstant.BASIC_INFO_ID));
		groupCityElctronic = new MonitoringParametersGroup(eq, formToolkit,UIConstant.CITY_ELECTRONIC, contextService.loadMonitorList(UIConstant.CITY_ELECTRONIC_ID));
		groupEntrance =new MonitoringParametersGroup(eq, formToolkit,UIConstant.ENTRANCE, contextService.loadMonitorList(UIConstant.ENTRANCE_ID));
		groupBattery = new MonitoringParametersGroup(eq, formToolkit,UIConstant.BATTERY,contextService.loadMonitorList(UIConstant.BATTERY_ID));
		groupSensor = new MonitoringParametersGroup(eq, formToolkit,UIConstant.SENSER, contextService.loadMonitorList(UIConstant.SENSER_ID));
		//init monitorList
		monitorListInit();
	}

	private void monitorListInit()
	{
		monitorMap = new HashMap<Integer,MonitorView>();
		Map<Integer, Integer> typeMap=contextService.loadIndicatorTypeMap();
		for(int i:typeMap.keySet()){
			switch(typeMap.get(i)){
			case UIConstant.BASIC_INFO_ID:
				monitorMap.put(i, groupbasicInfo.getMonitorList().get(i));
				break;
			case UIConstant.BATTERY_ID:
				monitorMap.put(i, groupBattery.getMonitorList().get(i));
				break;
			case UIConstant.CITY_ELECTRONIC_ID:
				monitorMap.put(i, groupCityElctronic.getMonitorList().get(i));
				break;
			case UIConstant.ENTRANCE_ID:
				monitorMap.put(i, groupEntrance.getMonitorList().get(i));
				break;
			case UIConstant.SENSER_ID:
				monitorMap.put(i, groupSensor.getMonitorList().get(i));	
				break;
			}
		}
	}

	private void initSignalMonitorBlock(Composite composite)
	{
		lblSignalIntensity  = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblSignalIntensity, true, true);
		lblSignalIntensity.setText("信号强度： ");
		
		
		lblSignaIntensitlValue =new Label(composite, SWT.NONE);
		lblSignaIntensitlValue.setText("无信号");		
		formToolkit.adapt(lblSignaIntensitlValue, true, true);
		
		
		lblSignalService = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblSignalService, true, true);
		lblSignalService.setText("    服务商：");
		
		
		lblServiceValue =new Label(composite, SWT.NONE);
		lblServiceValue.setText("无信号");		
		formToolkit.adapt(lblServiceValue, true, true);
	}

	private void initBaseSitTreeBlock(Composite container)
	{
		//BaseSiteTreeGroup
		groupBaseSite = new Group(container, SWT.BORDER);
		groupBaseSite.setText("基站名称");
		groupBaseSite.setLayoutData(BorderLayout.WEST);
		formToolkit.adapt(groupBaseSite);
		formToolkit.paintBordersFor(groupBaseSite);
				
		//BaseSiteTree		
		treeBaseSite = new Tree(groupBaseSite, SWT.NONE);
		treeBaseSite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				System.out.println(e.item.toString());
				TreeItem etim= (TreeItem) e.item;
				setBaseID(etim.getText());
			}
		});
		contextService.LoadBaseSiteTree(treeBaseSite);
		treeBaseSite.setBounds(10, 20, 200, 650);
		formToolkit.paintBordersFor(treeBaseSite);
	}

	/**
	 * Create the actions.
	 */
	private void createActions()
	{
		
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager()
	{
		MenuManager menuManager = new MenuManager("menu");
		menuManager.add(MenuAction.aboutAction);
		menuManager.add(MenuAction.exitAction);
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */


	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager()
	{
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}


	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText(UIConstant.FRAME_NAME);
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(1024, 768);
	}

	/**
	 * @return the log
	 */
	public Log getLog()
	{
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(Log log)
	{
		this.log = log;
	}

	/**
	 * @return the groupBaseSite
	 */
	public Group getGroupBaseSite()
	{
		return groupBaseSite;
	}

	/**
	 * @param groupBaseSite the groupBaseSite to set
	 */
	public void setGroupBaseSite(Group groupBaseSite)
	{
		this.groupBaseSite = groupBaseSite;
	}

	/**
	 * @return the groupEquipment
	 */
	public Group getGroupEquipment()
	{
		return groupEquipment;
	}

	/**
	 * @param groupEquipment the groupEquipment to set
	 */
	public void setGroupEquipment(Group groupEquipment)
	{
		this.groupEquipment = groupEquipment;
	}

	/**
	 * @return the lblSignalIntensity
	 */
	public Label getLblSignalIntensity()
	{
		return lblSignalIntensity;
	}

	/**
	 * @param lblSignalIntensity the lblSignalIntensity to set
	 */
	public void setLblSignalIntensity(Label lblSignalIntensity)
	{
		this.lblSignalIntensity = lblSignalIntensity;
	}

	/**
	 * @return the lblSignalService
	 */
	public Label getLblSignalService()
	{
		return lblSignalService;
	}

	/**
	 * @param lblSignalService the lblSignalService to set
	 */
	public void setLblSignalService(Label lblSignalService)
	{
		this.lblSignalService = lblSignalService;
	}

	/**
	 * @return the groupbasicInfo
	 */
	public Group getGroupbasicInfo()
	{
		return groupbasicInfo;
	}

	
	/**
	 * @return the treeBaseSite
	 */
	public Tree getTreeBaseSite()
	{
		return treeBaseSite;
	}

	/**
	 * @param treeBaseSite the treeBaseSite to set
	 */
	public void setTreeBaseSite(Tree treeBaseSite)
	{
		this.treeBaseSite = treeBaseSite;
	}

	/**
	 * @return the lblSignaIntensitlValue
	 */
	public Label getLblSignaIntensitlValue()
	{
		return lblSignaIntensitlValue;
	}

	/**
	 * @param lblSignaIntensitlValue the lblSignaIntensitlValue to set
	 */
	public void setLblSignaIntensitlValue(Label lblSignaIntensitlValue)
	{
		this.lblSignaIntensitlValue = lblSignaIntensitlValue;
	}

	/**
	 * @return the lblServiceValue
	 */
	public Label getLblServiceValue()
	{
		return lblServiceValue;
	}

	/**
	 * @param lblServiceValue the lblServiceValue to set
	 */
	public void setLblServiceValue(Label lblServiceValue)
	{
		this.lblServiceValue = lblServiceValue;
	}
	/**
	 * @return the formToolkit
	 */
	public FormToolkit getFormToolkit()
	{
		return formToolkit;
	}

	public Map<Integer,MonitorView> getMonitorMap()
	{
		return monitorMap;
	}

	public void setMonitorMap(Map<Integer,MonitorView> monitorMap)
	{
		this.monitorMap = monitorMap;
	}

	/**
	 * @return the groupCityElctronic
	 */
	public MonitoringParametersGroup getGroupCityElctronic()
	{
		return groupCityElctronic;
	}

	/**
	 * @param groupCityElctronic the groupCityElctronic to set
	 */
	public void setGroupCityElctronic(MonitoringParametersGroup groupCityElctronic)
	{
		this.groupCityElctronic = groupCityElctronic;
	}

	/**
	 * @return the groupEntrance
	 */
	public MonitoringParametersGroup getGroupEntrance()
	{
		return groupEntrance;
	}

	/**
	 * @param groupEntrance the groupEntrance to set
	 */
	public void setGroupEntrance(MonitoringParametersGroup groupEntrance)
	{
		this.groupEntrance = groupEntrance;
	}

	/**
	 * @return the groupBattery
	 */
	public MonitoringParametersGroup getGroupBattery()
	{
		return groupBattery;
	}

	/**
	 * @param groupBattery the groupBattery to set
	 */
	public void setGroupBattery(MonitoringParametersGroup groupBattery)
	{
		this.groupBattery = groupBattery;
	}

	/**
	 * @return the groupSensor
	 */
	public MonitoringParametersGroup getGroupSensor()
	{
		return groupSensor;
	}

	/**
	 * @param groupSensor the groupSensor to set
	 */
	public void setGroupSensor(MonitoringParametersGroup groupSensor)
	{
		this.groupSensor = groupSensor;
	}

	/**
	 * @return the contextService
	 */
	public ContextService getContextService()
	{
		return contextService;
	}

	/**
	 * @param contextService the contextService to set
	 */
	public void setContextService(ContextService contextService)
	{
		this.contextService = contextService;
	}

	/**
	 * @param groupbasicInfo the groupbasicInfo to set
	 */
	public void setGroupbasicInfo(MonitoringParametersGroup groupbasicInfo)
	{
		this.groupbasicInfo = groupbasicInfo;
	}

	public void updateData(List<MonitorValueGroup> radomData)
	{
		for(MonitorValueGroup mvg:radomData){
			if(mvg!=null && monitorMap!=null ){
				MonitorView mv=monitorMap.get(mvg.getMonitorID());
				if(null!=mv){
					mv.getMonitorValue().setText(mvg.getMonitorValue());
				}
			}
		
			
		}
		
	}

	public String getBaseID()
	{
		return BaseID;
	}

	public void setBaseID(String baseID)
	{
		BaseID = baseID;
	}


}
