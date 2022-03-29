package maven1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class all {
	//room,name
	public static Map<String, String> room = new HashMap<String, String>();
	
	//date, (room,name)
	public static Map<String, List<String>> stored = new HashMap();
	
	
	public static void allRoom() {
		for(int i=1;i<6;i++) {
			for(int j=1;j<6;j++) {
				StringBuffer ro = new StringBuffer() ;
				ro.append(i).append("0").append(j);
				room.put(ro.toString(), null);
			}
			
		}
	}
	
	//预定
	//日期默认为xxxx-mm-dd的String
	public synchronized static String Store(String name, String room, List<String> date) {
		for(int i=0;i<date.size();i++) {
			if(stored.containsKey(date.get(i))){
				for(String r : stored.get(date.get(i))) {
					String[] s = r.split(",");
					if(s[0].equals(room)) {
						System.out.println("房间已被预定");
						return "房间已被预定";
					}
				}
				stored.get(date.get(i)).add(room+","+name);
			}else {
				List<String> s1 = new ArrayList<String>();
				s1.add(room+","+name);
				stored.put(date.get(i), s1);
			}
			
		}
		System.out.println("预定成功");
		return "预定成功";
	}
	
	//按日期查找
	public static Map<String, String> Find(List<String> date) {
		Map<String, String> sr = room;
		for(int i=0;i<date.size();i++) {
			if(stored.containsKey(date.get(i))){
				for(String r : stored.get(date.get(i))) {
					String[] s = r.split(",");
					if(sr.containsKey(s[0])) {
						sr.remove(s[0]);
					}
					
				}
			}
		}
		return sr;
		
	}
	
	//按人查找
	public static Map<String, String> Findguest(String name) {
		Map<String, String> result = new HashMap<String, String>();
		for(String date : stored.keySet()) {
			for(String rn : stored.get(date)) {
				String[] s = rn.split(",");
				if(s[1].equals(name)) {
					result.put(date, s[0]);
				}
			}
		}
		if(result.isEmpty()) {
			System.out.println("未查询到该客户");
			return result;
		}else {
			return result;
		}
		
	}
	
	public static void main(String[] args) {
		//初始化房间
		allRoom();
		System.out.println(room);
		
		//预定
		List<String> list = new ArrayList();
		list.add("2020-01-01");
		list.add("2020-01-02");
		list.add("2020-01-03");
		list.add("2020-01-04");
		list.add("2020-01-05");
		list.add("2020-01-06");
		List<String> list3 = new ArrayList();
		list3.add("2020-02-06");
		list3.add("2020-02-07");
		list3.add("2020-02-08");
		list3.add("2020-02-09");
		list3.add("2020-02-10");
		
		Store("a","101", list);
		Store("b","102", list);
		Store("m","103", list3);
		Store("a","105", list3);
		System.out.println(stored);
		
		//重复预定
//		List<String> list2 = new ArrayList();
//		list2.add("2020-01-05");
//		list2.add("2020-01-06");
//		
//		Store("a","101", list);
//		Store("b","101", list);
//		System.out.println(stored);
		
//		//按日期查找
//		List<String> date = new ArrayList();
//		date.add("2020-01-05");
//		date.add("2020-01-08");
//		System.out.println(Find(date));
//		
		//按人查找
		System.out.println(Findguest("a"));
//		
//		//查无此人
//		System.out.println(Findguest("c"));
	}
	
}
