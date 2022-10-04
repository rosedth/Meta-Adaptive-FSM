package org.rossedth.meta_adaptive_fsm;

import org.rossedth.adaptive_logic.Data;
import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Reasoner;

public class Meta_Reasoner_FSM extends Reasoner{

	public Meta_Reasoner_FSM(Memory mem) {
		super(mem);
	}
	
	public void process(Data data) {
		System.out.println("Processing Data with Meta-Reasoner");
	}
}
