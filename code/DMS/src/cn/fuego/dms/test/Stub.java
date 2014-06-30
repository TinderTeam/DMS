package cn.fuego.dms.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.fuego.dms.dao.impl.BaseSiteDaoImpl;
import cn.fuego.dms.dao.impl.IndicatorInfoDaoImpl;
import cn.fuego.dms.domain.po.BaseSite;
import cn.fuego.dms.domain.po.IndicatorInfo;
import cn.fuego.dms.service.model.Collection;
import cn.fuego.dms.service.model.Indicator;
import cn.fuego.dms.service.model.Resource;

public class Stub
{

	public static Collection getRadomCollection()
	{
		Collection c=new Collection();
		List<Resource> rl= new ArrayList<Resource>();
		List<IndicatorInfo> iif=new IndicatorInfoDaoImpl().getAll();
		for(BaseSite b: new BaseSiteDaoImpl().getAll()){
			Resource r= new Resource();
			r.setResID(b.getResourceID());
			r.setResName(b.getBaseSiteName());
			List<Indicator> il=new ArrayList<Indicator>();
			
			for(IndicatorInfo i:iif){
				Indicator in=new Indicator();
				in.setIndicatorID(i.getIndicateID());
				in.setName(i.getIndicateName());
				in.setUnit(i.getUnit());
				if(i.getIndicateID()==1){
					in.setValue(r.getResName());
				}else if(i.getIndicateID()==2){
					in.setValue(r.getResID());
				}else if(i.getIndicateID()==6){
					in.setValue(new Random().nextBoolean()?"关":"开");
				}else if(i.getIndicateID()==7){
					in.setValue(new Random().nextBoolean()?"烟雾报警":"正常");
				}else if(i.getIndicateID()==10||i.getIndicateID()==11){
					in.setValue(String.valueOf(new Random().nextInt(100)));
				}else{
					in.setValue(String.valueOf(new Random().nextInt(300)));
				}
				il.add(in);
			}
			
			r.setIndicatorList(il);
			
			rl.add(r);			
		}
		
		c.setResourceList(rl);
		return c;
	}
}
