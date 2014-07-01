package cn.fuego.dms.domain.po;

public class DataFormat
{
	private int seq;
	private int indicatorID;
	private int dataLength;
	public int getSeq()
	{
		return seq;
	}
	public void setSeq(int seq)
	{
		this.seq = seq;
	}
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
	@Override
	public String toString()
	{
		return "DataFormat [seq=" + seq + ", indicatorID=" + indicatorID + ", dataLength=" + dataLength + "]";
	}
 
}
