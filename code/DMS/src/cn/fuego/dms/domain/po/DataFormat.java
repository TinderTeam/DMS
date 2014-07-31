package cn.fuego.dms.domain.po;

public class DataFormat
{ 
	private int indicatorID;
	private int dataLength;
	private String dataType;
	private String formula;

 
	public int getIndicatorID()
	{
		return indicatorID;
	}

	public void setIndicatorID(int indicatorID)
	{
		this.indicatorID = indicatorID;
	}

	public int getDataLength()
	{
		return dataLength;
	}

	public void setDataLength(int dataLength)
	{
		this.dataLength = dataLength;
	}

	public String getFormula()
	{
		return formula;
	}

	public void setFormula(String formula)
	{
		this.formula = formula;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	@Override
	public String toString()
	{
		return "DataFormat [indicatorID=" + indicatorID + ", dataLength=" + dataLength + ", dataType=" + dataType + ", formula=" + formula + "]";
	}

	 

}
