package kr.co.raphaell.telegram;

import java.util.HashMap;
import java.util.Map;

import me.shib.java.lib.telegram.bot.service.TelegramBot;
import me.shib.java.lib.telegram.bot.types.ChatId;
import me.shib.java.lib.telegram.bot.types.Update;
import me.shib.java.lib.telegram.bot.types.Chat.ChatType;

public class TelegramBotService {
	// Singleton
	private TelegramBotService() {}
	private static class Singleton { private static final TelegramBotService instance = new TelegramBotService(); }
	public static TelegramBotService  getInstance() { return Singleton.instance; }

	
	private static final String botToken = "421693306:AAF6hezjjOZz8V0uJgPdrzdOh3s2ATbTgv0";
	private TelegramBot telegramBot;
	
	public void setBotToken(String botApiToken) {
		if(botApiToken == null) {
			telegramBot = TelegramBot.getInstance(botToken);
		} else {
			telegramBot = TelegramBot.getInstance(botApiToken);
		}
	}
	
	public Map<Integer, Long> getGroupsMap() throws Exception {
		Map<Integer, Long> map = null;
		Update[] updates = null;
		
		updates = telegramBot.getUpdatesImmediately();
		if(updates != null && updates.length > 0) {
			
			map = new HashMap<Integer, Long>();
			
		    for (Update update : updates) {
		    	if(update.getMessage().getChat().getType().equals(ChatType.Group)) {
		    		map.put(update.getMessage().getChat().getGroup().getTitle().hashCode(), update.getMessage().getChat().getGroup().getId());
		    	}
		    }
		}
		
		return map;
	}

	public Map<Long, String> getGroups() throws Exception {
		Map<Long, String> map = null;
		Update[] updates = null;
		
		updates = telegramBot.getUpdatesImmediately();
		if(updates != null && updates.length > 0) {
			
			map = new HashMap<Long, String>();
			
		    for (Update update : updates) {
		    	if(update.getMessage().getChat().getType().equals(ChatType.Group)) {
		    		map.put(update.getMessage().getChat().getGroup().getId(), update.getMessage().getChat().getGroup().getTitle());
		    	}
		    }
		}
		
		return map;
	}

	public void send(long groupId, String msg) throws Exception {
		telegramBot.sendMessage(new ChatId(groupId), msg);
	}
	
}
