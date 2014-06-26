package cn.fuego.dms.ui.frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.fuego.dms.service.ContextService;
import cn.fuego.dms.service.impt.ContextServiceImpl;
import cn.fuego.dms.ui.constant.UIConstant;
import cn.fuego.dms.ui.control.UIController;
import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;


public class MainJFrame extends JFrame
{

	
	private JPanel contentPane;
	
	static Log log = LogFactory.getLog(MainJFrame.class);
	ContextService  contextService = new ContextServiceImpl();
	
	private String selectedBaseSite;	//选中的对象
	private JLabel lblServer;
	private JLabel lblSinal;
	private Map<Integer,MonitorView> monitorMap = new HashMap<Integer,MonitorView>();
	
	private JTree treeBaseSite;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainJFrame frame = new MainJFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainJFrame()
	{
		setTitle("中国移动基站设备监控系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBorder(new TitledBorder(null, "\u57FA\u7AD9\u5217\u8868", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.WEST);
		
		JTree tree = new JTree();
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				JTree a = (JTree) e.getSource();
				DefaultMutableTreeNode note= (DefaultMutableTreeNode) a.getLastSelectedPathComponent();
				String name=note.toString();//获得这个结点的名称
				selectedBaseSite=name;			
			}
		});
		
		tree.setModel(contextService.LoadBaseSiteTree());
		
		tree.setFont(new Font("宋体", Font.PLAIN, 13));
		tree.setForeground(SystemColor.control);
		tree.setBackground(Color.WHITE);
		panel.add(tree);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "\u76D1\u63A7\u6307\u6807", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 0));
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setBackground(Color.WHITE);
		basicInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u57FA\u672C\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(basicInfoPanel);
		basicInfoPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		
		
		
	
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new EmptyBorder(10, 10, 10, 0));
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel electronicInfoPanel = new JPanel();
		electronicInfoPanel.setBackground(Color.WHITE);
		electronicInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5E02\u7535\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(electronicInfoPanel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new EmptyBorder(10, 10, 10, 0));
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel entranceInfoPanel = new JPanel();
		entranceInfoPanel.setBackground(Color.WHITE);
		entranceInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u95E8\u7981\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.add(entranceInfoPanel, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new EmptyBorder(10, 10, 10, 0));
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel sensorPanel = new JPanel();
		sensorPanel.setBackground(Color.WHITE);
		sensorPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6E29\u6E7F\u5EA6\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.add(sensorPanel, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_7.setBorder(new EmptyBorder(10, 10, 10, 0));
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JPanel betteryPanel = new JPanel();
		betteryPanel.setBackground(Color.WHITE);
		betteryPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u84C4\u7535\u6C60\u53C2\u6570", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.add(betteryPanel, BorderLayout.CENTER);
		
		loadLbl(basicInfoPanel, UIConstant.BASIC_INFO_ID);
		loadLbl(entranceInfoPanel, UIConstant.ENTRANCE_ID);
		entranceInfoPanel.setLayout(new GridLayout(0, 4, 0, 0));
		loadLbl(sensorPanel, UIConstant.SENSER_ID);
		sensorPanel.setLayout(new GridLayout(0, 4, 0, 0));
		loadLbl(betteryPanel, UIConstant.BATTERY_ID);
		betteryPanel.setLayout(new GridLayout(0, 4, 0, 0));
		loadLbl(electronicInfoPanel, UIConstant.CITY_ELECTRONIC_ID);
		electronicInfoPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		
		
		
		
		JPanel panel_12 = new JPanel();
		contentPane.add(panel_12, BorderLayout.SOUTH);
		panel_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNewLabel_4 = new JLabel("信号大小：");
		panel_12.add(lblNewLabel_4);
		
		lblSinal = new JLabel("一般");
		panel_12.add(lblSinal);
		
		JLabel lblNewLabel_6 = new JLabel("         运营商：");
		panel_12.add(lblNewLabel_6);
		
		lblServer = new JLabel("中国移动");
		lblServer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServer.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_12.add(lblServer);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblNewLabel);
		UIController uic = new UIController(this);
	}

	private void loadLbl(JPanel panel,int id)
	{
		List<MonitorValueGroup> list= contextService.loadMonitorList(id);

		for(MonitorValueGroup mg:list){
			//Create LBL
			JLabel lbl = new JLabel(mg.getMonitorName());
			lbl.setHorizontalAlignment(JLabel.CENTER);
			JLabel lbl2 = new JLabel(mg.getMonitorValue()+mg.getMonitorUnit());
			lbl.setHorizontalAlignment(JLabel.CENTER);
			panel.add(lbl);
			panel.add(lbl2);
			MonitorView mv = new MonitorView();
			mv.setMonitorName(lbl);
			mv.setMonitorValue(lbl2);
			monitorMap.put(mg.getMonitorID(), mv);
		}
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

	/**
	 * @return the selectedBaseSite
	 */
	public String getSelectedBaseSite()
	{
		return selectedBaseSite;
	}

	/**
	 * @param selectedBaseSite the selectedBaseSite to set
	 */
	public void setSelectedBaseSite(String selectedBaseSite)
	{
		this.selectedBaseSite = selectedBaseSite;
	}

	/**
	 * @return the lblServer
	 */
	public JLabel getLblServer()
	{
		return lblServer;
	}

	/**
	 * @param lblServer the lblServer to set
	 */
	public void setLblServer(JLabel lblServer)
	{
		this.lblServer = lblServer;
	}

	/**
	 * @return the lblSinal
	 */
	public JLabel getLblSinal()
	{
		return lblSinal;
	}

	/**
	 * @param lblSinal the lblSinal to set
	 */
	public void setLblSinal(JLabel lblSinal)
	{
		this.lblSinal = lblSinal;
	}

	/**
	 * @return the monitorMap
	 */
	public Map<Integer, MonitorView> getMonitorMap()
	{
		return monitorMap;
	}

	/**
	 * @param monitorMap the monitorMap to set
	 */
	public void setMonitorMap(Map<Integer, MonitorView> monitorMap)
	{
		this.monitorMap = monitorMap;
	}

	/**
	 * @return the treeBaseSite
	 */
	public JTree getTreeBaseSite()
	{
		return treeBaseSite;
	}

	/**
	 * @param treeBaseSite the treeBaseSite to set
	 */
	public void setTreeBaseSite(JTree treeBaseSite)
	{
		this.treeBaseSite = treeBaseSite;
	}
}
