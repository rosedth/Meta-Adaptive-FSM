package org.rossedth.meta_adaptive_fsm;

import org.rossedth.adaptive_logic.Action;

public class ALAction implements Action{
	private String type;
	private String element;
	private String eventType;
	private String eventHandler;
	
	
	
	public ALAction() {
	}


	public ALAction(String type, String element, String eventType) {
		super();
		this.type = type;
		this.element = element;
		this.eventType = eventType;
	}
	
	
	public ALAction(String type, String element, String eventType, String eventHandler) {
		super();
		this.type = type;
		this.element = element;
		this.eventType = eventType;
		this.eventHandler = eventHandler;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventHandler() {
		return eventHandler;
	}
	public void setEventHandler(String eventHandler) {
		this.eventHandler = eventHandler;
	}
	
	
}
