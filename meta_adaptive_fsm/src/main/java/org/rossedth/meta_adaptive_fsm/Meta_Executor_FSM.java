package org.rossedth.meta_adaptive_fsm;

import org.rossedth.adaptable_adaptive_fsm.FSMData;
import org.rossedth.adaptable_adaptive_fsm.Monitor_FSM;
import org.rossedth.adaptable_fsm.RecognizerFSM;
import org.rossedth.adaptive_logic.AdaptiveLogic;
import org.rossedth.adaptive_logic.Executor;
import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Plan;


public class Meta_Executor_FSM extends Executor{
	
	private Monitor_FSM monitor;
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
		monitor=(Monitor_FSM)((AdaptiveLogic)this.getSysU()).getMonitor();
		monitor.getBlockedEntries().add(action.getElement());
		monitor.setUndefinedEntryListener(
				(event)->{
					System.out.println("Unidentified input " +event.getName()+" detected at state "+ ((RecognizerFSM)monitor.getSysU()).getFSM().getCurrentState().getName()+" reported from NEW UnidentifiedEntryListener");
					monitor.trackUnidentifiedEntries(event.getName());
					if (!monitor.entryBlocked(event.getName())) {
						monitor.saveData(new FSMData(((RecognizerFSM)monitor.getSysU()).getFSM().getCurrentState(),event));
						monitor.saveDataToFile();
						monitor.sendData();													
					}else {
						System.out.println("Blocked entry");						
					}
				}
				);
	}


}
