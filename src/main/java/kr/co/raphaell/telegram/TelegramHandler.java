package kr.co.raphaell.telegram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.shib.java.lib.telegram.bot.service.TelegramBot;
import me.shib.java.lib.telegram.bot.types.ChatId;
import me.shib.java.lib.telegram.bot.types.Update;
import me.shib.java.lib.telegram.bot.types.Chat.ChatType;


public class TelegramHandler extends HttpServlet {
	
	private String method = "list";
	
	public TelegramHandler(String method) {
		this.method = method;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        String title = null;
        String msg = null;
        StringBuffer sb = null;
        TelegramBotService telegramBotService = null;
        Map<Long, String> groupsMap = null;

        telegramBotService = TelegramBotService.getInstance();
        
        try {
            if("list".equals(this.method)) {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);

            	sb = new StringBuffer();
            	
            	groupsMap = telegramBotService.getGroups();
            	
            	Set<Long> keySet = null;
            	if(groupsMap != null) {
            		keySet = groupsMap.keySet();
            		for(long key : keySet) {
    		    		sb.setLength(0);
    		    		sb.append("<h1>");
    		    		sb.append(groupsMap.get(key)).append(":").append(key);
    		    		sb.append("</h1>");
    		    		response.getWriter().println(sb.toString());	
            		}
            	}
            } else if("send".equals(this.method)) {
            	title = request.getParameter("title");
            	msg = request.getParameter("msg");
            	
            	telegramBotService.send(TelegramGroup.getGroupId(title), msg);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}
	
}
