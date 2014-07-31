package cn.fuego.dms.ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.domain.po.IndicatorGroup;
import cn.fuego.dms.service.DataCollectorService;
import cn.fuego.dms.service.ServiceContext;
import cn.fuego.dms.service.SystemBasicService;
import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.service.model.Indicator;
import cn.fuego.dms.service.model.Resource;
import cn.fuego.dms.ui.constant.UIConstant;
import cn.fuego.dms.ui.control.MenuActionListener;
import cn.fuego.dms.ui.control.UIController;
import cn.fuego.dms.ui.model.BaseSiteTreeItem;
import cn.fuego.dms.ui.model.IndicatorViewComponent;
import cn.fuego.dms.ui.model.MonitorValue;
import cn.fuego.dms.util.SystemConfigInfo;

/**
 * 
 * @ClassName: MainJFrame
 * @Description: TODO
 * @author Nan Bowen
 * @date 2014-7-7 上午09:29:59
 * 
 */
public class MainJFrame extends JFrame
{
	// Log
	private static Log log = LogFactory.getLog(MainJFrame.class);
	// Service
	private SystemBasicService contextService = ServiceContext.getInstance().getSystemBasicService();
	private DataCollectorService collectorService = ServiceContext.getInstance().getCollectorService();
	// Para
	private BaseSiteTreeItem selectedBaseSite; // Selected basesite
	private Map<Integer, IndicatorViewComponent> monitorMap = new HashMap<Integer, IndicatorViewComponent>(); // monitorMap to find the compoment

	// Constant
	String iconPath = "/resource/icon.png";
	// Content
	private JPanel contentPane; // Basic contentPanel

	// Component
	private JLabel lblServer;
	private JLabel lblSignal;
	private BaseSiteTree baseSiteTree;
	private UIController uic;

	private LoadWindow loadWindow;

	/**
	 * Create the frame.
	 */
	public MainJFrame()
	{
		loadWindow = new LoadWindow();

		// basic init

		initFont();
		loadWindow.nextStep();
		initIcon();
		loadWindow.nextStep();
		initMainFramePara();
		loadWindow.nextStep();

		// content init

		// menu
		initMenu();
		loadWindow.nextStep();
		// layout
		// WestPanel
		initWestPanel();
		loadWindow.nextStep();
		initCenterPanel();
		loadWindow.nextStep();
		initSouthPanel();
		loadWindow.nextStep();

		// listener
		addListener();
		loadWindow.nextStep();

		// start Controller
		this.updateData();
		startController();
		loadWindow.nextStep();

	}

	public void startController()
	{
		uic = new UIController(this);
		try
		{
			uic.start();
			updateSignal(collectorService.getSignalInfo(), collectorService.getServerName());
		}
		catch (RuntimeException ex)
		{
			updateSignal(0, UIConstant.NO_SERVER);
			WarningDialog dailog = new WarningDialog(ex.getMessage());
			dailog.setVisible(true);
		}
	}

	public void updateSignal(int i, String server)
	{

		if (i == 0 || i == 99)
		{
			lblSignal.setText(UIConstant.NO_SIGNAL);
		}
		else if (i > 0 && i < 8)
		{
			lblSignal.setText(UIConstant.LOW_SIGNAL);
		}
		else if (i > 10 && i < 21)
		{
			lblSignal.setText(UIConstant.MID_SIGNAL);
		}
		else if (i > 20 && i <= 31)
		{
			lblSignal.setText(UIConstant.HIGH_SIGNAL);
		}
		lblServer.setText(server);

	}

	private void addListener()
	{
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{

				try
				{
					uic.stopThread();
				}
				catch (Exception e)
				{
					log.error("stop collector", e);
				}
				log.info("system exist now");
				System.exit(0);
			}
		});

	}

	private void initSouthPanel()
	{
		JPanel panel_12 = new JPanel();
		contentPane.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lblNewLabel_4 = new JLabel("信号大小：");
		panel_12.add(lblNewLabel_4);

		lblSignal = new JLabel("一般");
		panel_12.add(lblSignal);

		JLabel lblNewLabel_6 = new JLabel("         运营商：");
		panel_12.add(lblNewLabel_6);

		lblServer = new JLabel("中国移动");
		lblServer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServer.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_12.add(lblServer);

	}

	private void initCenterPanel()
	{
		// panel
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, UIConstant.MONITOR_VALUE, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));

		List<IndicatorGroup> groupList = contextService.loadMonitorGroupList();

		for (IndicatorGroup ig : groupList)
		{
			JPanel panel_box = new JPanel();
			panel_box.setBackground(Color.WHITE);
			panel_box.setBorder(new EmptyBorder(10, 10, 10, 0));
			panel_1.add(panel_box);
			panel_box.setLayout(new BorderLayout(0, 0));
			JPanel basicInfoPanel = new JPanel();
			basicInfoPanel.setBackground(Color.WHITE);
			basicInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), ig.getIndicateGroupName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_box.add(basicInfoPanel);
			basicInfoPanel.setLayout(new GridLayout(0, 4, 0, 0));
			loadLbl(basicInfoPanel, ig.getIndicateGroupID());
		}
	}

	private void initWestPanel()
	{
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		panel.setBorder(new TitledBorder(null, UIConstant.BASESIT_INFO, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.WEST);

		baseSiteTree = new BaseSiteTree();
		baseSiteTree.addTreeSelectionListener(new TreeSelectionListener()
		{
			public void valueChanged(TreeSelectionEvent e)
			{
				JTree a = (JTree) e.getSource();
				try
				{
					BaseSiteTreeItem note = (BaseSiteTreeItem) a.getLastSelectedPathComponent();
					selectedBaseSite = note;
				 
					updateData();
				}
				catch (RuntimeException ex)
				{
					log.error("error", ex);
				}
			}
		});
		
		panel.add(baseSiteTree);
 
	}

	/*
	 * init menu
	 */
	private void initMenu()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("系统");
		menuBar.add(mnNewMenu);
		MenuActionListener mls = new MenuActionListener(this);

		JMenuItem mntmRefresh = new JMenuItem("刷新");
		mntmRefresh.addActionListener(mls);

		mntmRefresh.setIcon(new ImageIcon(UIConstant.REFRESH_PATH));
		mnNewMenu.add(mntmRefresh);

		JMenuItem mntmAbout = new JMenuItem("关于DMS...");
		mntmAbout.addActionListener(mls);

		JMenuItem mntmConnect = new JMenuItem("连接服务器");
		mnNewMenu.add(mntmConnect);
		mntmConnect.addActionListener(mls);

		mntmAbout.setIcon(new ImageIcon(UIConstant.ABOUTUS_PATH));
		mnNewMenu.add(mntmAbout);

		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);

		JMenuItem mntmExit = new JMenuItem("退出");
		mntmExit.addActionListener(mls);
		mntmExit.setIcon(new ImageIcon(UIConstant.EXIT_PATH));
		mnNewMenu.add(mntmExit);
	}

	/*
	 * init mainframe para
	 */
	private void initMainFramePara()
	{
		setTitle(SystemConfigInfo.getProductName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		this.setLocation((int) (UIConstant.SCR_WIDTH - 1024) / 2, (int) (UIConstant.SCR_HEIGHT - 768) / 2);
	}

	/*
	 * Init JFrame Icon
	 */
	private void initIcon()
	{
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(UIConstant.ICON_PATH));
	}

	private void initFont()
	{
		try
		{
			Font commonFont = new Font("宋体", Font.PLAIN, 12);
			Font titleFont = new Font("宋体", Font.BOLD, 13);
			UIManager.getDefaults().put("TextField.inactiveForeground", Color.darkGray);
			UIManager.getDefaults().put("Button.font", commonFont);
			UIManager.getDefaults().put("ComboBox.font", commonFont);
			UIManager.getDefaults().put("CheckBox.font", commonFont);
			UIManager.getDefaults().put("Label.font", commonFont);
			UIManager.getDefaults().put("Menu.font", commonFont);
			UIManager.getDefaults().put("MenuBar.font", commonFont);
			UIManager.getDefaults().put("MenuItem.font", commonFont);
			UIManager.getDefaults().put("RadioButtonMenuItem.font", commonFont);
			UIManager.getDefaults().put("TabbedPane.font", commonFont);
			UIManager.getDefaults().put("ToggleButton.font", commonFont);
			UIManager.getDefaults().put("TitledBorder.font", titleFont);
			UIManager.getDefaults().put("List.font", commonFont);
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void loadLbl(JPanel panel, int id)
	{
		List<MonitorValue> list = contextService.loadMonitorList(id);

		for (MonitorValue mg : list)
		{
			// Create LBL
			JLabel lbl = new JLabel(mg.getMonitorName() + mg.getMonitorUnit());
			lbl.setHorizontalAlignment(JLabel.CENTER);
			JLabel lbl2 = new JLabel(mg.getMonitorValue());
			lbl.setHorizontalAlignment(JLabel.CENTER);
			panel.add(lbl);
			panel.add(lbl2);
			IndicatorViewComponent mv = new IndicatorViewComponent();
			mv.setMonitorName(lbl);
			mv.setMonitorValue(lbl2);
			monitorMap.put(mg.getMonitorID(), mv);
		}
	}

	public void updateData()
	{
		Resource resource;
		Collection collection = collectorService.getCurCollection();
		if (null != collection)
		{
			for (Integer indiID : monitorMap.keySet())
			{
				monitorMap.get(indiID).getMonitorValue().setText("");
			}

			resource = collection.getResourceByResID(this.selectedBaseSite.getId());

			if (resource != null && resource.getIndicatorList() != null)
			{
				for (Indicator i : resource.getIndicatorList())
				{
					IndicatorViewComponent mv = monitorMap.get(i.getIndicatorID());
					if (null != mv)
					{
						mv.getMonitorValue().setText(i.getValue());
					}
					
					
				}
			}
			else
			{
				log.warn("can not get the data of the resource. the resource id is :" + this.selectedBaseSite);
			}

		}
		else
		{
			log.warn("the latest collection is empty");
		}
		IndicatorViewComponent mvSiteName = monitorMap.get(1); 
		mvSiteName.getMonitorValue().setText((String)this.selectedBaseSite.getUserObject());
		IndicatorViewComponent mvSiteID = monitorMap.get(2);
		mvSiteID.getMonitorValue().setText(this.selectedBaseSite.getId());

	}

}
