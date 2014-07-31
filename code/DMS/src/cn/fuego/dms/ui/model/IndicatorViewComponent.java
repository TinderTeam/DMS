package cn.fuego.dms.ui.model;

import javax.swing.JLabel;

public class IndicatorViewComponent
{
	private JLabel monitorName;
	private JLabel monitorValue;

	/**
	 * @return the monitorName
	 */
	public JLabel getMonitorName()
	{
		return monitorName;
	}

	/**
	 * @param monitorName
	 *            the monitorName to set
	 */
	public void setMonitorName(JLabel monitorName)
	{
		this.monitorName = monitorName;
	}

	/**
	 * @return the monitorValue
	 */
	public JLabel getMonitorValue()
	{
		return monitorValue;
	}

	/**
	 * @param monitorValue
	 *            the monitorValue to set
	 */
	public void setMonitorValue(JLabel monitorValue)
	{
		this.monitorValue = monitorValue;
	}

}
