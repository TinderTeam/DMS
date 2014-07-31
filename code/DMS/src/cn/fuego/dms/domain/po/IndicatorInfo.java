package cn.fuego.dms.domain.po;

public class IndicatorInfo
{
	private int indicateID;
	private String indicateName;
	private int indicateGroupID;
	private String unit;

	/**
	 * @return the indicateID
	 */
	public int getIndicateID()
	{
		return indicateID;
	}

	/**
	 * @param indicateID
	 *            the indicateID to set
	 */
	public void setIndicateID(int indicateID)
	{
		this.indicateID = indicateID;
	}

	/**
	 * @return the indicateName
	 */
	public String getIndicateName()
	{
		return indicateName;
	}

	/**
	 * @param indicateName
	 *            the indicateName to set
	 */
	public void setIndicateName(String indicateName)
	{
		this.indicateName = indicateName;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Indicate [indicateID=" + indicateID + ", indicateName=" + indicateName + ", indicateGroupID=" + indicateGroupID + "]";
	}

	/**
	 * @return the unit
	 */
	public String getUnit()
	{
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit)
	{
		this.unit = unit;
	}

}
