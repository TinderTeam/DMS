package cn.fuego.dms.ui.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;











import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import cn.fuego.dms.ui.constant.UIConfigConstant;
import cn.fuego.dms.ui.model.MonitorValueGroup;
import cn.fuego.dms.ui.model.MonitorView;

public class MonitoringParametersGroup extends Group
{
	
	private Log log = LogFactory.getLog(MonitoringParametersGroup.class);
	private Composite composite;
	private String groupTitle;
	private FormToolkit formToolkit;
	
	
	
	private Map<Integer,MonitorView> monitorList=new HashMap<Integer,MonitorView>();
	
	public MonitoringParametersGroup(Composite parent,FormToolkit ftk,String groupText,List<MonitorValueGroup> monitorStrList)
	{
		super(parent,  SWT.FILL);
		// init monitorList

		formToolkit=ftk;
		groupTitle=groupText;
		
		
		FormLayout formLayout = new FormLayout();
		formLayout.marginRight = 10;
		formLayout.marginLeft = 10;
		formLayout.marginHeight = 10;
		formLayout.marginBottom = 10;
	
		this.setLayout(formLayout);
		this.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		this.setText(groupText);
		
		formToolkit.adapt(this);
		formToolkit.paintBordersFor(this);
		//load composite
		
		
		composite = new Composite(this, SWT.NONE);
		composite.setLayout(new GridLayout(UIConfigConstant.MONITOR_SHOW_LIST*2, false));
		FormData formData = new FormData();

		formData.left = new FormAttachment(0, 100);
		formData.right = new FormAttachment(0, 750);
		formData.top = new FormAttachment(0, 10);
		composite.setLayoutData(formData);
		formToolkit.adapt(composite);
		formToolkit.paintBordersFor(composite);
		
		monitorList=initMonitorList(monitorStrList);
		
	}

	private Map<Integer,MonitorView> initMonitorList(List<MonitorValueGroup> list)
	{
		if(null==list){
			return null;
		}
		monitorList=new HashMap<Integer,MonitorView>();
		
		for(MonitorValueGroup mg:list){
			//Create LBL
			Label lbl = new Label(composite, SWT.NONE);
			lbl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lbl.setText(mg.getMonitorName());
			formToolkit.adapt(lbl, true, true);
			
			//CREATE TEXT
			Label lbl2 = new Label(composite, SWT.NONE);
			lbl2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));			
			lbl2.setText(mg.getMonitorValue()+"  "+mg.getMonitorUnit());
			formToolkit.adapt(lbl2, true, true);
			MonitorView mv = new MonitorView();
			mv.setMonitorName(lbl);
			mv.setMonitorValue(lbl2);
			
			monitorList.put(mg.getMonitorID(), mv);
			
		}
		
		return monitorList;
	}

	@Override
    protected void checkSubclass() {
        // TODO Auto-generated method stub
    
    }

	/**
	 * @return the monitorList
	 */
	public Map<Integer, MonitorView> getMonitorList()
	{
		return monitorList;
	}

	/**
	 * @param monitorList the monitorList to set
	 */
	public void setMonitorList(Map<Integer, MonitorView> monitorList)
	{
		this.monitorList = monitorList;
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
	 * @return the composite
	 */
	public Composite getComposite()
	{
		return composite;
	}

	/**
	 * @param composite the composite to set
	 */
	public void setComposite(Composite composite)
	{
		this.composite = composite;
	}

	/**
	 * @return the groupTitle
	 */
	public String getGroupTitle()
	{
		return groupTitle;
	}

	/**
	 * @param groupTitle the groupTitle to set
	 */
	public void setGroupTitle(String groupTitle)
	{
		this.groupTitle = groupTitle;
	}

	/**
	 * @return the formToolkit
	 */
	public FormToolkit getFormToolkit()
	{
		return formToolkit;
	}

	/**
	 * @param formToolkit the formToolkit to set
	 */
	public void setFormToolkit(FormToolkit formToolkit)
	{
		this.formToolkit = formToolkit;
	}
	
}
