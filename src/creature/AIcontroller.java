package creature;

import java.util.*;
/*
 * Name: AIcontroller.java
 * Description:
 * 		This class operates the AI for NPCs in game.
 * The AI operates like a state machine, switching
 * between states depending on certain trigger conditions.
 * After checking the state, the AI can semi-randomly pick
 * what action to take depending on it's state.
 */

public class AIcontroller {

	Random rolls = new Random();
	private int state;									// state's values
														//0 - guarding
														//1 - alert/fighting
														//2 - scared
														//3 - panic
	
	//this value is true if a guard AI spots a character
	private boolean spotted;
	private boolean guardWander;
	private int maxHP;
	
	public AIcontroller(int HP, boolean isAlert, boolean guardWander) 
	{
		//connects a character to the AI
		maxHP = HP;
		
		//set up guard status
		if(!isAlert) {
			spotted = false;
			//guard if true, wander if false
			this.guardWander = guardWander;
			state = 0;
		}

		else{
			spotted = true;		
			state = 1;
		}
		
	}
	
	public int getState() 
	{
		return state;
	}
	
	/*Name: updateState
	 *returns nothing
	 *inputs: int currHP - the current HP of the character controlled.
	 *	HP can determine state changes.
	 *  	  
	 *        boolean critHit, boolean rally:  
	 *        critHit- determines whether the character was critically hit
	 *        rally - determines if a leader has done a rally
	 * 
	 */
	public void updateState(int currHP, boolean critHit, boolean rally) 
	{
		switch(state) {
		
		//guard state
		case 0:
			//spotted character, change to alert state
			if(spotted)
			{
				state = 1;
			}
		
		//alert state
		case 1:
			//character is rattled, change to scared state
			if(currHP < (maxHP/2) && rolls.nextInt(20) >= 17)
			{
				state = 2;
			}
			//character panics if critically hit or low on HP
			else if(critHit && rolls.nextInt(20) >= 17)
			{
				state = 3;
			}
			else if(currHP < (maxHP/4))
			{
				state = 3;
			}
			
		
		//scared state
		case 2:
			//scared to panic
			if(currHP < (maxHP/4) && rolls.nextInt(20) >= 17)
			{
				state = 3;
			}
			//rallied
			else if(rally)
			{
				state = 1;
			}
		//panic state
		case 3:
			if(rally || currHP > (maxHP/2) && rolls.nextInt(20) > 12)
			{
				state = 1;
			}
		
		}
	}
}
