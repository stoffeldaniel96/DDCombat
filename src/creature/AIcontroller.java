package creature;

import java.util.*;

import DDCombat.CreatureInfo;
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
	
	
	private boolean spotted;					//this value is true if a guard AI spots a character
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
	
	//Name: turnActions
	//inputs: creature host - the creature doing the action
	public void turnActions(creature host, creature target, CreatureInfo hostInfo, CreatureInfo targetInfo)
	{
		switch(state) {
		
		//guard
		case 0:
			//move action
			if(guardWander) 
			{
				//guardPath;	//not yet coded
			}
			else
				//wander;		//not yet coded
		
			//spot check
			//spotted = spotCheck();
		
			//update character's state
			updateState(host.getHitPoints(), false, false);

		//alert
		case 1:
			//engage
			/*pick a random enemy*/
			//if not near enemy, charge
			if(!characterNear(hostInfo, targetInfo))
			{
				charge();
			}
			else
			{
				strike();
			}
			
			//if character hp is below a certain threshold maybe heal
			
		//scared
		case 2:
			flee();
			//possibly heal
		//panic
		case 3:
			rout();
			//possibly heal
		}
	}
	
	
	//strike
	//attacks target character
	public void strike()
	{
		//roll attack roll
		//check target AC
		//if hits, calculate and deal damage
		//else, do nothing
	}
	
	//charge
	//chases a target creature
	public void charge()
	{
		//try to move next to target
		//if not possible, try to move as close as possible
	}
	
	public void flee()
	{
		//get distance from target
	}
	
	public void rout()
	{
		//get off the map
	}
	
	//characterNear
	//returns true if target character is within striking distance of host character
	public boolean characterNear(CreatureInfo host, CreatureInfo target)
	{
		//if host is here
		//X-possible position T-target's position O-nothing
		//
		//XOO
		//XTO
		//XOO
		if(host.getLocx() == target.getLocx()-1 && (host.getLocy() == target.getLocy()-1 || host.getLocy() == target.getLocy() ||host.getLocy() == target.getLocy()+1))
		{
			return true;
		}
		//host is in this position
		//OXO
		//OTO
		//OXO
		else if(host.getLocx() == target.getLocx() &&(host.getLocy() == target.getLocy()-1 || host.getLocy() == target.getLocy()+1))
		{
			return true;
		}
		//host is in this position
		//OOX
		//OTX
		//OOX
		else if(host.getLocx() == target.getLocx()+1 && (host.getLocy() == target.getLocy()-1 || host.getLocy() == target.getLocy() ||host.getLocy() == target.getLocy()+1))
		{
			return true;
		}
		
		//in this case, host character is not in striking position of target character
		return false;
	}
}
