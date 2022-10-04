package org.rossedth.meta_adaptive_fsm;

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
		
	}


}
