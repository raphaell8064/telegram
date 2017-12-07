package kr.co.raphaell.telegram;

import java.util.HashMap;
import java.util.Map;


public class TelegramGroup {
	private static Map<Integer, Long> groupIdMap = null;

	public static void init() {
		if(groupIdMap == null) {
			groupIdMap = new HashMap<Integer, Long>();
		}
		
		loadGroup();
	}
	
	public static void loadGroup() {
		try {







			groupIdMap = TelegramBotService.getInstance().getGroupsMap(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long getGroupId(String title) {
		return groupIdMap.get(title.hashCode());
	}
}
