package cn.fuego.dms.domain.po;

public class BaseSite
{
	private String baseSiteName;
	private String resourceID;

	/**
	 * @return the baseSiteName
	 */
	public String getBaseSiteName()
	{
		return baseSiteName;
	}

	/**
	 * @param baseSiteName
	 *            the baseSiteName to set
	 */
	public void setBaseSiteName(String baseSiteName)
	{
		this.baseSiteName = baseSiteName;
	}

	/**
	 * @return the resourceID
	 */
	public String getResourceID()
	{
		return resourceID;
	}

	/**
	 * @param resourceID
	 *            the resourceID to set
	 */
	public void setResourceID(String resourceID)
	{
		this.resourceID = resourceID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "BaseSite [baseSiteName=" + baseSiteName + ", resourceID=" + resourceID + "]";
	}
}
