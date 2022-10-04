package org.rossedth.meta_adaptive_fsm;

import org.rossedth.adaptive_logic.AdaptiveLogic;
import org.rossedth.adaptive_logic.Executor;
import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Plan;


public class Meta_Executor_FSM extends Executor{

	public Meta_Executor_FSM(Memory mem) {
		super(mem);
		// TODO Auto-generated constructor stub
	}

	public void command(Plan response) {
		System.out.println("Commanding Actions from Meta-Executor"); 
		AdaptiveLogic AL=(AdaptiveLogic)this.getSysU();
		if (response!=null) {
			System.out.println("Meta-Executor needs to translate the plan and modify the AL");	
			ALAction action=(ALAction)response.getActions().get(0);
			if(action.getType().equalsIgnoreCase("block")) {
				applyBlockEntryAction(action, AL);
			}

		}

	}

	private void applyBlockEntryAction(ALAction action, AdaptiveLogic AL) {
		System.out.println("Meta-Executor blocking (ignoring) the use of entry "+ action.getElement());		
	}


}
