package org.rossedth.meta_adaptive_fsm;

import org.rossedth.adaptive_logic.Data;

public class ALData implements Data{
	protected String ALEvent;
	protected String entry;

	public ALData(String event, String entry) {
		ALEvent = event;
		this.entry=entry;
	}

	public String getALEvent() {
		return ALEvent;
	}

	public void setALEvent(String event) {
		ALEvent = event;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	
}
