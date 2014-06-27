package cn.fuego.dms.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import cn.fuego.dms.ui.constant.UIConstant;
import cn.fuego.dms.ui.model.MonitorValueGroup;

public class Stub
{
	public static List<MonitorValueGroup> getMap(){
		
		 List<MonitorValueGroup>  list = new  ArrayList<MonitorValueGroup>();

		 MonitorValueGroup a = new MonitorValueGroup();
		 a.setMonitorID(1);
		 a.setMonitorName("基站名称");
		 a.setMonitorValue("某某某");
		 a.setMonitorUnit("基站");
		 list.add(a);
		 
		 MonitorValueGroup b = new MonitorValueGroup();
		 b.setMonitorID(2);
		 b.setMonitorName("基站设备编号");
		 b.setMonitorValue("ASC-74512");
		 b.setMonitorUnit("");
		 list.add( b);
		 
		 MonitorValueGroup c = new MonitorValueGroup();
		 c.setMonitorID(3);
		 c.setMonitorName("电网电压");
		 c.setMonitorValue("220");
		 c.setMonitorUnit("V");
		 list.add( c);
		 
		 MonitorValueGroup d = new MonitorValueGroup();
		 d.setMonitorID(4);
		 d.setMonitorName("电网功率");
		 d.setMonitorValue("330");
		 d.setMonitorUnit("KW");
		 list.add( d);
		 
		 MonitorValueGroup e = new MonitorValueGroup();
		 e.setMonitorID(5);
		 e.setMonitorName("电网电流");
		 e.setMonitorValue("33.87");
		 e.setMonitorUnit("A");
		 list.add( e);
		 
		 
		 return list;	
		
	}
	
	
	public static Map<Integer,Integer> getIndicatorType(){
		
		Map<Integer,Integer>  map = new  HashMap<Integer,Integer>();
		map.put(1,UIConstant.BASIC_INFO_ID);
		map.put(2,UIConstant.BASIC_INFO_ID);
		map.put(3,UIConstant.CITY_ELECTRONIC_ID);
		map.put(4,UIConstant.CITY_ELECTRONIC_ID);
		map.put(5,UIConstant.CITY_ELECTRONIC_ID);
		map.put(6,UIConstant.BATTERY_ID);
		map.put(7,UIConstant.ENTRANCE_ID);
		map.put(8,UIConstant.SENSER_ID);
		
		return map;	
		
	}
	public static List<MonitorValueGroup> getRadomData(String str){
		
		 List<MonitorValueGroup>  list = new  ArrayList<MonitorValueGroup>();
		 for(int i=0;i<10;i++){
			 MonitorValueGroup a = new MonitorValueGroup();
			 if(str==null){
				 a.setMonitorID(i);
				 a.setMonitorName("基站指标名称"+i);
				 a.setMonitorValue("无基站");
				 a.setMonitorUnit("单位"); 
			 }else{
				 a.setMonitorID(i);
				 a.setMonitorName("基站指标名称"+i);
				 a.setMonitorValue(str+String.valueOf((new Random().nextInt())));
				 a.setMonitorUnit("单位"); 
			 }
			 
			 list.add(a);
		 }
		
		 
		 return list;	
		
	}
}
