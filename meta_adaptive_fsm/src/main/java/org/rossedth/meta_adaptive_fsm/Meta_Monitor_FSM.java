package org.rossedth.meta_adaptive_fsm;


import java.io.IOException;
import java.nio.file.Paths;

import org.rossedth.adaptable_adaptive_fsm.EntryTracker_FSM;
import org.rossedth.adaptable_adaptive_fsm.Monitor_FSM;
import org.rossedth.adaptable_adaptive_fsm.Monitor_FSM.ILimitReachedEvent;
import org.rossedth.adaptive_logic.AdaptiveLogic;
import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Meta_Monitor_FSM extends Monitor{
	private ILimitReachedEvent limitReachedlistener;
	private AdaptiveLogic sys_U;
	
	public Meta_Monitor_FSM () {};
	public Meta_Monitor_FSM  (Memory mem) {
		super(mem);
	}
	
	public void sense() {
		sys_U=(AdaptiveLogic)this.getSysU();
		Monitor_FSM monitor= (Monitor_FSM)sys_U.getMonitor();
		limitReachedlistener=new Monitor_FSM.ILimitReachedEvent(){

			@Override
			public void onLimitReached(String entry, EntryTracker_FSM tracker) {
				System.out.println("A frequent Unidentified entry detected from Meta-Monitor");	
				saveData(new ALData("onLimitReached",entry));
				saveDataToFile();
				sendData();
				
			}
			
		};
		monitor.setLimitReachedListener(limitReachedlistener);
		
	}
	
	public void saveDataToFile() {
		 ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		 try {
			mapper.writeValue(Paths.get("Meta_Monitor_Data.json").toFile(), this.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
