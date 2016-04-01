package bo.swift;

import beans.Message;
import beans.Trade;

public interface TemplateSelector {
	public String getTemplate(Trade trade, Message message,String name);

}
