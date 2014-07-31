package cn.fuego.dms.domain.po;

public class IndicatorGroup
{
	private int indicateGroupID;
	private String indicateGroupName;

	/**
	 * @return the indicateGroupID
	 */
	public int getIndicateGroupID()
	{
		return indicateGroupID;
	}

	/**
	 * @param indicateGroupID
	 *            the indicateGroupID to set
	 */
	public void setIndicateGroupID(int indicateGroupID)
	{
		this.indicateGroupID = indicateGroupID;
	}

	/**
	 * @return the indicateGroupName
	 */
	public String getIndicateGroupName()
	{
		return indicateGroupName;
	}

	/**
	 * @param indicateGroupName
	 *            the indicateGroupName to set
	 */
	public void setIndicateGroupName(String indicateGroupName)
	{
		this.indicateGroupName = indicateGroupName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "IndicateGroup [indicateGroupID=" + indicateGroupID + ", indicateGroupName=" + indicateGroupName + "]";
	}
}
