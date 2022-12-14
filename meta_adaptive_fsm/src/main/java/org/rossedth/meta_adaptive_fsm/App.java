package org.rossedth.meta_adaptive_fsm;

import java.io.IOException;
import java.util.Scanner;

import org.jeasy.states.api.FiniteStateMachineException;
import org.rossedth.adaptable_adaptive_fsm.AdaptiveLogicListener;
import org.rossedth.adaptable_adaptive_fsm.Executor_FSM;
import org.rossedth.adaptable_adaptive_fsm.Monitor_FSM;
import org.rossedth.adaptable_adaptive_fsm.Reasoner_FSM;
import org.rossedth.adaptable_adaptive_fsm.Selector_FSM;
import org.rossedth.adaptable_fsm.GraphViz;
import org.rossedth.adaptable_fsm.RecognizerFSM;
import org.rossedth.adaptive_logic.AdaptiveLogic;
import org.rossedth.adaptive_logic.Executor;
import org.rossedth.adaptive_logic.Memory;
import org.rossedth.adaptive_logic.Monitor;
import org.rossedth.adaptive_logic.Reasoner;
import org.rossedth.adaptive_logic.Selector;


/**
 * 
 * This tutorial is an implementation as proof of concept of the Holistic Model for adaptivity developed as part of a PhD project at EPUSP
 * @author Rosalia Edith Caya Carhuanina (rosalia.caya@usp.br)
 * 
 */

class Launcher {
	public static GraphViz viewer=new GraphViz();
	public static int max_app_entry;

	public static void main(String[] args) throws FiniteStateMachineException, IOException {


		/*
		 * Create a RecognizerFSM instance
		 */
		RecognizerFSM recognizer=new RecognizerFSM();
		
		/*
		 * Setup GraphViz
		 */

		viewer.addln(viewer.start_graph());
		viewer.setup_graph("GraphViz.config");

		
		/*
		 * The max. num of appearances allowed for unidentified entries can be loaded from a configuration file. In this case we indicate it will be 3.  
		 */
		max_app_entry=3;
		
        /*
         * Create a AdaptiveLogic instance
         */

    	AdaptiveLogic AL=new AdaptiveLogic();
    	createAdaptiveLogic(AL,recognizer);
    	AL.init();

        /*
         * Create a second AdaptiveLogic instance (meta-adaptive logic)
         */    	
    	AdaptiveLogic metaAL=new AdaptiveLogic();
    	createMetaAdaptiveLogic(metaAL, AL);
    	metaAL.init();
    	
    	
    	/*
		 * Fire some events and print FSM state
		 */

		recognizer.printCurrentState(viewer);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Insert an input or Press [q] to quit tutorial.");
		System.out.println("=================================================");

		while (true) {
			String input = scanner.nextLine();

			recognizer.processInput(input);
			recognizer.printCurrentState(viewer);

			if (recognizer.atFinalState()) {
				System.out.println("Recognizer has reach final state ");   
				System.exit(0);
				scanner.close();                
			}

			if (input.trim().equalsIgnoreCase("q")) {
				System.out.println("input = " + input.trim());
				System.out.println("Bye!");
				System.exit(0);
				scanner.close();
			}         

		}

	} 
    
    
    public static void createAdaptiveLogic(final AdaptiveLogic AL,RecognizerFSM sys_U) {
    	Memory mem= new Memory();
    	Monitor mon=new Monitor_FSM(mem,max_app_entry);
    	Reasoner rea=new Reasoner_FSM(mem);
    	Selector sel=new Selector_FSM(mem); 
    	Executor ex=new Executor_FSM(mem);
    	
    	AL.loadAdaptiveComponents(mem, mon, rea, sel, ex);   	
    	AL.connect(sys_U);

    }
    
    public static void createMetaAdaptiveLogic(final AdaptiveLogic metaAL,AdaptiveLogic sys_U) {
    	Memory meta_mem= new Memory();
    	Monitor meta_mon=new Meta_Monitor_FSM(meta_mem);
    	Reasoner meta_rea=new Meta_Reasoner_FSM(meta_mem);
    	Selector meta_sel=new Meta_Selector_FSM(meta_mem); 
    	Executor meta_ex=new Meta_Executor_FSM(meta_mem);
    	
    	metaAL.loadAdaptiveComponents(meta_mem, meta_mon, meta_rea, meta_sel, meta_ex);   	
    	metaAL.connect(sys_U);
    }
    
}
