package org.rossedth.meta_adaptive_fsm;

import java.util.List;

import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Plan;
import org.rossedth.adaptive_logic.Selector;

public class Meta_Selector_FSM extends Selector{

	public Meta_Selector_FSM(Memory mem) {
		super(mem);
	}

	public void selectResponse(List<Plan> responses) {
		System.out.println("Selecting Response with Meta-Selector");
		if (!responses.isEmpty()){
			if(responses.size()>1) {
				// There are more than 1 option to select from
			}
			else {
				this.setResponse(responses.get(0));
			}
			sendData();	
		}  
		else {
			// There are no responses
			System.out.println("There is nothing to be done");    				
		}
	}
}
