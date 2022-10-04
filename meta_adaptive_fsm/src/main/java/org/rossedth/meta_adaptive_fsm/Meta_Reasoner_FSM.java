package org.rossedth.meta_adaptive_fsm;

import java.util.ArrayList;
import java.util.List;

import org.rossedth.adaptive_logic.Action;
import org.rossedth.adaptive_logic.Data;
import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Plan;
import org.rossedth.adaptive_logic.Reasoner;

public class Meta_Reasoner_FSM extends Reasoner{

	public Meta_Reasoner_FSM(Memory mem) {
		super(mem);
	}
	
	public void process(Data data) {
		List<Plan> responses=new ArrayList<Plan>();
		System.out.println("Processing Data with Meta-Reasoner");
		
		ALData AL_data=(ALData)data;
		if (AL_data.getALEvent().equalsIgnoreCase("onLimitReached")){
			Plan plan=new Plan();
			List<Action> actions=new ArrayList<Action>();
			
			actions.add(new ALAction("block",AL_data.getEntry(),AL_data.getALEvent()));
			plan.setActions(actions);
			responses.add(plan);
		}
		
		
		setResponses(responses);
		sendResponses();
		
	}
}
