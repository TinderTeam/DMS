package cn.fuego.dms.ui.model;

public class MonitorValue
{
	private int	monitorID;
	private String monitorName;
	private String monitorValue;
	private String monitorUnit;
	/**
	 * @return the monitorName
	 */
	public String getMonitorName()
	{
		return monitorName;
	}
	/**
	 * @param monitorName the monitorName to set
	 */
	public void setMonitorName(String monitorName)
	{
		this.monitorName = monitorName;
	}
	/**
	 * @return the monitorValue
	 */
	public String getMonitorValue()
	{
		return monitorValue;
	}
	/**
	 * @param monitorValue the monitorValue to set
	 */
	public void setMonitorValue(String monitorValue)
	{
		this.monitorValue = monitorValue;
	}
	/**
	 * @return the monitorUnit
	 */
	public String getMonitorUnit()
	{
		return monitorUnit;
	}
	/**
	 * @param monitorUnit the monitorUnit to set
	 */
	public void setMonitorUnit(String monitorUnit)
	{
		this.monitorUnit = monitorUnit;
	}
	public int getMonitorID()
	{
		return monitorID;
	}
	public void setMonitorID(int monitorID)
	{
		this.monitorID = monitorID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MonitorValueGroup [monitorID=" + monitorID + ", monitorName="
				+ monitorName + ", monitorValue=" + monitorValue
				+ ", monitorUnit=" + monitorUnit + "]";
	}
}
